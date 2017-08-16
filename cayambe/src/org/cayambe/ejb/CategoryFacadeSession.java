package org.cayambe.ejb;

import java.util.*;
import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import org.cayambe.core.CategoryVO;
import org.cayambe.core.ProductVO;
import org.cayambe.util.CategoryListHandler;
import org.cayambe.dao.CategoryDAO;
import org.apache.log4j.Category;


/**
 * anatr:16.10: added getAllCategories() for taglib usage, added listHandler=null to passivate
 *
 * Created Sep 20, 2001 9:31:43 PM
 * @author Mike Davis <mdavis@wantjava.com>
 */

public class CategoryFacadeSession implements SessionBean {
    private SessionContext context;
    private CategoryListHandler listHandler;
    private CategoryDAO categoryDAO;
    
	private static String CLASSNAME = CategoryFacadeSession.class.getName();
    private Category cat;

    /**
     * No argument constructor required by container.
     */
    public CategoryFacadeSession() {
    }

    public List getCategoryList() //throws CategoryException
    {
        return getListHandler().getCategoryList();
    }


    public Hashtable getAllCategories()
    {
      Hashtable categories = getListHandler().getCategoryDAO().getAllCategories();
      //Add dummy top category - must be a cleaner way!
      CategoryVO c = new CategoryVO("0");
      c.setName("");
      categories.put(new Long("0"),c);
      
      Enumeration categoryEnum = categories.elements();
      while( categoryEnum.hasMoreElements() ){
        CategoryVO current = (CategoryVO)categoryEnum.nextElement();
        CategoryVO parentOfCurrent = (CategoryVO)categories.get( current.getParentId() );
        //take care of root Category
        if (parentOfCurrent != null && !parentOfCurrent.equals(current))
            parentOfCurrent.addChild( current );
      }
      
      return categories;
    }
    
    public CategoryVO getCategoryHierarchy( CategoryVO selected )
    {
/*
THIS DOES NOTHING CURRENTLY

      Hashtable categories = getListHandler().getCategoryDAO().getAllCategories(selected);
      getCategoryLogger().debug("selected.getName() = " + selected.getName());

      Enumeration enum = categories.elements();
      while( enum.hasMoreElements() ){
        CategoryVO current = (CategoryVO)enum.nextElement();
    	getCategoryLogger().debug("current.getName() = " + current.getName());
	    getCategoryLogger().debug("current.getParentId() = " + current.getParentId());
        if( ! current.getParentId().equals( selected.get ) ){
          CategoryVO parentOfCurrent = (CategoryVO)categories.get( current.getParentId() );
          parentOfCurrent.addChild( current );
	      getCategoryLogger().debug("parentOfCurrent.getName() = " + parentOfCurrent.getName());
        }
      }
*/
      return selected;
    }


    public CategoryVO getCategory(CategoryVO c) //throws CategoryException
    {
        return getCategoryDAO().getCategory(c);
    }

    public int getListSize() //throws IteratorException
    {
        return getListHandler().getListSize();
    }

    public void listCategories(CategoryVO listCriteria) //throws CategoryException
    {
        getListHandler().listCategories(listCriteria);
    }

    public void RemoveCategory(CategoryVO c) //throws CategoryException
    {
        getCategoryDAO().Remove(c);
    }

    public void RemoveProductsFromCategory(CategoryVO c) //throws CategoryException
    {
        getCategoryDAO().RemoveProductsFromCategory(c);
    }


    public void UpdateCategory(CategoryVO c) //throws CategoryException
    {
        getCategoryDAO().Save(c);
    }

    public void SaveProductToCategory(CategoryVO c, ProductVO p) //throws CategoryException
    {
        getCategoryDAO().SaveProductToCategory(c,p);
    }

    private CategoryDAO getCategoryDAO()
    {
        if( categoryDAO == null ){
            categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }

    private CategoryListHandler getListHandler()
    {
        if (listHandler == null) {
            listHandler = new CategoryListHandler();
        }
        return listHandler;
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
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
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
      categoryDAO = null;
      listHandler = null;
	  cat = null;
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
}
