<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*, java.util.List" %>
<%@ page isELIgnored ="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>Lista de Estudantes</title>
    </head>
    <body>
        <h1>Lista de Estudantes do Ensino Superior</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Curso</th>
            </tr>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.name}" /></td>
                    <td><c:out value="${student.course}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>