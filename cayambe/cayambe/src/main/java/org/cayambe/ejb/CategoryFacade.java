package org.cayambe.ejb;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.util.List;
import java.util.Hashtable;
import org.cayambe.core.CategoryVO;
import org.cayambe.core.ProductVO;

/**
 * anatr:16.10: added getAllCategories() for taglib usage
 *
 * Created Sep 20, 2001 9:31:43 PM
 * @author Mike Davis <mdavis@wantjava.com>
 */

public interface CategoryFacade extends EJBObject
{
    public void listCategories( CategoryVO listCriteria )
        throws RemoteException /*, CategoryException*/;

    public int getListSize()
        throws RemoteException /*, IteratorException*/;

    public List getCategoryList()
        throws RemoteException /*, CategoryException*/;
    
    public Hashtable getAllCategories()
        throws RemoteException;

    public CategoryVO getCategoryHierarchy( CategoryVO selected )
        throws RemoteException;

    public CategoryVO getCategory(CategoryVO c)
        throws RemoteException /*, CategoryException*/;

    public void RemoveCategory(CategoryVO c)
        throws RemoteException /*, CategoryException*/;

    public void UpdateCategory(CategoryVO c)
        throws RemoteException /*, CategoryException*/;

    public void SaveProductToCategory(CategoryVO c, ProductVO p)
        throws RemoteException /*, CategoryException*/;

    public void RemoveProductsFromCategory(CategoryVO c)
        throws RemoteException /*, CategoryException*/;
}
