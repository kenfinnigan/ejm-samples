<%@ page language="java" %>

<%@ page import="org.cayambe.core.CartVO" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<html:errors />

<form:form name="OrderForm" type="org.cayambe.web.form.OrderActionForm" action="SubmitOrder.do" scope="request">

<table border="1" width="100%">

  <tr>
    <th align="left" colspan="2">
      <u>Shipping Info</u>
    </th>
  </tr>

  <tr>
    <th align="right">
      Name:
    </th>
    <td align="left">
      <form:text property="shippingName" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Address 1:
    </th>
    <td align="left">
      <form:text property="shippingAddress" size="65"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Address 2:
    </th>
    <td align="left">
      <form:text property="shippingAddress2" size="65"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      City:
    </th>
    <td align="left">
      <form:text property="shippingCity" size="30"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      State:
    </th>
    <td align="left">
      <form:text property="shippingState" size="20"/>
    </td>
  </tr>
  
  <tr>
    <th align="right">
      Zip Code:
    </th>
    <td align="left">
      <form:text property="shippingZipCode" size="12"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Country:
    </th>
    <td align="left">
      <form:text property="shippingCountry" size="30"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Phone Number:
    </th>
    <td align="left">
      <form:text property="shippingPhoneNumber" size="20"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Shipping Method:
    </th>
    <td align="left">
      <form:text property="shippingMethod" size="20"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Shipping Amount:
    </th>
    <td align="left">
      <form:text property="shippingAmount" size="12"/>
    </td>
  </tr>
 
   <tr>
    <th align="right">
      Shipping Instructions:
    </th>
    <td align="left">
	  <form:textarea property="shippingInstructions" cols="25" rows="5"/>
    </td>
  </tr>



  <tr>
    <th align="left" colspan="2"><br></th>
  </tr>

  <tr>
    <th align="left" colspan="2">
      <u>Billing Info</u>
    </th>
  </tr>

  <tr>
    <th align="right">
      Name:
    </th>
    <td align="left">
      <form:text property="billingName" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Address 1:
    </th>
    <td align="left">
      <form:text property="billingAddress" size="65"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Address 2:
    </th>
    <td align="left">
      <form:text property="billingAddress2" size="65"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      City:
    </th>
    <td align="left">
      <form:text property="billingCity" size="30"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      State:
    </th>
    <td align="left">
      <form:text property="billingState" size="20"/>
    </td>
  </tr>
  
  <tr>
    <th align="right">
      Zip Code:
    </th>
    <td align="left">
      <form:text property="billingZipCode" size="12"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Country:
    </th>
    <td align="left">
      <form:text property="billingCountry" size="30"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Phone Number:
    </th>
    <td align="left">
      <form:text property="billingPhoneNumber" size="20"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Email:
    </th>
    <td align="left">
      <form:text property="billingEmail" size="20"/>
    </td>
  </tr>



  <tr>
    <th align="left" colspan="2"><br></th>
  </tr>

  <tr>
    <th align="left" colspan="2">
      <u>Credit Card Info</u>
    </th>
  </tr>
  
  <tr>
    <th align="right">
      Name On Card:
    </th>
    <td align="left">
      <form:text property="nameOnCard" size="16"/>
    </td>
  </tr>
  
  <tr>
    <th align="right">
      Credit Card Number:
    </th>
    <td align="left">
      <form:text property="cardNumber" size="16"/>
    </td>
  </tr>
 
  <tr>
    <th align="right">
      Credit Card Type:
    </th>
    <td align="left">
      <form:text property="cardType" size="16"/>
    </td>
  </tr>
  <tr>
    <th align="right">
      Credit Card Expiration Month:
    </th>
    <td align="left">
      <form:text property="cardExpirationMonth" size="2"/>
    </td>
  </tr>
 
  <tr>
    <th align="right">
      Credit Card Expiration Year:
    </th>
    <td align="left">
      <form:text property="cardExpirationYear" size="10"/>
    </td>
  </tr>

  <tr>
    <th align="left" colspan="2"><br></th>
  </tr>

  <tr>
    <td align="center" colspan="2">
      <form:submit property="submit" value="Submit"/>
      <form:reset/>
    </td>
  </tr>

</table>

</form:form>
