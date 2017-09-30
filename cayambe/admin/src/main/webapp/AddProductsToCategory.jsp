<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<h3> Add Product To Category</h3>
<table border=0>

 <tr>
   <td>SKU</td>
   <td>Title</td>
   <td>price</td>
   <td>Add</td>
 </tr>

<form:form action="AddProductToCategory.do" scope="request"> 
  <form:hidden property="categoryId"/>

 <logic:iterate name="products" id="p" scope="request">
	
  <tr>
   <td><bean:write name="p" property="SKU"/><br></td>
   <td><bean:write name="p" property="title"/><br></td>
   <td><bean:write name="p" property="price"/><br></td>
   <td>
     <%-- <INPUT TYPE="checkbox" NAME="productId" VALUE="<bean:write name="p" property="productId"/>"> --%>
	 <form:checkbox name="p" property="productId" />
   </td>
  </tr>

 </logic:iterate> 

  <tr>
    <td align="center" colspan=3>
      <form:submit property="submit" value="Submit"/>
    </td>
  </tr>

</form:form>

</table>