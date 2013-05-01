<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- author: johnson hu -->
<!-- date: 9/20/12 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Employees</title>
<style type="text/css">
table.myTable {
	border-collapse: collapse;
}

table.myTable td,table.myTable th {
	border: 1px solid black;
	padding: 5px;
}
</style>
</head>
<body>
	
	Click <a href="RegisterEmployee">here</a> to register a new employee.
	<br /><br />
	
	<fieldset title="searchFunction">
		<legend>
			<b>Employee Search</b>
		</legend>
		<form:form modelAttribute="employeeSearchForm" action="ShowEmployees" method="post">
			<table border="0" cellspacing="3" cellpadding="0" align="center">
				<tr>
					<td align="right"><form:label for="searchTerm" path="searchTerm" cssErrorClass="error">Search: </form:label></td>
					<td align="left"><form:input path="searchTerm" size="25" maxlength="70" /> </td>
					<td align="left"><input type="submit" value="Search"/> </td>
				</tr>
				<!-- 
				<tr>
					<td></td>
					<td><font color="red"><form:errors path="searchTerm" /></font></td>
				</tr>
				 -->
			</table>
			<div align="center"><font color="red"><form:errors path="searchTerm" /></font></div>
		</form:form>
	</fieldset>

	<br/>
	<font color="red"><c:out value="${actionMessage}" /></font>

	<c:choose>
		<c:when test="${empty employees}">
			<div align="center">
				<b>There are no employees in our records.</b>
			</div>
		</c:when>
		<c:otherwise>
			<table border="1" width="80%" align="center">
				<caption>
					<h2>Current Employees</h2>
				</caption>
				<tr>
					<th>Action</th>
					<th>Last Name</th>
					<th>First Name</th>
					<th>Middle Initial</th>
					<th>Email</th>
					<th>Phone Number</th>
				</tr>
				<c:forEach items="${employees}" var="bean">
					<c:url value="ShowEmployees/delete/${bean.id}" var="delLink" />
					<c:url value="ShowEmployees/view/${bean.id}" var="viewLink" />

					<tr>
						<td><a href="${delLink}">Delete</a> | <a href="${viewLink}">View</a></td>
						<td>${bean.lastName}</td>
						<td>${bean.firstName}</td>
						<td>${bean.middleInitial}</td>
						<td>${bean.email}</td>
						<td>${bean.home.phone}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>