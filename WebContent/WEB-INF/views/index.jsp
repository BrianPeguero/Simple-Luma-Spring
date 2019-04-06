<%@page import="java.util.Set"%>
<%@page import="models.Product"%>
<%@page import="java.util.LinkedHashMap"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% LinkedHashMap<String, Product> firstFiveProducts = (LinkedHashMap<String, Product>) request.getAttribute("firstFiveProducts"); %>

<% Object[] productKeyArray = firstFiveProducts.keySet().toArray(); %>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../components/__head.jsp"></jsp:include>
	</head>
	<body>
		<header class="nav">
			<jsp:include page="../components/__header.jsp"></jsp:include>
		</header>
		
		<body>
			<div class="mainWelcomePage">
				<img src="localhost/magento2/pub/media/wysiwyg/training/training-main.jpg"/>
			</div>
			<div class="container">
				<% for (int i = 0; i < productKeyArray.length; i++) { %>
					<div class="item">
						<img src="<%= firstFiveProducts.get(productKeyArray[i]).getImage_file() %>" class="productImage"/>
						<div class="productName">
							<%= firstFiveProducts.get(productKeyArray[i]).getName() %>
						</div>
						<div class="productPrice">
							<%= firstFiveProducts.get(productKeyArray[i]).getPrice() %>
						</div>
					</div>
				<% } %>			
			</div>	
		</body>
		
		<footer class="footer">
			<jsp:include page="../components/__footer.jsp"></jsp:include>
		</footer>
	</body>
</html>