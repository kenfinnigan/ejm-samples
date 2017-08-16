package org.cayambe.ejb;

import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import org.cayambe.core.ProductVO;
import org.cayambe.core.CategoryVO;
import org.cayambe.util.ProductListHandler;
import org.cayambe.dao.ProductDAO;
import org.apache.log4j.Category;

/**
 * anatr:21.10: added  things to passivate
 *
 * Created Sep 20, 2001 9:31:43 PM
 * @author Jon Rose <jonr@wantjava.com>
 */

public class ProductFacadeSession implements SessionBean {
    private SessionContext context;
    private ProductListHandler listHandler;
    private ProductDAO productDAO;
    
	private static String CLASSNAME = CategoryFacadeSession.class.getName();
    private Category cat;

    /**
     * No argument constructor required by container.
     */
    public ProductFacadeSession() {
		getCategoryLogger().debug("create instance");
    }

    public List getProductList() //throws ProductException
    {
        return getListHandler().getProductList();
    }

    public ProductVO getProduct(String productId)
    {
        ProductVO p = getProductDAO().FindByProductId(productId);
        return p;
    }

    public void SearchProduct(ProductVO p) //throws ProductException
    {		
		try
		{
    		getListHandler().SearchProduct(p);	
		}
		catch (Exception e)
		{
		    getCategoryLogger().debug("<-><->" + e.getMessage());
		}        
    }

    public void listProductsInCategory(CategoryVO category) //throws ProductException
    {
        getListHandler().listProductsInCategory(category);
    }

    public void UpdateProduct(ProductVO p) {
        getProductDAO().Save(p);
    }


    public void RemoveProduct(ProductVO p) {
        getProductDAO().Remove(p);
    }


    private ProductListHandler getListHandler()
    {
        if (listHandler == null) {
            listHandler = new ProductListHandler();
        }
        return listHandler;
    }

    private ProductDAO getProductDAO()
    {
        if( productDAO == null ){
            productDAO = new ProductDAO();
        }
        return productDAO;
    }
	
    private Category getCategoryLogger()
    {
        if( cat == null ){
          cat = (Category)Category.getInstance(CLASSNAME);
        }
        return cat;
    }
    

    /**
     * Create method specified in EJB 1.1 section 6.10.3
     */
    public void ejbCreate() throws CreateException {
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
      context = null;
      productDAO = null;
      listHandler = null;
	  cat = null;
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
}
