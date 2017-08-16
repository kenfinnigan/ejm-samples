package org.cayambe.web.checkout.action;

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

public class IndexAction extends Action
{
  
  private static String CLASSNAME = IndexAction.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public ActionForward perform( ActionMapping mapping, ActionForm actionForm,
      HttpServletRequest request, HttpServletResponse response )
      throws IOException, ServletException
  {

      ActionErrors errors = new ActionErrors();
      String forwardMapping = CayambeActionMappings.SUCCESS;      

	  String cartId = (String)request.getParameter("cartId");
	  HttpSession session = request.getSession(true);

	  try {

	    CartDelegate delegate = new CartDelegate( cartId );
            CartVO myCart = (CartVO)delegate.getCart();
            session.setAttribute("Cart", myCart);
	    session.setAttribute("cartId",cartId);
        
      }catch(Exception e){
          forwardMapping = CayambeActionMappings.FAILURE;
          errors.add( ActionErrors.GLOBAL_ERROR,
                      new ActionError("error.cart.UpdateCartError") );
      }      

      return mapping.findForward( forwardMapping );


  }

}
