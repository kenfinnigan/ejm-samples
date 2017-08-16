package org.cayambe.web.admin.action;

import org.cayambe.util.*;
import org.cayambe.client.CategoryDelegate;
import org.cayambe.core.CategoryVO;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import org.apache.log4j.Category;
import org.cayambe.web.admin.form.*;

/**
 *
 * @author  jon rose <jonr@wantjava.com>
 * @version 0.1
 */

public class ListCategoriesAction extends Action {
    CategoryDelegate cDelegate;    

    private static String CLASSNAME = ListCategoriesAction.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);    

    public ActionForward perform( ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response )
        throws IOException, ServletException
    {   
		String forwardMapping = CayambeActionMappings.SUCCESS;
        ActionErrors errors = new ActionErrors();                
        try{

			ProcessRequest( form, request );

        }catch(Exception e){
            cat.info(e.getMessage());
            forwardMapping = CayambeActionMappings.FAILURE;
            errors.add( ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.category.CategoryListError") );
        }

        return mapping.findForward( forwardMapping );
    }


	public void ProcessRequest( ActionForm form, HttpServletRequest request)
        throws IOException, ServletException
    {        

        CategoryActionForm caf = (CategoryActionForm)form;
        cDelegate = new CategoryDelegate();

        //lookup all of the categories, parentId may be null
        String categoryId = caf.getCategoryId();		
		if( categoryId == null || categoryId.length() < 1 ){
          categoryId = "0";
        }
		cat.debug("The categoryId is: " + categoryId);
        
        CategoryVO categoryListCriteria = new CategoryVO(categoryId);
                
        try{

            cDelegate.listCategories(categoryListCriteria);
            List categoryList = cDelegate.getCategoryList();

            request.setAttribute("CategoryList", categoryList);

            //set the selected category and the selected categories parent
            //TODO: Need a cleaner way to do this on the view
            CategoryVO selected = cDelegate.getCategory(categoryListCriteria);
            request.setAttribute("SelectedCategory", selected);
            request.setAttribute("SelectedCategoryParent",
                cDelegate.getCategory( new CategoryVO(selected.getParentId().toString()) ));

			caf = (CategoryActionForm)form;
			caf.setForm(selected);
	        request.setAttribute("CategoryForm",caf);
	

        }catch(Exception e){
           e.printStackTrace();
        }
        
    }

}