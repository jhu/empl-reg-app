<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- author: johnson hu -->
<!-- date: 9/20/12 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Registration Form</title>
</head>
<body>
	<h2>Employee Registration Form</h2>
	<form:form modelAttribute="employeeRegistrationForm" action="RegisterEmployee" method="POST">
		<table width="75%" align="center">
			<tr>
				<td>Please enter the information below and click "<b>Submit</b>"
					when complete. Fields marked with an asterisk '<font color="red"><b>*</b></font>'
					are required. Click "<b>Reset</b>" to clear the fields. Click <a
					href="ShowEmployees">here</a> to return to table of employees.
				</td>
			</tr>
			<!-- 
			<tr>
				<td><form:errors path="*" cssClass="errorblock" element="div" /></td>
			</tr>
			 -->
			<tr>
				<td>
					<fieldset title="personalInfo">
						<legend>
							<b>Employee Personal Information</b>
						</legend>
						<table border="0" cellspacing="3" cellpadding="0" align="center">
							<tr>
								<td align="right"><form:label for="firstName" path="firstName" cssErrorClass="error"><font color="red">*</font> First Name </form:label></td>
								<td align="left"><form:input path="firstName" size="25" maxlength="70" /> <form:errors path="firstName" cssStyle="color:red"/></td>
							</tr>
							<tr>
								<td align="right"><form:label for="lastName" path="lastName" cssErrorClass="error"><font color="red">*</font> Last Name </form:label></td>
								<td align="left"><form:input path="lastName" size="25" maxlength="70" /> <font color="red"><form:errors path="lastName" /></font></td>
							</tr>
							<tr>
								<td align="right"><form:label for="middleInitial" path="middleInitial" cssErrorClass="error">Middle Initial </form:label></td>
								<td align="left"><form:input path="middleInitial" size="3" maxlength="10" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="email" path="email" cssErrorClass="error"><font color="red">*</font> Email Address </form:label></td>
								<td align="left"><form:input path="email" size="25" maxlength="70" /><font color="red"> <form:errors path="email" /></font></td>
							</tr>
							<tr>
								<td align="right"><form:label for="phoneNumber" path="phoneNumber" cssErrorClass="error">Phone Number </form:label></td>
								<td align="left"><form:input path="phoneNumber" size="20" maxlength="70" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="jobTitle" path="jobTitle" cssErrorClass="error">Job Title </form:label></td>
								<td align="left"><form:input path="jobTitle" size="25" maxlength="70" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="street" path="street" cssErrorClass="error">Address </form:label></td>
								<td align="left"><form:input path="street" size="40" maxlength="100" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="city" path="city" cssErrorClass="error">City </form:label></td>
								<td align="left"><form:input path="city" size="20" maxlength="70" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="state" path="state" cssErrorClass="error">State </form:label></td>
								<td align="left"><form:input path="state" size="20" maxlength="70" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="zipCode" path="zipCode" cssErrorClass="error">Zip code </form:label></td>
								<td align="left"><form:input path="zipCode" size="15" maxlength="40" /></td>
							</tr>
							
							<tr>
								<td align="right"><form:label for="supervisor" path="supervisor" cssErrorClass="error">Supervisor </form:label></td>
								<td align="left">
								<form:select path="supervisor" items="${supervisors}" itemValue="id" itemLabel="fullName" />
								</td>
							</tr>
							
						</table>
					</fieldset>
					<fieldset title="workInfo">
						<legend>
							<b>Office Information</b>
						</legend>
						<table border="0" cellspacing="3" cellpadding="0" align="center">
							<tr>
								<td align="right"><form:label for="workStreet" path="workStreet" cssErrorClass="error">Address </form:label></td>
								<td align="left"><form:input path="workStreet" size="40" maxlength="100" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="workCity" path="workCity" cssErrorClass="error">City </form:label></td>
								<td align="left"><form:input path="workCity" size="20" maxlength="70" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="workState" path="workState" cssErrorClass="error">State </form:label></td>
								<td align="left"><form:input path="workState" size="20" maxlength="70" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="workZipCode" path="workZipCode" cssErrorClass="error">Zip code </form:label></td>
								<td align="left"><form:input path="workZipCode" size="15" maxlength="40" /></td>
							</tr>
							<tr>
								<td align="right"><form:label for="workPhoneNumber" path="workPhoneNumber" cssErrorClass="error">Phone number </form:label></td>
								<td align="left"><form:input path="workPhoneNumber" size="20" maxlength="70" /></td>
							</tr>
						</table>
					</fieldset>
					<hr />
					<div align="center">
						<input type="submit" value="Submit" />&nbsp;<input type="reset" />
					</div>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>