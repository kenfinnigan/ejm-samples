package org.cayambe.ejb;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import org.cayambe.core.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public interface CartFacade extends EJBObject
{
	public void setCartId( String cartId )
      throws RemoteException;

    public void addItem( ProductVO product, int quantity )
      throws RemoteException;

    public void addItem( ProductVO product )
      throws RemoteException;

    public void addItem( String productId, int quantity )
      throws RemoteException;

    public void addItem( String productId )
      throws RemoteException;

    public void removeItem( String productId )
      throws RemoteException;

    public void updateQuantity( String productId, int quantity )
      throws RemoteException;

    public CartVO getCart()
      throws RemoteException;

	public void setCart( CartVO cartVO )
      throws RemoteException;

	public void purgeCart( CartVO cartVO )
      throws RemoteException;

	public void loadCart ( String cartId ) 
	  throws RemoteException;

	public void Save ( CartVO cartVO ) 		
      throws RemoteException;

}
