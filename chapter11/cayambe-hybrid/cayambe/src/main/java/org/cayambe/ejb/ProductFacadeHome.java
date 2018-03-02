package org.cayambe.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Created Sep 20, 2001 9:31:43 PM
 * @author Jon Rose <jonr@wantjava.com>
 */

public interface ProductFacadeHome extends EJBHome 
{
    public ProductFacade create() 
        throws RemoteException,CreateException;
}
