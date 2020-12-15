<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css" />
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<meta charset="UTF-8">
<title>보드 리스트</title>
</head>
<body>
    <form id="form1" action="../ajax/get_list">
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
    <script>
        var table;
        $(document).ready(function() {
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
                        d.form = $('#form1').serializeArray();
                    }
                },
                columns : [
                    {"data" : "name"},
                    {"data" : "email"},
                    {"data" : "level"}
                ],
                columnDefs : [

                ]
             });
        });

        $('#submit').click(function() {
            table.ajax.reload();
        });

        var ajax = {
            url : '../ajax/get_list',
            data : function(d) {
                d.form = $('#form1').serializeArray();
            }
        };

        function dataTableInit(tableId, ajaxObj, columns, columnDefs, ordering = true, searching = false) {

        }
    </script>
</body>
</html>