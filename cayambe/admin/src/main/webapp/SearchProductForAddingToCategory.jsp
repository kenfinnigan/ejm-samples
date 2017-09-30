<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<center>
<h3>Search For Product Adding to Category</h3>

<form:form action="ListProductForAddingToCategory.do">
<table border="0" width="100%">

  <form:hidden property="categoryId"/>

  <tr>
    <th align="right">
      SKU Search String:
    </th>
    <td align="left">
      <form:text name="productVO" property="SKU" size="20"/>  
    </td>
  </tr>
  <tr>
    <th align="right">
      Title Search String:
    </th>
    <td align="left">
      <form:text name="productVO" property="title" size="30"/> 
    </td>
  </tr>
  <tr>
    <th align="right">
      Desc Search String:
    </th>
    <td align="left">
      <form:text name="productVO" property="desc" size="30"/> 
    </td>
  </tr>

  <tr>
    <td align="right">
      <form:submit property="submit" value="Submit"/>
    </td>
    <td align="left">
      <form:reset/>
    </td>
  </tr>


</table>

</form:form>