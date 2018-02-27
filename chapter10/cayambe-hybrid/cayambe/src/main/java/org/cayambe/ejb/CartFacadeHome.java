package org.cayambe.ejb;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import org.cayambe.core.CartVO;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public interface CartFacadeHome extends EJBHome
{
    CartFacade create()
        throws RemoteException,CreateException;
}
