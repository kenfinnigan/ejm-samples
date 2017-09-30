<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:greaterThan name="CategoryForm" property="categoryId" value="0">

<center>
<h1>Manage Category - <bean:write name="CategoryForm" property="name"/></h1>

<table border=0 width="500">

   <tr>
    <td CLASS="small" align="center"> 

	   
       [&nbsp;<html:link page="/NewCategory.do?manageCategoryPage=AddSubCategoryForm.jsp" 
             paramId="categoryId" paramName="CategoryForm" paramProperty="categoryId"		
             styleClass="normal">Add Sub Category</html:link>&nbsp;]&nbsp;

	   [&nbsp;<html:link page="/EditCategory.do?manageCategoryPage=UpdateCategoryForm.jsp" 
             paramId="categoryId" paramName="CategoryForm" paramProperty="categoryId"		
             styleClass="normal">Edit</html:link>&nbsp;]&nbsp;

       [&nbsp;<html:link page="/RemoveCategory.do" 
             paramId="categoryId" paramName="CategoryForm" paramProperty="categoryId"		
             styleClass="normal">Remove</html:link>&nbsp;]&nbsp;

       [&nbsp;<html:link page="/SearchProductForAddingToCategory.do"
              paramId="categoryId" paramName="CategoryForm" paramProperty="categoryId"		
              styleClass="normal">Add Product</html:link>&nbsp;]&nbsp;

       [&nbsp;<html:link page="/ListProductForCategory.do?manageCategoryPage=CategoryListProduct.jsp"
              paramId="categoryId" paramName="CategoryForm" paramProperty="categoryId"		
              styleClass="normal">List Product</html:link>&nbsp;]

    </td>
   </tr>

</table>
<br><br>

</logic:greaterThan>


<logic:equal name="CategoryForm" property="categoryId" value="0">

       <html:link page="/NewCategory.do?manageCategoryPage=AddSubCategoryForm.jsp" 
             paramId="categoryId" paramName="CategoryForm" paramProperty="categoryId"		
             styleClass="normal">Add New Category</html:link>

</logic:equal>