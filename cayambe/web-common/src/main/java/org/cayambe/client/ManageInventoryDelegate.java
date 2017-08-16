/*
 * ManageInventoryDelegate.java
 *
 * Created on September 19, 2001, 10:48 PM
 */

package org.cayambe.client;
import java.util.List;
import org.cayambe.ejb.*;
import org.cayambe.core.ProductVO;
import org.cayambe.core.CategoryVO;
import org.apache.log4j.Category;
import org.cayambe.util.*;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */
public class ManageInventoryDelegate {
    ProductFacade productFacade;
    CategoryFacade categoryFacade;
    
    private static String CLASSNAME = ManageInventoryDelegate.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public ManageInventoryDelegate()
 	throws Exception {
      try{
        ProductFacadeHome productHome = (ProductFacadeHome)CayambeServiceLocator.getInstance()
				.getHome( "ProductFacade", ProductFacadeHome.class );

        productFacade = (ProductFacade)productHome.create();

        CategoryFacadeHome categoryHome = (CategoryFacadeHome)CayambeServiceLocator.getInstance()
                                .getHome( "CategoryFacade", CategoryFacadeHome.class );

        categoryFacade = (CategoryFacade)categoryHome.create();

      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
	throw e;
      }
    }

    public void UpdateProduct( ProductVO p)
    {
      try{
        productFacade.UpdateProduct(p);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public void RemoveProduct( ProductVO p)
    {
      try{
        productFacade.RemoveProduct(p);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public void UpdateCategory( CategoryVO c)
    {
      try{
        categoryFacade.UpdateCategory(c);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public void RemoveCategory( CategoryVO c)
    {
      try{
        categoryFacade.RemoveCategory(c);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public void RemoveProductsFromCategory( CategoryVO c)
    {
      try{
        categoryFacade.RemoveProductsFromCategory(c);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }


    public void SaveProductToCategory(CategoryVO c, ProductVO p) 
    {
      try{
        categoryFacade.SaveProductToCategory(c,p);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }

    }

}