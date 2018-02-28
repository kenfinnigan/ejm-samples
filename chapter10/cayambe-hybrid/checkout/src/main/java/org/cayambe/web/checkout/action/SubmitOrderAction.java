package org.cayambe.web.checkout.action;

import org.cayambe.client.*;
import org.cayambe.util.*;
import org.cayambe.core.*;
import org.cayambe.web.checkout.action.payments.PaymentRequest;
import org.cayambe.web.checkout.action.payments.PaymentResponse;
import org.cayambe.web.checkout.action.payments.PaymentService;
import org.cayambe.web.form.OrderActionForm;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import org.apache.log4j.Category;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


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

	    // Call Payment Service
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://cayambe-payment-service-myproject.192.168.64.33.nip.io");
        PaymentService paymentService = target.proxy(PaymentService.class);
        PaymentResponse paymentResponse = paymentService.charge(new PaymentRequest()
                              .amount((long) (orderVO.getCartVO().getTotalCost() * 100))
                              .cardToken(oaf.getCardToken())
                              .orderId(Math.toIntExact(orderVO.getOrderId()))
        );

        orderVO.getBillingInfoVO().setCardChargeId(paymentResponse.getChargeId());

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
