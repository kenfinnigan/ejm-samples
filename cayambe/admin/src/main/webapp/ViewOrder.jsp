<%@ page language="java" %>

<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<%
	NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
%>

<table border="0">

  <tr>
    <th align="left" colspan="2">
      <u>Shipping Info</u>
    </th>
  </tr>

  <tr>
    <td align="left">
      Name:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingName" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Address 1:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingAddress" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Address 2:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingAddress2" />
    </td>
  </tr>

  <tr>
    <td align="left">
      City:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingCity" />
    </td>
  </tr>

  <tr>
    <td align="left">
      State:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingState" />
    </td>
  </tr>
  
  <tr>
    <td align="left">
      Zip Code:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingZipCode" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Country:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingCountry" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Phone Number:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingPhoneNumber" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Shipping Method:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingMethod" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Shipping Amount:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingAmount" />
    </td>
  </tr>
 
  <tr>
    <td align="left">
      Shipping Instructions:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="shippingInstructions" />
    </td>
  </tr>



  <tr>
    <td align="left" colspan="2"><br></td>
  </tr>

  <tr>
    <th align="left" colspan="2">
      <u>Billing Info</u>
    </th>
  </tr>

  <tr>
    <td align="left">
      Name:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingName" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Address 1:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingAddress" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Address 2:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingAddress2" />
    </td>
  </tr>

  <tr>
    <td align="left">
      City:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingCity" />
    </td>
  </tr>

  <tr>
    <td align="left">
      State:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingState" />
    </td>
  </tr>
  
  <tr>
    <td align="left">
      Zip Code:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingZipCode" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Country:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingCountry" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Phone Number:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingPhoneNumber" />
    </td>
  </tr>

  <tr>
    <td align="left">
      Email:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="billingEmail" />
    </td>
  </tr>



  <tr>
    <td align="left" colspan="2"><br></td>
  </tr>

  <tr>
    <th align="left" colspan="2">
      <u>Credit Card Info</u>
    </th>
  </tr>
  
  <tr>
    <td align="left">
      Name On Card:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="nameOnCard" />
    </td>
  </tr>
  
  <tr>
    <td align="left">
      Credit Card Number:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="cardNumber" />
    </td>
  </tr>
 
  <tr>
    <td align="left">
      Credit Card Type:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="cardType" />
    </td>
  </tr>
  <tr>
    <td align="left">
      Credit Card Expiration Month:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="cardExpirationMonth" />
    </td>
  </tr>
 
  <tr>
    <td align="left">
      Credit Card Expiration Year:
    </td>
    <td align="left">
	  <bean:write name="OrderForm" property="cardExpirationYear" />
    </td>
  </tr>

  <tr>
    <td align="left" colspan="2"><br></td>
  </tr>

  <tr>
    <th align="left" colspan="2">
      <u>Order Summary</u>
    </th>
  </tr>

<%--
  <tr>
    <td align="left">
      Total:
    </td>
    <td align="left">
       nf.format( cart.getTotalCost() ) 
    </td>
  </tr>
--%>

</table>