package org.cayambe.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Created Sep 20, 2001 9:31:43 PM
 * @author Mike Davis <mdavis@wantjava.com>
 */

public interface CategoryFacadeHome extends EJBHome 
{
    public CategoryFacade create() 
        throws RemoteException,CreateException;
}
