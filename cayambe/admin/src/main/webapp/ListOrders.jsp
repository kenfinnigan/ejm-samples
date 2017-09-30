<%@ page import="org.cayambe.core.*" %> 
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<center>
<h3>Orders</h3>
<table border=1>

 <tr>
  <td><b>Order Id</b></td>
  <td><b>Billing Name</b></td>
  <td><b>View</td>
 </tr>


 <logic:iterate id="o" scope="request" name="orders" type="org.cayambe.core.OrderVO">

  <tr>
   <td>
    <bean:write name="o" property="orderId"/>
   </td>
   <td>
    <bean:write name="o" property="billingInfoVO.name"/>
   </td>
   <td>

	 <html:link page="/ViewOrder.do" 
          paramId="orderId" paramName="o" paramProperty="orderId"		
          styleClass="normal">
		  View	      
     </html:link>   
   </td>
  </tr>

 </logic:iterate> 

</table>