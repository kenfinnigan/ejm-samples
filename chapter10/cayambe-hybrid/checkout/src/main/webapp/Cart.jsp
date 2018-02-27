<%@ page language='java' %>

<%@ page import="org.cayambe.core.*" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Iterator" %>

<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-form.tld' prefix='form' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>

<logic:present name="Cart" >

  <bean:define id="cart" name="Cart" type="CartVO" />
  <bean:define id="cost" name="cart" property="totalCost" scope="page"/>
  <bean:define id="itemCount" name="cart" property="size" scope="page"/>
  <%        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US); %>

  <table border="1">
    <tr>                
      <td align="center" valign="top"><b>Title</b></td>
      <td align="center" valign="top"><b>Price</b></td>
      <td align="center" valign="top"><b>Quantity</b></td>
      <td align="center" valign="top"><b>Total</b></td>
    </tr>
    <bean:define id="items" name="cart" property="cartItems" scope="page"/>
    <logic:iterate id="item" scope="page" name="items" type="CartItemVO">
      <bean:define id="product" name="item" type="ProductVO" property="product"/>
      <%
        double price = product.isOnSale()?product.getSalePrice():product.getPrice();
        double total = price * item.getQuantity();
      %>
      <tr>                
        <td align="left" valign="top">
          <bean:write name="product" property="title"/>
        </td>
        <td align="left" valign="top">
          <%= nf.format(price) %>
        </td>
        <td align="left" valign="top">
          <bean:write name="item" property="quantity"/>
        </td>
        <td align="left" valign="top">
          <%= nf.format(total) %>
        </td>
      </tr>        
    </logic:iterate>
    <tr bgcolor="#CCCCCC">
      <td align=left colspan=3>
        <B>Cart Total</B>
      </td>
      <td>
        <B><%= nf.format(cost) %></B>
      </td>
    </tr>
  </table>
</logic:present>
<logic:notPresent name="Cart" >
  Your Cart is empty
</logic:notPresent>
