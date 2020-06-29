<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRM App</title>

	<!-- Reference our style -->
	<link type="text/css" 
			rel="stylesheet" 
			href="${pageContext.request.contextPath}/resources/css/style.css" />
	
</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h1>CRM - Customer Relationship Manager</h1> <hr>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- Put a new button : Add Customer -->
			
			<input type="button" value="Add Customer" 
					onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button" />
			
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search customer: <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<!-- add out HTML table here -->
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- Loop over and print customers -->
				
				<c:forEach var="customer" items="${customers}">
				
				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${customer.id}" />
				</c:url>
				
				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${customer.id}" />
				</c:url>
				
					<tr>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td>
							<!-- display update link -->
							<a href="${updateLink}">Update</a>
							/
							<a href="${deleteLink}" 
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
				
			</table>
			
		</div>
	</div>
	

</body>
</html>