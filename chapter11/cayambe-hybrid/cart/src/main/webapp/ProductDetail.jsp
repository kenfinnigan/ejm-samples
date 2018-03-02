<%@ page language='java' %>
<%@ page import="org.cayambe.core.*" %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-form.tld' prefix='form' %>

<bean:define id="product" name="product" type="ProductVO" scope="request" />
<bean:parameter id="CategoryId" name="CategoryId" />

<body bgcolor="#ffffff">


 <bean:define id="image" name="product" property="image" scope="page" />
 <logic:notEmpty scope="page" name="image">
  <img src="<bean:write name="image" scope="page" />">
 </logic:notEmpty>


 <table>
  <tr>
   <td colspan=2 align=left>
    <bean:write name="product" property="title" scope="page" />
   </td>
  </tr>
  <tr>
   <td colspan=2 align=left>
    <bean:write name="product" property="desc" scope="page" />
   </td>
  </tr>


  <logic:equal name="product" property="onSale" scope="page" value="true">
  <tr>
   <td align=left>Sale Price:</td>   
   <td align=left>
    <bean:write name="product" property="dollarSalePrice" scope="page" />
   </td>
  </tr>
  </logic:equal>
  <logic:notEqual name="product" property="onSale" scope="page" value="true">
  <tr>
   <td align=left>Price:</td>
   <td align=left>
    <bean:write name="product" property="dollarPrice" scope="page" />
   </td>
  </tr> 
  </logic:notEqual>
  <tr>
   <td align=center colspan=2>
     <form action="AddToCart.do" method="post">
	   <form:hidden name="product" property="productId" />
       <input type="hidden" name="CategoryId" value="<bean:write name="CategoryId" />">
	   <form:submit property="submit" value="Add To Cart"/>
     </form>
   </td>
  </tr>
</table>

</body>