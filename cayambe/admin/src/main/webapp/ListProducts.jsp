<%@ page import="org.cayambe.core.*" %> 
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<center>
<h3>Product List</h3>
<table border=1>

  <tr>
   <td><b>SKU</b></td>
   <td><b>Title</b></td>
   <td><b>Price</b></td>
   <td><b>Sale Price</b></td>
   <td><b>On Sale</b></td>
   <td><b>Visible</b></td>
   <td><b>View</b></td>
   <td><b>Edit</b></td>
   <td><b>Remove</b></td>
 </tr>


 <logic:iterate id="p" scope="request" name="products" type="org.cayambe.core.ProductVO">

 <tr>
   <td><bean:write name="p" property="SKU"/><br></td>
   <td><bean:write name="p" property="title"/><br></td>
   <td><bean:write name="p" property="dollarPrice"/><br></td>
   <td><bean:write name="p" property="dollarSalePrice"/><br></td>
   <td><bean:write name="p" property="onSale"/><br></td>
   <td><bean:write name="p" property="visible"/><br></td>
   <td>
     <html:link page="/ViewProduct.do"
              paramId="productId" paramName="p" paramProperty="productId"		
              styleClass="normal">View</html:link>
   </td>
   <td>
     <html:link page="/EditProduct.do"
              paramId="productId" paramName="p" paramProperty="productId"		
              styleClass="normal">Edit</html:link>
   </td>
   <td>
     <html:link page="/RemoveProduct.do"
              paramId="productId" paramName="p" paramProperty="productId"		
              styleClass="normal">Remove</html:link>
   </td>
  </tr>

 </logic:iterate> 

</table>