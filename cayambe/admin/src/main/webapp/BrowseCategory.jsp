<%@ page language='java' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/cayambe.tld' prefix='cayambe' %>

<bean:define id="nodeId"  name="SelectedCategory" property="id" scope="request" type="java.lang.Long" />

<cayambe:categories topTreeName="Categories" selectedNodeId="<%=nodeId%>">
<table>
<cayambe:nodes>

<cayambe:node nodeType="expandingBranch" part="before">
  <tr>         
    <td valign="top" align="left">       

     <logic:notEqual parameter="nodeId" value="0">
      <html:link page="/BrowseCatalog.do" 
            paramId="categoryId" 
            paramName="node" 
            paramProperty="parentId"><img src="images/minus.gif" border="0">&nbsp;
      </html:link>
	 </logic:notEqual>

      <html:link page="/BrowseCatalog.do" 
            paramId="categoryId" 
            paramName="node" 
            paramProperty="nodeId"
            styleClass="normal">
        <logic:present name="isSelected" scope="page"><b></logic:present> 
         <bean:write name="node" property="nodeName" />
        <logic:present name="isSelected" scope="page"></b></logic:present> 
      </html:link> 
      <table width="100%">
</cayambe:node>

<cayambe:node nodeType="closedBranch">
        <tr>       
          <td valign="top" align="left">
            <html:link page="/BrowseCatalog.do" 
                 paramId="categoryId" 
                 paramName="node" 
                 paramProperty="nodeId"><img src="images/plus.gif" border=0>&nbsp;<bean:write name="node" property="nodeName" />
            </html:link>
          </td>
        </tr>
</cayambe:node>

<cayambe:node nodeType="leaf">
        <tr>
          <td valign="top" align="left">
            <html:link page="/BrowseCatalog.do" 
                 paramId="categoryId" 
                 paramName="node" 
                 paramProperty="nodeId">
              <logic:present name="isSelected" scope="page"><b></logic:present>
              <bean:write name="node" property="nodeName" />
              <logic:present name="isSelected" scope="page"></b></logic:present>  
             </html:link>
          </td>                
        </tr>
</cayambe:node>

<cayambe:node nodeType="expandingBranch" part="after">
       </table>
      </td>
    </tr>
</cayambe:node>

</cayambe:nodes>
</table>
</cayambe:categories>