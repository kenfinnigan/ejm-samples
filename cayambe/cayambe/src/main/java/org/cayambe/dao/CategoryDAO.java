/*
 * CategoryDAO.java
 *
 * Created on September 20, 2001, 11:09 PM
 */

package org.cayambe.dao;
import org.cayambe.core.CategoryVO;
import org.cayambe.core.ProductVO;
import java.util.*;
import java.sql.*;
import org.cayambe.util.CayambeServiceLocator;
import org.apache.log4j.Category;
import javax.sql.DataSource;
import javax.naming.*;


/**
 *
 * @author  Mike Davis <mdavis@wantjava.com>
 * @author  Jon Rose<jonr@wantjava.com>
 * @version 0.1
 */
public class CategoryDAO {

    private final String topCategoryId = "0";
    
    private static String CLASSNAME = CategoryDAO.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    /** Creates new CategoryDAO */
    public CategoryDAO() {
    }

    public void Save(CategoryVO c)
    {

        Statement stmt = null;
        Connection conn = null;
        try {

          cat.debug("Category to save: " + c.toString());
          
          StringBuffer sqlString = new StringBuffer(512);
          StringBuffer sqlString2 = new StringBuffer(512);
          if (c.getId() == null || c.getId().equals(new Long("0"))) {
            sqlString.append("insert into category");
            sqlString.append("(name,header,visible,image) ");
            sqlString.append("values ('" );
            sqlString.append(c.getName());
            sqlString.append("','");
            sqlString.append(c.getHeader());
            sqlString.append("','");
	      if (c.isSelected()) {
              sqlString.append("1");
	      } else {
              sqlString.append("0");
	      }
            sqlString.append("','");
            sqlString.append(c.getImagePath());
            sqlString.append("')");

	        sqlString2.append("insert into category_category (category_id,parent_id)");
	        sqlString2.append(" values (");
	        sqlString2.append("LAST_INSERT_Id()");
	        sqlString2.append(",'");
	        sqlString2.append(c.getParentId());
	        sqlString2.append("')");
          } else {
            sqlString.append("update category set name = '");
            sqlString.append(c.getName());
	        sqlString.append("', header = '");
            sqlString.append(c.getHeader());
	        sqlString.append("', visible = '");
            if (c.isSelected()) {
              sqlString.append("1");
            } else {
              sqlString.append("0");
            }
	        sqlString.append("', image = '");
            sqlString.append(c.getImagePath());
            sqlString.append("' where category_id = '");
            sqlString.append(c.getId());
            sqlString.append("'");
          }


          cat.debug(sqlString.toString());
          cat.debug(sqlString2.toString());
          
          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();
          
          stmt = conn.createStatement();
		  cat.debug("About run first stmt.");
          stmt.executeUpdate(sqlString.toString());
		  cat.debug("first stmt executed successfully");
          stmt.executeUpdate(sqlString2.toString());
          stmt.close();
          conn.close();


        } catch (Exception e) {          
          cat.info(e.getMessage());
  	    } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
          } catch (SQLException ignored) {}
        }      
    }


    public void SaveProductToCategory(CategoryVO c, ProductVO p)
    {

        Statement stmt = null;
        Connection conn = null;
        try {

          StringBuffer sqlString = new StringBuffer(512);
          sqlString.append("insert into product_category ");
          sqlString.append("(product_id,category_id) ");
          sqlString.append("values ('" );
          sqlString.append(p.getProductId());
          sqlString.append("','" );
          sqlString.append(c.getId());
          sqlString.append("')");

          cat.debug(sqlString.toString());

          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();
          
          stmt = conn.createStatement();
          stmt.executeUpdate(sqlString.toString());
          stmt.close();
          conn.close();

        } catch (Exception e) {          
          cat.info(e.getMessage());
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
          } catch (SQLException ignored) {}
        }      

    }

    public void Remove(CategoryVO c)
    {

        Statement stmt = null;
        Connection conn = null;
        try {

          StringBuffer sqlString = new StringBuffer(512);
          sqlString.append("delete from category");
          sqlString.append(" where category_id = ");
          sqlString.append(c.getId());

          cat.debug(sqlString.toString());

          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();
          
          stmt = conn.createStatement();
          stmt.executeUpdate(sqlString.toString());
          stmt.close();
          conn.close();


        } catch (Exception e) {          
          cat.info(e.getMessage());
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
          } catch (SQLException ignored) {}
        }      

    }

    public void RemoveProductsFromCategory(CategoryVO c)
    {

        Statement stmt = null;
        Connection conn = null;
        try {



          StringBuffer sqlString = new StringBuffer(512);
          sqlString.append("delete from product_category");
          sqlString.append(" where category_id = ");
          sqlString.append(c.getId());

          cat.debug(sqlString.toString());
          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();

          stmt = conn.createStatement();
          stmt.executeUpdate(sqlString.toString());
          stmt.close();
          conn.close();

        } catch (Exception e) {          
          cat.info(e.getMessage());
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
          } catch (SQLException ignored) {}
        }      

    }


    public Hashtable getAllCategories()
    {
      return getAllCategories( new CategoryVO(topCategoryId) );
    }

    //anatr: where does selectedCategory used?
    public Hashtable getAllCategories( CategoryVO selectedCategory )
    {
        Hashtable categories = new Hashtable();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        //sql look up of categories based on listCriteria.id being the parent id
        StringBuffer sqlString = new StringBuffer(512);
        sqlString.append("SELECT category.category_id, category.name, category.header, ");
        sqlString.append("category.image, category_category.parent_id ");
        sqlString.append("FROM category, category_category ");
        sqlString.append("WHERE category.category_id = category_category.category_id ");
        sqlString.append(" AND category.visible = 0 ");
        sqlString.append(" ORDER BY category_category.parent_id, category.category_id");
        

        cat.debug("----->" + sqlString.toString());
        try {

          
          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();

          stmt = conn.createStatement();
          rs = stmt.executeQuery(sqlString.toString());
          while (rs.next()) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setId( new Long( rs.getLong("category_id")) );
            categoryVO.setName( rs.getString("name"));
            categoryVO.setHeader( rs.getString("header") );
            categoryVO.setImagePath( rs.getString("image") );
            categoryVO.setParentId( new Long(rs.getString("parent_id")) );

            if( categoryVO.getId().equals( selectedCategory.getId() ) ){
              categoryVO.setSelected(true);
            }
            categories.put(categoryVO.getId(), categoryVO);
          }
          rs.close();
          stmt.close();
          conn.close();

        } catch (Exception e) {
          //later
          cat.info(e.getMessage());
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
            if (rs != null) rs.close();     
          } catch (SQLException ignored) {}
        }      

        //return a collection of CategoryVO's
        return categories;
    }

    public Collection listCategories( CategoryVO listCriteria )
    {
        ArrayList list = new ArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        
        //sql look up of categories based on listCriteria.id being the parent id
        StringBuffer sqlString = new StringBuffer(512);
        sqlString.append("select category.category_id, category.name, category_category.parent_id ");
        sqlString.append("from category, category_category ");
        sqlString.append("where ");
        sqlString.append("category_category.parent_id = ");
        sqlString.append(listCriteria.getId());
        sqlString.append(" and ");
        sqlString.append("category.category_id = category_category.category_id");
        cat.debug("----->" + sqlString.toString());
        try {

          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();

          stmt = conn.createStatement();
          rs = stmt.executeQuery(sqlString.toString());
          while (rs.next()) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setId( new Long( rs.getLong("category_id")) );
            categoryVO.setParentId( new Long( rs.getLong("parent_id")) );
            cat.debug("Category Name: " + rs.getString("category.name"));
            categoryVO.setName( rs.getString("name"));
            list.add(categoryVO);
            cat.debug("Catagor being add..." + categoryVO.toString());
          }
          rs.close();
          stmt.close();
          conn.close();

        } catch (Exception e) {          
          cat.info(e.getMessage());
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
            if (rs != null) rs.close();     
          } catch (SQLException ignored) {}
        }      

        //return a collection of CategoryVO's
        return list;
    }

    public CategoryVO getCategory( CategoryVO c)
    {
	
        CategoryVO categoryVO = new CategoryVO();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        //sql look up of categories based on listCriteria.id being the parent id
        StringBuffer sqlString = new StringBuffer(512);
        sqlString.append("select category.category_id, category.name, category.header, category_category.parent_id ");
        sqlString.append("from category, category_category");
        sqlString.append(" where ");
        sqlString.append("category.category_id = '");
        sqlString.append(c.getId());
        sqlString.append("'");
        sqlString.append(" and category.category_id = category_category.category_id");        
        cat.debug("----->" + sqlString.toString());
        try {
          
          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();
          
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sqlString.toString());
          rs.next();
            categoryVO.setId( new Long( rs.getLong("category.category_id")) );
            categoryVO.setParentId( new Long( rs.getLong("category_category.parent_id")) );
            categoryVO.setName( rs.getString("category.name"));
            categoryVO.setHeader( rs.getString("category.header"));
          rs.close();
          stmt.close();
          conn.close();

        } catch (Exception e) {          
          cat.info(e.getMessage());
        } finally {          
          try {
            if (conn != null) conn.close();     
            if (stmt != null) stmt.close();     
            if (rs != null) rs.close();     
          } catch (SQLException ignored) {}
        }      

        //return a collection of CategoryVO's
        return categoryVO;
    }

}