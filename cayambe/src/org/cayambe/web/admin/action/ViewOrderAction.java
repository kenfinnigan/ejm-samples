package org.cayambe.web.admin.action;

/**
 *
 * @author  jon rose
 * @version 
 */

import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.cayambe.web.form.OrderActionForm;
import org.cayambe.core.OrderVO;
import org.cayambe.client.OrderDelegate;
import org.cayambe.util.CayambeActionMappings;
import org.apache.log4j.Category;

public final class ViewOrderAction extends Action {

    private static String CLASSNAME = ViewOrderAction.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);


    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {

        OrderActionForm oaf = new OrderActionForm();

        String worked = CayambeActionMappings.SUCCESS;
    	try {
	      OrderVO o = new OrderVO(); 

          OrderDelegate od = new OrderDelegate();
		  o.setOrderId ( request.getParameter("orderId") );
	      o = od.getOrderVO(o);
	      oaf.setForm( o );
	      request.setAttribute("OrderForm",oaf);

	    } catch (Exception e){
	      cat.info(e.getMessage());
	    }

        return (mapping.findForward(worked));

    }

}