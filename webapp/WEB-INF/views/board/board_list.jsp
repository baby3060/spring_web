<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="//cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css" />

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="//cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>

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
                    <th></th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>레벨</th>
                </tr>
            </thead>
        </table>
    </form>
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
                    {"data" : "memberSeq", render : function(data, type, row) {
                         if ( type === 'display' ) {
                            return '<input type="checkbox" id="seqChk" class="editor-active">';
                        }
                        return data;
                    },  className: "dt-body-center"},
                    {"data" : "name"},
                    {"data" : "email"},
                    {"data" : "level"}
                ],
                'columnDefs': [
                    {
                        'targets': 0,
                        'checkboxes': {
                            'selectRow': true
                        }
                    }
                ],
                'select': {
                    'style': 'multi'
                },
                order: [[ 1, 'asc' ]]
             });
        });

        $('#search').click(function() {
            table.ajax.reload();
        });

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
    </script>
</body>
</html>