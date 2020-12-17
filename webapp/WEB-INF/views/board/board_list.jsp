<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.6.2/css/buttons.dataTables.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.5/css/responsive.dataTables.css" />


<meta charset="UTF-8">
<title>보드 리스트</title>
</head>
<body>
    <form id="form1" action="../ajax/get_list">
        <input type="button" id="search" value="조회" />
        <input type="button" id="submit" value="제출" />
        <select id="level" name="level">
            <option value="">전체</option>
            <option value="0">BRONZE</option>
            <option value="1">SILVER</option>
            <option value="2">GOLD</option>
        </select>

        <select id="field" name="field">
            <option value="">전체</option>
            <option value="id">ID</option>
            <option value="name">NAME</option>
        </select>
        <input type="text" name="searchText" id="searchText" value="${search.searchText}" />
        <table id="table" class="display" style='width : 100%'>
            
            <thead>
                <tr>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>레벨</th>
                </tr>
            </thead>
        </table>
    </form>
    <script src="https://code.jquery.com/jquery-3.0.0.js" ></script>
    <script src="https://code.jquery.com/jquery-migrate-3.3.0.js" ></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js" ></script>
    <script src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.js" ></script>
    <script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.js" ></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js" ></script>
    <script src="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/dataTables.altEditor.free.js"></script>

    <script>
        var table;
        $(document).ready(function() {
            $('#level').val('${search.level}');
            $('#field').val('${search.field}');

            table = $('#table').DataTable({
                serverSide : true,
                processing : true,
                info : false,
                ordering : false,
                searching : false,
                altEditor : true,
                dom: 'Bfrtip',
                responsive: true,
                select: 'single',
                buttons: [
                    {
                    text: 'Add',
                    name: 'add'        // do not change name
                    },
                    {
                    extend: 'selected', // Bind to Selected row
                    text: 'Edit',
                    name: 'edit'        // do not change name
                    },
                    {
                    extend: 'selected', // Bind to Selected row
                    text: 'Delete',
                    name: 'delete'      // do not change name
                    }
                ],
                ajax : {
                    url : '../ajax/get_list',
                    type : 'POST',
                    dataSrc : function(json) {
                        return json.data;
                    },
                    data : function(d) {
                        d.field = $('#field').val();
                        d.level = $('#level').val();
                        d.searchText = $('#searchText').val();
                    }
                },
                columns : [
                    {"data" : "name"},
                    {"data" : "email"},
                    {"data" : "level"}
                ],
                'select': {
                    'style': 'os'
                },
                order: [[ 0, 'asc' ]],
                 onAddRow: function(datatable, rowdata, success, error) {
                    $.ajax({
                        // a tipycal url would be / with type='PUT'
                        url: '../ajax/data_ajax',
                        type: 'POST',
                        data: rowdata,
                        success: success,
                        error: error,
                        dataType : 'JSON'
                    });
                },
             });
        });

        $('#search').click(function() {
            table.ajax.reload();
        });

        /*
        $('#submit').click(function() {
           var form = this;

            var rows_selected = table.column(0).checkboxes.selected();

            // Iterate over all selected checkboxes
            $.each(rows_selected, function(index, rowId){
                // Create a hidden element
                $(form).append(
                    $('<input>')
                        .attr('type', 'hidden')
                        .attr('name', 'id[]')
                        .val(rowId)
                );
            });
        });
        */


    </script>
</body>
</html>