package org.cayambe.web.cart.action;

import org.cayambe.client.*;
import org.cayambe.util.*;
import org.cayambe.core.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import org.apache.log4j.Category;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 * May 6th, 2002 
 */

public class ViewCartAction extends Action
{
  
  private static String CLASSNAME = ViewCartAction.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public ActionForward perform( ActionMapping mapping, ActionForm actionForm,
      HttpServletRequest request, HttpServletResponse response )
      throws IOException, ServletException
  {

      return mapping.findForward( CayambeActionMappings.SUCCESS );

  }

}
