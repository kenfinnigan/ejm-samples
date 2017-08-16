/*
 * ProjectListHandler.java
 *
 * Created on September 20, 2001, 11:01 PM
 */

package org.cayambe.util;
import org.cayambe.dao.CategoryDAO;
import org.cayambe.core.CategoryVO;
import java.util.*;

/**
 *
 * @author  Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */
public class CategoryListHandler extends ValueListHandler {

    private CategoryDAO categoryDAO;

    public void listCategories(CategoryVO listCriteria)
    {
        Collection categories = getCategoryDAO().listCategories( listCriteria );
        setList( categories );
    }

    public List getCategoryList()
    {
      return getList();
    }

    public int getListSize()
    {
      return 0;
    }

    public CategoryDAO getCategoryDAO()
    {
        if( categoryDAO == null ){
            categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }
}
