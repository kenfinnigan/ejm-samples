<%@ page language='java' %>
<%@ page import='org.cayambe.core.CategoryVO' %>
<%@ taglib uri='/WEB-INF/struts-logic.tld' prefix='logic' %>
<%@ taglib uri='/WEB-INF/struts-bean.tld' prefix='bean' %>
<%@ taglib uri='/WEB-INF/struts-html.tld' prefix='html' %>
<%@ taglib uri='/WEB-INF/cayambe.tld' prefix='cayambe' %>

  <table border=0 cellspacing=0 cellpadding=0>
    <tr valign=bottom align=center>
      <td>
        <img src="http://g-images.amazon.com/images/G/01/v9/search-browse/search-gateway.gif" width=171 height=19 border=0 alt="Search">
      </td>
    </tr> 
    <tr valign=top align=center>
      <td>
      <table border=0 width=171 cellpadding=1 cellspacing=0 bgcolor=#708090 >
        <tr>
          <td width=100%>
          <table width=100% border=0 cellpadding=4 cellspacing=0 bgcolor=#708090>
            <tr>
              <td bgcolor=#FFCC66 valign=top width=100%>
              <form method="post" action="/SearchProducts.do" name="searchform">
              <input type="text" name="SearchText" size="15">
              <input type="image" height="21" width="21" border=0 value="Go" name="Go" src="http://g-images.amazon.com/images/G/01/v9/search-browse/go-button-gateway.gif" align=absmiddle>
              </form>
              </td>
            </tr>
          </table>
          </td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
