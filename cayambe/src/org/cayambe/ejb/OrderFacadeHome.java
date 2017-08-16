package org.cayambe.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public interface OrderFacadeHome extends EJBHome
{
    public OrderFacade create()
        throws RemoteException,CreateException;
}
