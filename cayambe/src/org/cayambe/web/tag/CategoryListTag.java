package org.cayambe.web.tag;
import org.cayambe.core.CategoryVO;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

/**
 * @author  Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public class CategoryListTag extends TagSupport
{
  List categoryList;

  public void setCategoryList( List _categoryList )
  {
    categoryList = _categoryList;
  }

  public int doStartTag()
  {
    try {
      JspWriter out = pageContext.getOut();
      out.println("<ul>");

      Iterator i = categoryList.iterator();
      while( i.hasNext() ){
        CategoryVO vo = (CategoryVO)i.next();
        out.print("<li><a href=\"BrowseCatalog.do?CategoryID=" + vo.getId() + "\">"
          + vo.getName() + "</a>");
      }

      out.println("");
      out.println("</ul>");
    }catch( Exception e ){
      e.printStackTrace();
    }
    return EVAL_BODY_INCLUDE;
  }
}