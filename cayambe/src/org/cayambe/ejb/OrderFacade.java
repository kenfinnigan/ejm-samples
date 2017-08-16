package org.cayambe.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.util.Collection;
import java.util.Hashtable;
import org.cayambe.core.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public interface OrderFacade extends EJBObject
{

	public void ListOrdersByStatus( String status )
      throws RemoteException;

	public Collection getOrderList( )
      throws RemoteException;

	public Hashtable getOrderStatuses( )
      throws RemoteException;

	public OrderVO getOrderVO( OrderVO orderVO )
      throws RemoteException;
}