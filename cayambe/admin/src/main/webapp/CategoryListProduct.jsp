<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<table border=1 width="350">

  <tr>
   <td><b>SKU</b></td>
   <td><b>Title</b></td>
   <td><b>Description</b></td>
   <td><b>Include Product</b></td>
  </tr>

<form:form action="UpdateProductToCategory.do" scope="request">
 
  <form:hidden property="categoryId"/>

  <logic:iterate id="p" scope="request" name="products">

   <tr>
    <td><bean:write name="p" property="SKU"/><br></td>
    <td><bean:write name="p" property="title"/><br></td>
    <td><bean:write name="p" property="desc"/><br></td>
    <td>
	  <input type="checkbox" name="productId" value="<bean:write name="p" property="productId"/>" checked>
	</td>
   </tr>

  </logic:iterate> 

  <tr>
    <td align="center" colspan="4">
      <form:submit property="submit" value="Submit"/>   
      <form:reset/>
    </td>
  </tr>


</form:form>

</table>