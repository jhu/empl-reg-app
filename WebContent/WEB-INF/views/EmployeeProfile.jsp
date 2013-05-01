<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Employee Profile</h2>

<b>${employee.firstName} ${employee.middleInitial} ${employee.lastName}</b><br/>
${employee.email}<br/>
${employee.home.phone}<br/>
${employee.office.street}<br/>
${employee.office.city}, ${employee.office.state} ${employee.office.zipCode}<br/>
Supervisor: ${employee.supervisor.fullName} (${employee.supervisor.email})
<br/><br/>
<a href="/jhu5/SweMvc/ShowEmployees">Back to Employees List</a>