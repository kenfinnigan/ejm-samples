/*
 * ProjectListHandler.java
 *
 * Created on September 20, 2001, 11:01 PM
 */

package org.cayambe.util;
import org.cayambe.dao.ProductDAO;
import org.cayambe.core.ProductVO;
import org.cayambe.core.CategoryVO;
import java.util.*;

/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */
public class ProductListHandler extends ValueListHandler {

    private ProductDAO productDAO;

    public ProductListHandler() {
    }

    public void SearchProduct(ProductVO p)
    {
       Collection products = getProductDAO().SearchProduct(p);
       setList( products );
    }

    public void listProductsInCategory( CategoryVO category )
    {
      setList( getProductDAO().FindByCategoryId(category) );
    }

    public List getProductList()
    {
       return (List)getList();
    }

    private ProductDAO getProductDAO()
    {
        if( productDAO == null ){
            productDAO = new ProductDAO();
        }
        return productDAO;
    }
}
