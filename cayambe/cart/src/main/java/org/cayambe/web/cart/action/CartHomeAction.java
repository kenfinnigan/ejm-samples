/*
 * ListCategoryAction.java
 *
 * Created on September 19, 2001, 10:43 PM
 */

package org.cayambe.web.cart.action;
import org.cayambe.client.*;
import org.cayambe.util.*;
import org.cayambe.core.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author  Administrator
 * @version 0.1
 */
public class CartHomeAction extends Action {
    CategoryDelegate cDelegate;
    ProductDelegate  pDelegate;

    public ActionForward perform( ActionMapping mapping, ActionForm actionForm,
        HttpServletRequest req, HttpServletResponse res )
        throws IOException, ServletException
    {

        ActionErrors errors = new ActionErrors();
        String forwardMapping = "success";
        cDelegate = new CategoryDelegate();

        //lookup all of the categories, parentId may be null
        String categoryId = req.getParameter("CategoryId");
        if( categoryId == null || categoryId.length() < 1 ){
          categoryId = "0";
        }
        
        CategoryVO categoryListCriteria = new CategoryVO(categoryId);

        try{
            cDelegate.listCategories(categoryListCriteria);

            List categoryList = cDelegate.getCategoryList();
            req.setAttribute("CategoryList", categoryList);

            //set the selected category and the selected categories parent
            //TODO: Need a cleaner way to do this on the view
            CategoryVO selected = cDelegate.getCategory(categoryListCriteria);
            req.setAttribute("SelectedCategory", selected);
            req.setAttribute("SelectedCategoryParent",
                cDelegate.getCategory( new CategoryVO(selected.getParentId().toString()) ));
        }catch(Exception e){
            //need to log the exception
            //set an error message to show to the view
            forwardMapping = CayambeActionMappings.FAILURE;
            errors.add( ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.category.CategoryListError") );
        }

        //lookup all of the products under the selected category
        if( categoryId != null ){
            pDelegate = new ProductDelegate();
            try{
                pDelegate.listProductsInCategory(categoryListCriteria);
                //send the list of products to the view
                List productList = pDelegate.getProductList();

                req.setAttribute("ProductList", productList);
            }catch(Exception e){
                //need to log the exception
                //set an error message to show to the view
                forwardMapping = CayambeActionMappings.FAILURE;
                errors.add( ActionErrors.GLOBAL_ERROR,
                            new ActionError("error.categoryProductListError") );
            }
        }

        return mapping.findForward( forwardMapping );
    }

}
