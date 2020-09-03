<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de productos</title>
</head>
<body>

<h3>Lista de productos</h3>

<table border="1">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Cantidad</th>
<th>Precio</th>
<th>Fecha de registro</th>
<th>Fecha actualizacion</th>
<th>Acciones<th>

</tr>

<c:forEach var="producto" items="${lista}">

<tr>
<td><c:out value="${producto.id }"></c:out></td>
<td><c:out value="${producto.nombre}"></c:out></td>
<td><c:out value="${producto.cantidad}"></c:out></td>
<td><c:out value="${producto.precio}"></c:out></td>
<td><c:out value="${producto.fechaCrear}"></c:out></td>
<td><c:out value="${producto.actualizarFecha}"></c:out></td>

<td><a href="productos?opcion=eliminar&id=<c:out value="${producto.id}"></c:out>">Eliminar</a> 

<a href="productos?opcion=editar&id=<c:out value="${ producto.id}"></c:out>"> Editar</a>
</td>

</tr>
</c:forEach>

</table>

</body>
</html>