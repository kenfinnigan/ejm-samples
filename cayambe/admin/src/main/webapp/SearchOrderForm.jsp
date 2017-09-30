<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<center>
<h3>Search Orders</h3>
<table border=1>

  <tr>
   <td><b>Order Statuses</b></td>

   <html:form name="EmptyForm" type="org.cayambe.web.form.EmptyActionForm" action="ListOrders.do" scope="request">


   <td>
    <select name="statusId">
    <logic:iterate id="s" name="statuses">
     <option value="<bean:write name="s" property="key"/>">
		<bean:write name="s" property="value"/>
    </logic:iterate> 
	</select>
   </td>
  </tr>

  <tr>
    <td align="center" colspan="2">
      <html:submit property="submit" value="Submit"/>
    </td>
  </tr>
  
  </html:form>

</table>