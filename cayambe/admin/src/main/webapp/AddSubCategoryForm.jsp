<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<h3>Add Sub Category</h3>
<html:errors />

<form:form name="CategoryForm" type="org.cayambe.web.admin.form.CategoryActionForm" action="AddSubCategory.do" scope="request">

<table border="1" width="100%">

  <form:hidden name="categoryVO" property="categoryId"/> 
  <form:hidden name="categoryVO" property="parentId"/> 

  <tr>
    <th align="right" width="125">
      Category Name:
    </th>
    <td align="left">
      <form:text name="categoryVO" property="name" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Category Header:
    </th>
    <td align="left">
      <form:textarea name="categoryVO" property="header" cols="50" rows="10"/>
    </td>
  </tr>

  <tr>
    <td align="center" colspan="2">
      <form:submit property="submit" value="Submit"/>
      <form:reset/>
    </td>
  </tr>

</table>

</form:form>