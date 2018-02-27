<%@ page language='java' %>

<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-form.tld' prefix='form' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>

<table align="center" bgcolor="#ececec" width="100%" cellspacing="2" cellpadding="2" border="0">
<tr>
    <td align="center" valign="middle"><font face="Arial" size="+2" color="Navy">Cayambe Open Source Shopping Cart Reference Implementation</font></td>
</tr>
</table>
<FONT SIZE=5><B><CENTER>Check Out</CENTER></B></FONT>
[&nbsp;
<html:link page="/ContinueShopping.do"
  paramId="cartId" paramName="cartId" styleClass="normal" paramScope="session">
  Main Catalog
</html:link>
&nbsp;]&nbsp;
<BR><BR>
