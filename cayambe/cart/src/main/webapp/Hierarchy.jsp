<%@ page language='java' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>


 <logic:present scope="request" name="currentCategoryHierarchyList">
	<html:link page="ListProductsInCategory.do?CategoryId=0">Home</html:link>

   <logic:iterate id="category" scope="request" name="currentCategoryHierarchyList">
     
	 &nbsp;&gt;&nbsp;
	 <html:link page="ListProductsInCategory.do" 
		paramId="CategoryId" paramName="category" paramProperty="categoryId">
		<bean:write name="category" property="name"/>
 	 </html:link>

   </logic:iterate>
</logic:present>
