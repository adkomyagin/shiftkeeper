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

		<table style="display: inline-block;" align='center'>
			<tr>
			<td><i>Support Team</i></td>
			</tr>
			<form:form align='center' method='post' commandName="aGroup">
			<form:hidden path="name" value="support" />
			<tr>
				<form align='center' method='post'>
					<td><input type='search' size='30' name='email'
						placeholder='Enter the email' required><br></td>
					<td colspan=2><input type="submit" align="center" name="add"
						value="Add"></td>
			</tr>
			</form>

			<c:forEach items="${requestScope.support}" var="user">
				<tr>
					<form:form align='center' method='post' commandName="aUser">
						<td>${user.displayName}</td>
						<form:hidden path="name" value="${user.name}" />
						
						<td colspan=2><input type="submit" align="center"
							name="remove" value="Remove"></td>
				</tr>
				</form:form>
			</c:forEach>
			</form:form>
		</table>

<table style="display: inline-block;" align='center'>
			<tr>
			<td><i>Team USA</i></td>
			</tr>
			<form:form align='center' method='post' commandName="aGroup">
			<form:hidden path="name" value="support_us" />
			<tr>
				<form align='center' method='post'>
				    <input type='hidden' name='group' value='support_us'>
					<td><input type='search' size='30' name='email'
						placeholder='Enter the email' required><br></td>
					<td colspan=2><input type="submit" align="center" name="add"
						value="Add"></td>
			</tr>
			</form>
			
			<c:forEach items="${requestScope.support_us}" var="user">
				<tr>
					<form:form align='center' method='post' commandName="aUser">
						<td>${user.displayName}</td>
						<form:hidden path="name" value="${user.name}" />
						
						<td colspan=2><input type="submit" align="center"
							name="remove" value="Remove"></td>
				</tr>
				</form:form>
			</c:forEach>
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
