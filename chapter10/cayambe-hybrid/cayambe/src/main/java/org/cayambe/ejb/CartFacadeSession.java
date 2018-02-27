package org.cayambe.ejb;
import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import org.cayambe.core.*;
import org.cayambe.dao.*;
import org.apache.log4j.Category;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public class CartFacadeSession implements SessionBean
{
    private SessionContext context;
    private CartVO cart;
    private CartDAO cartDAO;

	private static String CLASSNAME = CartFacadeSession.class.getName();
	private Category cat;

    //----------------------------------- Business Methods -----------------------------------------
	public void setCartId ( String cartId )
	{
		cart.setCartId ( cartId );
	}
    public void addItem( ProductVO product, int quantity )
    {
      if( cart == null ){
        cart = new CartVO();
      }
      cart.addItem( product, quantity );
    }

    public void addItem( ProductVO product )
    {
	  try
	  {
		if ( cart.getCartItem( product.getProductId() ).getQuantity() > 0)
		{
		  updateQuantity( product.getProductId(), cart.getCartItem( product.getProductId() ).getQuantity() + 1 );
		} else {
		  addItem( product.getProductId(), 1 );
		}
	  }
	  catch (NullPointerException  ne)
	  {
		  addItem( product.getProductId(), 1 );
	  } 
	  catch (Exception e)
	  {
          getCategoryLogger().info( e.getMessage() );
	  }
    }

    public void addItem( String productId, int quantity )
    {
      ProductVO product = new ProductVO();
      ProductDAO dao = new ProductDAO();
      product = dao.FindByProductId(productId);
      addItem( product, quantity );
    }

    public void addItem( String productId )
    { 
	  ProductVO p = new ProductVO();
	  p.setProductId( productId );
	  addItem( p );
      //addItem( productId, 1 );
    }

    public void removeItem( ProductVO product )
    {
      removeItem( product.getProductId() );
    }

    public void removeItem( String productId )
    {
      if( cart == null ){
        return;
      }
      cart.removeItem( productId );
    }

    public void updateQuantity( String productId, int quantity )
    {
      cart.updateQuanity( productId, quantity );
    }

    public CartVO getCart()
    {
      return cart;
    }

	public void setCart( CartVO cartVO)
    {
      this.cart = cartVO;
    }

	public void purgeCart( CartVO cartVO )
    {
	  cart = null;
	  getCartDAO().Remove( cartVO );
    }

	public void loadCart ( String cartId ) 
	{
	  cart = getCartDAO().getCart( cartId );
	}

	public void Save ( CartVO cartVO ) 		
	{
		getCartDAO().Save( cartVO );
	}

	private CartDAO getCartDAO()
    {
        if( cartDAO == null ){
            cartDAO = new CartDAO();
        }
        return cartDAO;
    }

    private Category getCategoryLogger()
    {
        if( cat == null ){
          cat = (Category)Category.getInstance(CLASSNAME);
        }
        return cat;
    }
    
    //-------------------------------------- EJB Methods -------------------------------------------
    /**
     * Create method specified in EJB 1.1 section 6.10.3
     */
    public void ejbCreate() throws CreateException
    {
    }

    /* Methods required by SessionBean Interface. EJB 1.1 section 6.5.1. */

    /**
     * @see javax.ejb.SessionBean#setContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext _context){
        context = _context;
    }

    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
    }

    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
		cartDAO = null;
		cat = null;
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
}
