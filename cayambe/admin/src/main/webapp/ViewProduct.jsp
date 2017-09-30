<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<center>
<h3>View Product</h3>

<table>

 <tr>
  <td><b>Title</b></td>
  <td><bean:write name="ProductForm" property="title" /></td>
 </tr>

 <tr>
  <td><b>Description</b></td>
  <td><bean:write name="ProductForm" property="desc" /></td>
 </tr>

 <tr>
  <td><b>Price</b></td>
  <td><bean:write name="ProductForm" property="dollarPrice" /></td>
 </tr>
 
 <tr>
  <td><b>Image</b></td>
  <td><bean:write name="ProductForm" property="image" /></td>
 </tr>
 
 <tr>
  <td><b>SKU</b></td>
  <td><bean:write name="ProductForm" property="SKU" /></td>
 </tr>

 <tr>
  <td><b>Sale Price</b></td>
  <td><bean:write name="ProductForm" property="dollarSalePrice" /></td>
 </tr>

 <tr>
  <td><b>Product On Sale</b></td>
  <td><bean:write name="ProductForm" property="onSale" /></td>
 </tr>
 
 <tr>
  <td><b>Product Visible</b></td>
  <td><bean:write name="ProductForm" property="visible" /></td>
 </tr>

 </table>