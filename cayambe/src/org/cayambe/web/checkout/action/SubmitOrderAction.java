package org.cayambe.web.checkout.action;

import org.cayambe.client.*;
import org.cayambe.util.*;
import org.cayambe.core.*;
import org.cayambe.web.form.OrderActionForm;
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

public class SubmitOrderAction extends Action
{

  CheckOutDelegate delegate;
  
  private static String CLASSNAME = SubmitOrderAction.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public ActionForward perform( ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response )
      throws IOException, ServletException
  {

      ActionErrors errors = new ActionErrors();
      String forwardMapping = CayambeActionMappings.SUCCESS;      

      HttpSession session = request.getSession(true);

      OrderActionForm oaf = (OrderActionForm)form;

      try {

	    delegate = new CheckOutDelegate();
		OrderVO orderVO = new OrderVO();
	    orderVO = (OrderVO)oaf.toOrderVO();
	    orderVO.setCartVO( (CartVO) session.getAttribute("Cart") );
	    delegate.Save ( orderVO );

		CartDelegate cartDelegate = new CartDelegate();
		cartDelegate.Remove( orderVO.getCartVO() );
        
      } catch(Exception e) {
        forwardMapping = CayambeActionMappings.FAILURE;
        errors.add( ActionErrors.GLOBAL_ERROR, new ActionError("error.cart.UpdateCartError") );
      }      

      return mapping.findForward( forwardMapping ); 

  }

}
