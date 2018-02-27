<%@ page language='java' %>
<%@ page import='org.cayambe.core.ProductVO' %>

<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>

<center>

  <table width="100%" border="0" cellspacing="2" cellpadding="2" align="CENTER" valign="top">

  <logic:notEmpty scope="request" name="ProductList"> 
	<tr>
		<td align="center" valign="top" bgcolor="#cecece"><b>Title</b></td>
		<td align="center" valign="top" bgcolor="#cecece"><b>Description</b></td>
		<td align="center" valign="top" bgcolor="#cecece"><b>Price</b></td>
		<td align="center" valign="top" bgcolor="#cecece"><b>SKU</b></td>
	</tr>
  </logic:notEmpty>


 <logic:iterate id="product" scope="request" name="ProductList">
        
	<tr>
		<td align="left" valign="top" bgcolor="#ececec">
             <a href='ViewProduct.do?productId=<bean:write name="product" property="productId"/>&CategoryId=<bean:write name="product" property="categoryId" />'>
              <bean:write name="product" property="title"/>
             </a>

        </td>
		<td align="left" valign="top" bgcolor="#ececec">
			<bean:write name="product" property="desc"/>
		</td>


		<logic:equal name="product" property="onSale" scope="page" value="true">
		  <td align="left" valign="top" bgcolor="#ececec">
            <bean:write name="product" property="dollarSalePrice"/> - <font color="red">Sale</font>
	      </td>
		</logic:equal>
		<logic:notEqual name="product" property="onSale" scope="page" value="true">
         <td align="left" valign="top" bgcolor="#ececec">
		    <bean:write name="product" property="dollarPrice"/>
		 </td>
		</logic:notEqual>
		<td align="left" valign="top" bgcolor="#ececec">
		  <bean:write name="product" property="SKU"/>
		</td>

	</tr>
 </logic:iterate> 

</table>