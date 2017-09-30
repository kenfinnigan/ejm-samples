<%@ page language='java' %>

<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-form.tld' prefix='form' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>


<table border=0 cellspacing=0 cellpadding=0>
  <tr valign=bottom align=center>
   <td>
     <img src="http://g-images.amazon.com/images/G/01/v9/search-browse/browse-gateway.gif" width=171 height=19 border=0>
    </td>
  </tr> 
  <tr valign=top align=center>
   <td> 
     <table border=0 width= 171 cellpadding=1 cellspacing=0 bgcolor=#708090 >
       <tr> 
         <td width=100%>
           <table width=100% border=0 cellpadding=4 cellspacing=0 bgcolor=#708090>
             <tr> 
               <td bgcolor=#ffffff valign=top width=100%>
                 <table cellpadding=3 cellspacing=0>
                   <logic:present scope="request" name="currentLevelCategoryList">
                     <logic:iterate id="category" scope="request" name="currentLevelCategoryList">
                       <tr>
                          <td>
                            &#149;&nbsp;<b>
                            <html:link page="ListProductsInCategory.do" 
                                       paramId="CategoryId" paramName="category" paramProperty="id">
                            <bean:write name="category" property="name"/>
                            </html:link>
                            </b>
                          </td>
                       </tr>
                     </logic:iterate>
                   </logic:present>
                   <logic:present scope="request" name="topLevelCategoryList">
                     <tr>
                       <td>
                         <form action="/ListProductsInCategory.do" method="POST">
                         <select name="CategoryId">
                           <option value="0">Jump To:
                           <logic:iterate id="category" scope="request" name="topLevelCategoryList">
                             <option value='<bean:write name="category" property="id"/>'>
                                            <bean:write name="category" property="name"/>
                           </logic:iterate>
                         </select>
                         <html:image src="http://g-images.amazon.com/images/G/01/v9/search-browse/go-button-gateway.gif" align="absmiddl" border="0"/>
                         </form>
                       </td>
                     </tr>
                   </logic:present>
                   <logic:present scope="request" name="Cart">
                     <tr>
                       <td>
                         <html:link page="ViewCart.do">View Cart</html:link>
                       </td>
                     </tr>
                   </logic:present>
                 </table>
               </td> 
             </tr> 
           </table>
         </td> 
       </tr> 
     </table> 
   </td>
 </tr>
</table>
