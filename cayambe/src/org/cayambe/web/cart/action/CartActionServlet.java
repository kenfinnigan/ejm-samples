package org.cayambe.web.cart.action;

import org.apache.struts.tiles.ActionComponentServlet;
import javax.servlet.UnavailableException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.IOException;
import java.util.*;
import org.cayambe.client.CategoryDelegate;
import org.cayambe.client.CartDelegate;
import org.cayambe.core.CategoryVO;
import org.apache.log4j.Category;


public class CartActionServlet extends ActionComponentServlet {
  CategoryDelegate cDelegate;
  final String CART_DELEGATE = "CartDelegate";
  private static String CLASSNAME = CartActionServlet.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException 
  {

     //lookup all of the categories, parentId may be null
     String categoryId = request.getParameter("CategoryId");
     if( categoryId == null || categoryId.length() < 1 ){
       categoryId = "0";
     }

     cDelegate = new CategoryDelegate();

     CategoryVO topLevelCategoryCriteria = new CategoryVO("0");
     cDelegate.listCategories(topLevelCategoryCriteria);
     List topLevelCategoryList = cDelegate.getCategoryList();
     request.setAttribute("topLevelCategoryList", topLevelCategoryList);

     CategoryVO currentLevelCategoryCriteria = new CategoryVO(categoryId);
     cDelegate.listCategories(currentLevelCategoryCriteria);
     List currentLevelCategoryList = cDelegate.getCategoryList();
     if (currentLevelCategoryList.size() > 0) { 
       request.setAttribute("currentLevelCategoryList", currentLevelCategoryList);
     }

     CategoryVO currentCategory = cDelegate.getCategory(currentLevelCategoryCriteria);
     request.setAttribute("currentCategory", currentCategory);
 
     CategoryVO currentCategoryHierarchy = currentCategory;
     LinkedList currentCategoryHierarchyList = new LinkedList();
     while (currentCategoryHierarchy.getId().longValue() > 0) {
        currentCategoryHierarchyList.add(0,currentCategoryHierarchy);
        currentCategoryHierarchy = cDelegate.getCategory(new CategoryVO(currentCategoryHierarchy.getParentId().toString()));
     }
     if (currentCategoryHierarchyList.size() > 0) { 
       request.setAttribute("currentCategoryHierarchyList", currentCategoryHierarchyList);
     }

      //get the cart
      CartDelegate cartDelegate;
      HttpSession session = request.getSession(true);
      cartDelegate = (CartDelegate)session.getAttribute(CART_DELEGATE);
      try {
        request.setAttribute("Cart", cartDelegate.getCart());
      }
      catch (Exception e) {
        cat.info("cartDelegate.getCart() threw this: " + e.getMessage());
      }

     super.doPost(request,response); 
  } 


  public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws SecurityException, IOException, ServletException 
  {
      doPost( request, response );
  } 

}
