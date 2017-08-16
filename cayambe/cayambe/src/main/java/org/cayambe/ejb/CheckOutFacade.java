package org.cayambe.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import org.cayambe.core.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public interface CheckOutFacade extends EJBObject
{

    public void Save( OrderVO orderVO )
      throws RemoteException;

}
