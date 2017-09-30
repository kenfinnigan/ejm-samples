<%@ page language='java' %>

<%@ page import="org.cayambe.core.*" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Iterator" %>

<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-form.tld' prefix='form' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>

<logic:present name="Cart" scope="request" >

  <bean:define id="cart" name="Cart" type="CartVO" scope="request" />
  <bean:define id="cost" name="cart" property="totalCost" scope="page"/>
  <bean:define id="itemCount" name="cart" property="size" scope="page"/>
  <%        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US); %>

  <table border="1">
    <tr>                
      <td align="center" valign="top"><b>Remove</b></td>
      <td align="center" valign="top"><b>Title</b></td>
      <td align="center" valign="top"><b>Price</b></td>
      <td align="center" valign="top"><b>Quantity</b></td>
      <td align="center" valign="top"><b>Total</b></td>
    </tr>
    <form action="/UpdateCart.do" method="POST">
    <bean:define id="items" name="cart" property="cartItems" scope="page"/>
    <logic:iterate id="item" scope="page" name="items" type="CartItemVO">
      <bean:define id="product" name="item" type="ProductVO" property="product"/>
      <%
        double price = product.isOnSale()?product.getSalePrice():product.getPrice();
        double total = price * item.getQuantity();
      %>
      <form:hidden name="product" property="productId" />
      <tr>                
        <td align="left" valign="top">
          <input type="Checkbox" name="<bean:write name="product" property="productId"/>remove" value="true">
        </td>
        <td align="left" valign="top">
          <bean:write name="product" property="title"/>
        </td>
        <td align="left" valign="top">
          <%= nf.format(price) %>
        </td>
        <td align="left" valign="top">
          <input type="text" name="<bean:write name="product" property="productId"/>quantity" value="<bean:write name="item" property="quantity"/>" size="3" maxlength="5">
        </td>
        <td align="left" valign="top">
          <%= nf.format(total) %>
        </td>
      </tr>        
    </logic:iterate>
    <tr bgcolor="#CCCCCC">
      <td align=left colspan=4>
        <B>Cart Total</B>
      </td>
      <td>
        <B><%= nf.format(cost) %></B>
      </td>
    </tr>
    <tr>
      <td align=center colspan=5>
        <table border="0">
          <tr>
            <td align="center" width=50%>
              <form:submit property="submit" value="Update Cart"/>
            </td>
    </form>
            <td align="center" width=50%>
              <form method="POST" action="/CheckOut.do">
                <html:submit property="submit" value="Checkout"/>
              </form>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</logic:present>
<logic:notPresent name="Cart" scope="request" >
  Your Cart is empty
</logic:notPresent>
