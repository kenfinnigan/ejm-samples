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
 * May 7th, 2002 
 */

public class ViewProductAction extends Action
{
  CategoryDelegate cDelegate;
  ProductDelegate delegate;
    
  private static String CLASSNAME = ViewProductAction.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public ActionForward perform( ActionMapping mapping, ActionForm actionForm,
      HttpServletRequest request, HttpServletResponse response )
      throws IOException, ServletException
  {
      
      ActionErrors errors = new ActionErrors();
      String forwardMapping = CayambeActionMappings.SUCCESS;
      cDelegate = new CategoryDelegate();

      //lookup all of the categories, parentId may be null
      String categoryId = request.getParameter("CategoryId");
      if( categoryId == null || categoryId.length() < 1 ){
        categoryId = "0";
      }

      request.setAttribute("CategoryId", categoryId);

      CategoryVO categoryListCriteria = new CategoryVO(categoryId);

      try {
        cDelegate.listCategories(categoryListCriteria);

        List categoryList = cDelegate.getCategoryList();
        request.setAttribute("CategoryList", categoryList);

        //set the selected category and the selected categories parent
        //TODO: Need a cleaner way to do this on the view
        CategoryVO selected = cDelegate.getCategory(categoryListCriteria);
        request.setAttribute("SelectedCategory", selected);
        request.setAttribute("SelectedCategoryParent",
        cDelegate.getCategory( new CategoryVO(selected.getParentId().toString())));
      }
      catch(Exception e) {
          //need to log the exception
          //set an error message to show to the view
          forwardMapping = CayambeActionMappings.FAILURE;
          errors.add( ActionErrors.GLOBAL_ERROR,
                      new ActionError("error.category.CategoryListError") );
      }

      ProductDelegate delegate = new ProductDelegate();            
      try {      
        ProductVO productVO = new ProductVO();
        productVO.setProductId(request.getParameter("productId"));
        productVO = delegate.getProduct(productVO);

        request.setAttribute("product", productVO);        
        
      }catch(Exception e){
          forwardMapping = CayambeActionMappings.FAILURE;
          errors.add( ActionErrors.GLOBAL_ERROR,
                      new ActionError("error.cart.PurgeCartError") );
      }

      return mapping.findForward( forwardMapping );
  }

}
