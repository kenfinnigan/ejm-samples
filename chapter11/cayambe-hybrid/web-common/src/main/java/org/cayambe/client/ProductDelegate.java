/*
 * ProductDelegate.java
 *
 * Created on September 19, 2001, 10:48 PM
 */

package org.cayambe.client;
import java.util.List;
import java.util.Collection;
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
 * @author  Jon Rose<jonr@wantjava.com>
 * @version 0.1
 */
public class ProductDelegate {
   ProductFacade facade;
   
   private static String CLASSNAME = ProductDelegate.class.getName();
   private Category cat = (Category)Category.getInstance(CLASSNAME);
   

   public ProductDelegate()
   {
     try{
       ProductFacadeHome home = (ProductFacadeHome)CayambeServiceLocator.getInstance()
                                 .getHome( "ProductFacade", ProductFacadeHome.class );
       facade = (ProductFacade)home.create();
     }catch(Exception e){
       e.printStackTrace();
       cat.info(e.getMessage());       
     }
   }

   public void SearchProduct (ProductVO p) {
      try {
        facade.SearchProduct(p);		
      }catch(Exception e){
        cat.info("<-><->" + e.getMessage());        
        e.printStackTrace();
      }
   }

   public void listProductsInCategory( CategoryVO category )
   {
      try {
        facade.listProductsInCategory(category);
      }catch(Exception e){
        cat.info(e.getMessage());
        e.printStackTrace();
      }
   }

   public List getProductList () {
      List list = null;
      try {
        list = (List)facade.getProductList();
        cat.debug("ProductDelegate.getProductList(): size: "
          + list.size());
      }catch(Exception e){
        cat.info(e.getMessage());
        e.printStackTrace();
      }
      return list;
   }

    public ProductVO getProduct( String productId)
    {
      ProductVO p = null;
      try{
        p = facade.getProduct(productId);
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return p;
    }

    public ProductVO getProduct( ProductVO productVO)
    {      
      try{
        productVO = facade.getProduct(productVO.getProductId());
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
      return productVO;
    }

}