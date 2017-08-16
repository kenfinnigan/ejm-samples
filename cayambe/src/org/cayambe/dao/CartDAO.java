/*
 * CartDAO.java
 *
 * Created on June 4th, 2002, 1:09 AM
 */

package org.cayambe.dao;

import org.cayambe.core.*;
import org.cayambe.util.CayambeServiceLocator;
import org.apache.log4j.Category;
import java.util.*;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
import org.cayambe.ejb.*;

/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public class CartDAO {

    private static String CLASSNAME = CartDAO.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);


    public CartDAO() {
    }

    public void Save(CartVO cartVO)
    {


       Statement stmt = null;
       Connection conn = null;
       try { 
        
        CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
        DataSource ds = (DataSource)serviceLocator.getDatasource();
        conn = ds.getConnection();
       
		String sqlString = "delete from cart_line_items where cart_id = '" + cartVO.getCartId() + "'";
        stmt = conn.createStatement();
	    stmt.executeUpdate( sqlString );
		 
	    Iterator itemsIterator = cartVO.getItemIterator();
	    while ( itemsIterator.hasNext() ) 
 	    {
	     CartItemVO cartItemVO = cartVO.getCartItem( (String) itemsIterator.next() );
	     ProductVO product = cartItemVO.getProduct();
	     
		 

		 StringBuffer sqlItems = new StringBuffer(255);
         sqlItems.append("insert into cart_line_items ");
         sqlItems.append(" (cart_id,product_id,quantity) ");
         sqlItems.append(" values ");
         sqlItems.append("('");
         sqlItems.append( cartVO.getCartId() );
         sqlItems.append("','");
         sqlItems.append( product.getProductId() );
         sqlItems.append("','");
         sqlItems.append( cartItemVO.getQuantity() );
         sqlItems.append("')");

		 cat.debug ( sqlItems );
	     stmt.executeUpdate(sqlItems.toString());
        
		}

    	stmt.close();
    	conn.close();

      } catch (Exception e) {
    	cat.debug("ERROR: " + e.getMessage());
  	  } finally {          
        try {
          if (conn != null) conn.close();     
          if (stmt != null) stmt.close();     
        } catch (SQLException ignored) {}
      }      

   }


   public CartVO getCart(String cartId)
   {
	   	CartVO cartVO = new CartVO();
		cartVO.setCartId( cartId );

		String sqlString = "select * from cart_line_items where cart_id = '" + cartVO.getCartId() + "'";
		cat.debug ( sqlString );
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

		ProductFacade productFacade;   

		try
		{
     	  ProductFacadeHome home = (ProductFacadeHome)CayambeServiceLocator.getInstance()
                                 .getHome( "ProductFacade", ProductFacadeHome.class );
          productFacade = (ProductFacade)home.create();
        
          DataSource ds = (DataSource)CayambeServiceLocator.getInstance().getDatasource();
          conn = ds.getConnection();

          stmt = conn.createStatement();
          rs = stmt.executeQuery( sqlString );        
		  while ( rs.next() ) {
			ProductVO p = (ProductVO) productFacade.getProduct ( rs.getString("product_id") );											    
			cartVO.addItem( p, rs.getInt("quantity") );
          }
          rs.close();
          stmt.close();
		  conn.close();


        } catch(Exception e) {
          e.printStackTrace();
          cat.info(e.getMessage());       
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
            if (rs != null) rs.close();
          } catch (SQLException ignored) {}
        }      

        return cartVO;

    }


    public void Remove(CartVO cartVO)
    {


       Statement stmt = null;
       Connection conn = null;
       try { 
        
        CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
        DataSource ds = (DataSource)serviceLocator.getDatasource();
        conn = ds.getConnection();
       
		String sqlString = "delete from cart_line_items where cart_id = '" + cartVO.getCartId() + "'";
        stmt = conn.createStatement();
	    stmt.executeUpdate( sqlString );
    	stmt.close();
    	conn.close();

      } catch (Exception e) {
    	cat.debug("ERROR: " + e.getMessage());
  	  } finally {          
        try {
          if (conn != null) conn.close();     
          if (stmt != null) stmt.close();     
        } catch (SQLException ignored) {}
      }      

   }


}