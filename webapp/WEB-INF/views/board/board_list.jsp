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
        <input type="button" id="submit" value="수정" />
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
                    <th>선택</th>
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
                    extend: 'selectedSingle', // Bind to Selected row
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
                    // columnDefs가 안 먹힐 경우 columns에서 수정해 볼 것.
                    {"data" : "memberSeq", "type" : "hidden"},
                    {"data" : "name"},
                    {"data" : "email"},
                    {"data" : "userLevel", "special" : "user_level"}
                ],
                columnDefs : [
                    {
                        targets : 0,
                        type : 'hidden',
                        render : function(data, type, row, meta) {
                            return "<input type='checkbox' name='input_0' /><input type='hidden' name='memberSeq' value='" + data + "'/>";
                        }
                    },
                    {
                        targets : 1,
                        render : function(data, type, row, meta) {
                            return "<input type='text' name='input_1' onkeyup='dataChange(this)' value='" + data + "' />" ;
                        }
                    },
                    {
                        targets : 2,
                        render : function(data, type, row, meta) {
                            return "<input type='text' name='input_2' onkeyup='dataChange(this)' value='" + data + "' />" ;
                        }
                    },
                    {
                        targets : 3,
                        type : 'radio',
                        radioCol : [{value : '0', label : 'BRONZE', checked : true}, {value : '1', label : 'SILVER', checked : false}],
                        render : function(data, type, row, meta) {
                            return "<input TYPE='radio' id='input_" + meta.row + "_3_B' name='input_3_" + meta.row + "' value='0' " + ((data === 'BRONZE')?'checked':'') +" />" +
                                   "<label for='input_" + meta.row + "_3_B'>BRONZE</label>" + 
                                   "<input TYPE='radio' id='input_" + meta.row + "_3_S' name='input_3_" + meta.row + "' value='1' " + ((data === 'SILVER')?'checked':'') +" />" +
                                   "<label for='input_" + meta.row + "_3_S'>SILVER</label>";
                        }
                    }
                ],
                'select': 'multi',
                order: [[ 0, 'asc' ]],

                onAddRow: function(datatable, rowdata, success, error) {
                    $.ajax({
                        // a tipycal url would be / with type='PUT'
                        url: '../ajax/data_ajax',
                        type: 'POST',
                        data: { rowdata : rowdata, sqlmap : 'memberMapper.insertMember', code : 'INSERT' },
                        success: success,
                        error: error,
                        dataType : 'JSON'
                    });
                },

                onDeleteRow: function(datatable, rowdata, success, error) {
                    $.ajax({
                        // a tipycal url would be / with type='PUT'
                        url: '../ajax/data_ajax',
                        type: 'POST',
                        data: { rowdata : rowdata, sqlmap : 'memberMapper.deleteMember', code : 'DELETE' },
                        success: success,
                        error: error,
                        dataType : 'JSON'
                    });
                }
             });


            table.on('user-select',  function( e, dt, type, cell, originalEvent ) {
                var row = dt.row( cell.index().row ).node();
                var $curTarget = $(originalEvent.target);
                
                var tagName = $curTarget[0].nodeName.toLowerCase();
                var flag = true;

                // TD 눌렀을 경우 선택 안 함.
                if( tagName === 'td' || tagName === 'label' ) {
                    e.preventDefault();
                    flag = false;
                } else if( tagName === 'input' ) {
                    var type = $curTarget[0].type.toLowerCase();
                    // text의 경우 값 변경 시 호출할 생각
                    if( type === 'text' ) {
                        e.preventDefault();
                        flag = false;
                    } else if(type === 'radio') {
                        // 현재 row가 selected일 경우
                        if ( $(row).hasClass('selected') ) {
                            e.preventDefault();
                            flag = false;
                        // 현재 row가 selected가 아닐 경우
                        } else {
                            // 다른 값을 선택한다면,
                            var $curData = $curTarget.val();
                            var $originalData = table.cell({row : cell.index().row, column : cell.index().column }).data();

                            if($curData == $originalData) {
                                e.preventDefault();
                                flag = false;
                            }
                        }
                    }
                }

                if(flag) {
                     // Checkbox 체크용
                    var chk = $(row).children('td:first-child').find('input[type=checkbox]');

                    if ( $(row).hasClass('selected') ) {
                        chk.prop('checked', false);
                    } else {
                        chk.prop('checked', true);
                    }
                }
               
            });

        });

        $('#search').on('click', function() {
            table.ajax.reload();
        });

        
        $('#submit').click(function() {
            
            // 이 형식 맞추도록 할 것.
           // var rowdata = "[{\"memberSeq\" : 1, \"name\" : \"aaa\", \"email\" : \"aaa\", \"level\" : 2}, {\"memberSeq\" : 2, \"name\" : \"bbb\", \"email\" : \"bbb\", \"level\" : 1}]";
           $.ajax({
               url: '../ajax/data_ajax',
               type: 'POST',
               data: {rowdata : rowdata, sqlmap : "memberMapper.updateMember", code : "UPDATE"},
               success: function() {
                   table.ajax.reload();
               },
               error: function () {
                   console.log("error");
               },
               dataType : 'JSON'
           });
           
        });
        
        // text type check
        function dataChange(obj) {
            var $tr = $(obj).closest('tr');
            var $row_index = $tr.index();
            var $td = $(obj).closest('td');
            var $col_index = $td.index();
            var cur_value = obj.value;
            // datatable 저장 값
            var $original_value = table.cell({row : $row_index, column : $col_index }).data();
            
            // 유형까지 비교하면 피곤함.
            if(cur_value != $original_value) {
                table.rows($row_index).select();
                var chk = $tr.children('td:first-child').find('input[type=checkbox]');
                chk.prop('checked', true);
            }
        }



    </script>
</body>
</html>