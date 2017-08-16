<%
	String cartId = (String)request.getAttribute("cartId");
	response.sendRedirect("/OpenCart.do?cartId=" + cartId);
%>
