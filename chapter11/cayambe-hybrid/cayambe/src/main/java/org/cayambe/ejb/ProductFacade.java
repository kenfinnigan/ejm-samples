package org.cayambe.ejb;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.util.List;
import org.cayambe.core.ProductVO;
import org.cayambe.core.CategoryVO;

/**
 * Created Sep 20, 2001 9:31:43 PM
 * @author Jon Rose <jonr@wantjava.com>
 */

public interface ProductFacade extends EJBObject
{
    public void SearchProduct( ProductVO p )
        throws RemoteException /*, CategoryException*/;

    public List getProductList()
        throws RemoteException /*, CategoryException*/;

    public ProductVO getProduct(String productId)
        throws RemoteException /*, CategoryException*/;

    public void UpdateProduct( ProductVO p)
        throws RemoteException;

    public void RemoveProduct(ProductVO p)
        throws RemoteException;

    public void listProductsInCategory(CategoryVO category)
        throws RemoteException /*, ProductException */;

}