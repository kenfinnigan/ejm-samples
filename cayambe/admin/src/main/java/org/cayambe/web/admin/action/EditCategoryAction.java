/*
 * EditCategoryAction.java
 *
 * Created on September 22, 2001, 1:31 AM
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
import org.cayambe.web.admin.form.CategoryActionForm;
import org.cayambe.core.CategoryVO;
import org.cayambe.util.CayambeActionMappings;
import org.cayambe.client.CategoryDelegate;
import org.apache.log4j.Category;

public final class EditCategoryAction extends Action  {

    private static String CLASSNAME = EditCategoryAction.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {
	 
	    String worked = CayambeActionMappings.SUCCESS;

	    ListCategoriesAction lca = new ListCategoriesAction();
		lca.ProcessRequest( form, request );

     	try {
          CategoryActionForm caf = (CategoryActionForm)form;
	      CategoryVO c = new CategoryVO(); 
	      if (caf.getCategoryId() != null && caf.getCategoryId().length() > 0) {
	        c.setId(new Long(caf.getCategoryId()));
 	      }
	      if (caf.getParentId() == null && caf.getParentId().length() == 0) {
	        request.setAttribute("parentId",c.getId().toString());
	      }

          CategoryDelegate cd = new CategoryDelegate();
	      c = cd.getCategory(c);
	      caf.setForm(c);
	      request.setAttribute("CategoryForm",caf);

	    } catch (Exception e){
	      cat.info(e.getMessage());
	    }

        return (mapping.findForward(worked));

    }


}
