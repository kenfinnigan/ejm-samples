<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:errors />

<form:form 	name="ProductForm"  
           	type="org.cayambe.web.admin.form.ProductActionForm" 
		action="UpdateProduct.do" 	
		scope="request"
		focus="title">

<table border="0" width="100%">

  <form:hidden property="productId"/> 

  <tr>
    <th align="right">
      Product Title:
    </th>
    <td align="left">
      <form:text property="title"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Product Description:
    </th>
    <td align="left">
      <form:textarea property="desc"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Product Price:
    </th>
    <td align="left">
      <form:text property="price"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Product Image:
    </th>
    <td align="left">
      <form:text property="image"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Product SKU:
    </th>
    <td align="left">
      <form:text property="SKU"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Product Sale Price:
    </th>
    <td align="left">
      <form:text property="salePrice"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Product On Sale:
    </th>
    <td align="left">
      Yes <form:radio property="onSale" value="true"/>
      No <form:radio property="onSale" value="false"/>
    </td>
  </tr>

  
  <tr>
    <th align="right">
      Product Visible:
    </th>
    <td align="left">
      Yes <form:radio property="visible" value="true"/>
      No <form:radio property="visible" value="false"/>
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
