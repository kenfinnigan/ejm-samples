/*
 * ListOrdersAction.java
 *
 * Created on June 10th, 2002, 1:31 AM
 */

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
import org.cayambe.core.ProductVO;
import org.cayambe.util.CayambeActionMappings;
import org.cayambe.client.OrderDelegate;
import java.util.*;
import org.apache.log4j.Category;

public final class ListOrdersAction extends Action {

    private static String CLASSNAME = ListOrdersAction.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);


    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {

	    String worked = CayambeActionMappings.SUCCESS;

		String statusId = request.getParameter("statusId");

		OrderDelegate od = new OrderDelegate();

	    try {
	      od.ListOrdersByStatus( statusId );
	    } catch (Exception e){
	      cat.info(e.getMessage());
	    }

        request.setAttribute("orders",od.getOrderList());

        return (mapping.findForward(worked));

    }

}