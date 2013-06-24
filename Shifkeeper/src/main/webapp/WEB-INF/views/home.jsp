<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
<title>ShiftKeeper</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
	<div id="header" align='center'>
		<span><b>Support JIRA Group Management
				Utility</b></span> <br> <br>
	</div>


	<div id="body">

		<table style="float: left;" align='center'>
			<tr>
			<td><i>Support Team</i></td>
			</tr>
			<tr>
				<form:form align='center' method='post' commandName="aRequest">
				    <form:hidden path="group.name" value="support" />
					<td><form:input type='search' size='30' path='user.email' placeholder='Enter the email'/><br></td>
					<td colspan=2><input type="submit" align="center" name="add"
						value="Add"></td>
			</tr>
			</form:form>

			<c:forEach items="${requestScope.support}" var="user">
				<tr>
					<form:form align='center' method='post' commandName="aRequest">
						<td>${user.displayName}</td>
						<form:hidden path="user.name" value="${user.name}" />
						<form:hidden path="group.name" value="support" />
						<td colspan=2><input type="submit" align="center"
							name="remove" value="Remove"></td>
				</tr>
				</form:form>
			</c:forEach>
			
			<tr>
				<form:form align='center' method='post' commandName="aRequest">
				    <form:hidden path="group.name" value="support" />
					<td><form:input type='search' size='30' path='user.name' placeholder='Enter the name'/><br></td>
					<td colspan=2><input type="submit" align="center" name="filter"
						value="Filter"></td>
			</tr>
			</form:form>
		</table>

<table style="float: right;" align='center'>
			<tr>
			<td><i>Team USA</i></td>
			</tr>
			<tr>
				<form:form align='center' method='post' commandName="aRequest">
				    <form:hidden path="group.name" value="support_us" />
					<td><form:input type='search' size='30' path='user.email' placeholder='Enter the email'/><br></td>
					<td colspan=2><input type="submit" align="center" name="add"
						value="Add"></td>
			</tr>
			</form:form>
			
			<c:forEach items="${requestScope.support_us}" var="user">
				<tr>
					<form:form align='center' method='post' commandName="aRequest">
						<td>${user.displayName}</td>
						<form:hidden path="user.name" value="${user.name}" />
						<form:hidden path="group.name" value="support_us" />
						<td colspan=2><input type="submit" align="center"
							name="remove" value="Remove"></td>
				</tr>
				</form:form>
			</c:forEach>
			
			<tr>
				<form:form align='center' method='post' commandName="aRequest">
				    <form:hidden path="group.name" value="support_us" />
					<td><form:input type='search' size='30' path='user.name' placeholder='Enter the name'/><br></td>
					<td colspan=2><input type="submit" align="center" name="filter"
						value="Filter"></td>
			</tr>
			</form:form>
		</table>

	</div>
	
	<div id="footer" align='center'>
		<c:if test = "${requestScope.error != null}">
		<br> <br> <span>ERROR: ${requestScope.error}</span> 
		</c:if>
	</div>
</body>
</html>
