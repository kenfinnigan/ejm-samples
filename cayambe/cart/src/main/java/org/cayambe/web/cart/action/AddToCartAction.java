package org.cayambe.web.cart.action;

import org.cayambe.client.*;
import org.cayambe.util.*;
import org.cayambe.core.*;
import org.cayambe.web.cart.form.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public class AddToCartAction extends Action
{
  CartDelegate delegate;
  final String CART_DELEGATE = "CartDelegate";

  public ActionForward perform( ActionMapping mapping, ActionForm actionForm,
      HttpServletRequest request, HttpServletResponse response )
      throws IOException, ServletException
  {
      ActionErrors errors = new ActionErrors();
      String forwardMapping = CayambeActionMappings.SUCCESS;

      CartDelegate delegate;
      HttpSession session = request.getSession(true);
      delegate = (CartDelegate)session.getAttribute(CART_DELEGATE);

      if( delegate == null ){
        delegate = new CartDelegate();
      }

      String productId = ((CartActionForm)actionForm).getProductId();
      try{
        delegate.addItem( productId );
        request.setAttribute("Cart", delegate.getCart());

        session.setAttribute(CART_DELEGATE, delegate);
      }catch(Exception e){
          forwardMapping = CayambeActionMappings.FAILURE;
          errors.add( ActionErrors.GLOBAL_ERROR,
                      new ActionError("error.cart.AddToCartError") );

      }
      return mapping.findForward( forwardMapping );
  }

}
