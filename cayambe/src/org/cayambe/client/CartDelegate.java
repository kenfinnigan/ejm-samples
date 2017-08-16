package org.cayambe.client;

import java.util.List;
import org.cayambe.ejb.*;
import org.cayambe.core.CartVO;
import org.cayambe.util.*;
import org.apache.log4j.Category;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;


/**
 *
 * @author  Mike Davis <mdavis@wantjava.com>
 * @author  Jon Rose<jonr@wantjava.com>
 * @version 0.1
 */
public class CartDelegate {
    CartFacade facade;

	private static String CLASSNAME = CartDelegate.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public CartDelegate()
    {
      try{
        CartFacadeHome home = (CartFacadeHome)CayambeServiceLocator.getInstance()
                               .getHome( "CartFacade", CartFacadeHome.class );
        facade = (CartFacade)home.create();
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public CartDelegate( String cartId )
    {
      try
	  {
		CartFacadeHome home = (CartFacadeHome)CayambeServiceLocator.getInstance()
                          .getHome( "CartFacade", CartFacadeHome.class );
        facade = (CartFacade)home.create();
		facade.loadCart ( cartId );
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

	public void Save( CartVO cartVO ) {
	  try
	  {		
		facade.Save( cartVO );	
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());       
      }	
	}
	
    public void setCartId( String cartId )
    throws Exception
    {
      try{
        facade.setCartId( cartId );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
    }

	public void Remove( CartVO cartVO ) {
	  try
	  {		
		facade.purgeCart( cartVO );	
      }
      catch( RemoteException re ){
        re.printStackTrace();
        cat.info(re.getMessage());       
      }	
	}

    public void addItem( String productId )
    throws Exception
    {
      try{
        facade.addItem( productId );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
    }
    
    public void removeItem( String productId )
    throws Exception
    {
      try{
        facade.removeItem( productId );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
    }
    
    public void updateQuantity( String productId, int quantity )
    throws Exception
    {
      try{
        facade.updateQuantity( productId, quantity );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception("Error removing Item from Cart. " + re.getMessage());
      }
    }

    public CartVO getCart()
    throws Exception
    {
      CartVO cart = null;
      try{		
        cart = facade.getCart();
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception("Error getting user Cart. " + re.getMessage());
      }
      return cart;
    }
}
