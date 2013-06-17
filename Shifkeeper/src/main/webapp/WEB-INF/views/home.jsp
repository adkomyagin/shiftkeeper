<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${requestScope.serverTime}. </P>

<table class="border" align='center'>
	<tr><form align='center' method='post' > 
	<td>
	<input type='search' size='30' name='email' placeholder='Enter the email' required><br>	
	</td> 
	<td colspan=2> 			
			<input type="submit" align="center" name="add" value="Add">
	</td>
	</tr></form>
	
	<c:forEach items="${requestScope.users}" var="user">
	<tr><form align='center' method='post' > 
	<td> ${user.displayName} </td> 
	<td colspan=2> 			
			<input type="submit" align="center" name="remove" value="Remove">
	</td>
	</tr></form>
	</c:forEach>
</table>
	
</body>
</html>
