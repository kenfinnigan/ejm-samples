/*
 * CategoryDelegate.java
 *
 * Created on September 19, 2001, 10:48 PM
 */

package org.cayambe.client;

import java.util.List;
import java.util.Hashtable;
import org.cayambe.ejb.*;
import org.cayambe.core.CategoryVO;
import org.cayambe.util.*;
import org.apache.log4j.Category;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * anatr:16.10: added getAllCategories() for taglib usage
 *
 * @author  Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */
public class CategoryDelegate {
    CategoryFacade facade;
    private static String CLASSNAME = CategoryDelegate.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public CategoryDelegate()
    {
      try{
        CategoryFacadeHome home = (CategoryFacadeHome)CayambeServiceLocator.getInstance()
                                  .getHome( "CategoryFacade", CategoryFacadeHome.class );
        facade = (CategoryFacade)home.create();
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    /** searches for categories */
    public void listCategories( CategoryVO listCriteria )
//        throws CategoryException
    {
      try{
        facade.listCategories(listCriteria);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public int getListSize()
        //throws CategoryException
    {
      int listSize = 0;
      try{
        listSize = facade.getListSize();
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return listSize;
    }

    public List getCategoryList()
        //throws CategoryException
    {
      List catList = null;
      try{
        catList = facade.getCategoryList();
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return catList;
    }

    public CategoryVO getCategoryHierarchy( CategoryVO selected )
    {
      CategoryVO hierarchy = null;
      try{
        hierarchy = facade.getCategoryHierarchy( selected );
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return hierarchy;
    }

    public CategoryVO getCategory(CategoryVO c)
    {
      CategoryVO categoryVO = null;
      try{
        categoryVO = facade.getCategory(c);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return categoryVO;
    }

    public Hashtable getAllCategories()
    {
      Hashtable categoriesHT = null;
      try{
        categoriesHT = facade.getAllCategories();
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return categoriesHT;
    }

}
