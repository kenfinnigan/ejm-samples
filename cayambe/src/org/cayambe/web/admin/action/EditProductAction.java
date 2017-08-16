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
import org.cayambe.web.admin.form.ProductActionForm;
import org.cayambe.core.ProductVO;
import org.cayambe.util.CayambeActionMappings;
import org.cayambe.client.ProductDelegate;
import org.apache.log4j.Category;

public final class EditProductAction extends Action {

    private static String CLASSNAME = EditProductAction.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {

		String worked = CayambeActionMappings.SUCCESS;

        ProductActionForm paf = (ProductActionForm)form;

	    try {
	      ProductVO p; 
          ProductDelegate pd = new ProductDelegate();
	      p = pd.getProduct(paf.getProductId());
	      paf.setForm(p);
	      request.setAttribute("ProductForm",paf);
	    } catch (Exception e){
	      cat.info(e.getMessage());
	    }

        return (mapping.findForward(worked));

    }

}
