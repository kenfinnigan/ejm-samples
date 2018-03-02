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

public class UpdateCartAction extends Action
{
  CartDelegate delegate;
  final String CART_DELEGATE = "CartDelegate";
  
  private static String CLASSNAME = UpdateCartAction.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public ActionForward perform( ActionMapping mapping, ActionForm actionForm,
      HttpServletRequest request, HttpServletResponse response )
      throws IOException, ServletException
  {
      cat.debug("In UpdateCartAction");
      ActionErrors errors = new ActionErrors();
      String forwardMapping = CayambeActionMappings.SUCCESS;
      

      CartDelegate delegate;
      HttpSession session = request.getSession(true);
      delegate = (CartDelegate)session.getAttribute(CART_DELEGATE);
      try {

      if( delegate == null ){
        delegate = new CartDelegate();
      }

        String productIds[] = request.getParameterValues("productId");
        for (int i=0; i < productIds.length; i++) {
          String productId = productIds[i];
          cat.debug("Product Id: " + productId);
          delegate.updateQuantity( productId, 
            Integer.parseInt(request.getParameter(productId + "quantity" )));            
          if (request.getParameter(productId + "remove" ) != null) {
            cat.debug("Removing item: " + productId);
            delegate.removeItem( productId);
          }
        }

        if (delegate.getCart().getSize() == 0) {
          delegate = null;
          request.setAttribute("Cart", null);
          session.setAttribute(CART_DELEGATE, null);
        }
        else {
          request.setAttribute("Cart", delegate.getCart());
          session.setAttribute(CART_DELEGATE, delegate);
        }
        
      }catch(Exception e){
          forwardMapping = CayambeActionMappings.FAILURE;
          errors.add( ActionErrors.GLOBAL_ERROR,
                      new ActionError("error.cart.UpdateCartError") );

      }

      return mapping.findForward( forwardMapping );
  }

}
