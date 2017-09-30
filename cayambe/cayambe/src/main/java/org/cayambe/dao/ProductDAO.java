/*
 * ProductDAO.java
 *
 * Created on September 20, 2001, 11:09 PM
 */

package org.cayambe.dao;

import org.cayambe.core.*;
import org.cayambe.util.CayambeServiceLocator;
import org.apache.log4j.Category;
import java.util.*;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */
public class ProductDAO {

    private static String CLASSNAME = ProductDAO.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    /** Creates new ProductDAO */
    public ProductDAO() {
    }

    public void Save(ProductVO p)
    {

       Statement stmt = null;
       Connection conn = null;
       
       try { 

          StringBuffer sqlString = new StringBuffer(512);
          if (p.getProductId() == null || p.getProductId().length() == 0) {
            sqlString.append("insert into product ");
            sqlString.append("(title,description,price,image,sku,sale_price,on_sale,visible) ");
            sqlString.append("values ('" );
            sqlString.append(p.getTitle());
            sqlString.append("','");
            sqlString.append(p.getDesc());
            sqlString.append("','");
            sqlString.append(p.getPrice());
            sqlString.append("','");
            sqlString.append(p.getImage());
            sqlString.append("','");
            sqlString.append(p.getSKU());
            sqlString.append("','");
            sqlString.append(p.getSalePrice());
            sqlString.append("','");
            if (p.isOnSale()) {
              sqlString.append("1");
            } else {
              sqlString.append("0");
            }
              sqlString.append("','");
	        if (p.isVisible()) {
              sqlString.append("1");
	        } else {
              sqlString.append("0");
	        }
              sqlString.append("')");
            } else {
            sqlString.append("update product set title = '");
            sqlString.append(p.getTitle());
            sqlString.append("', description = '");
            sqlString.append(p.getDesc());
            sqlString.append("', price = '");
            sqlString.append(p.getPrice());
            sqlString.append("', image = '");
            sqlString.append(p.getImage());
            sqlString.append("', SKU = '");
            sqlString.append(p.getSKU());
            sqlString.append("', sale_price = '");
            sqlString.append(p.getSalePrice());
            sqlString.append("', on_sale = '");

            if (p.isOnSale()) {
              sqlString.append("1");
            } else {
              sqlString.append("0");
            }

            sqlString.append("', visible = '");
            if (p.isVisible()) {
              sqlString.append("1");
            } else {
              sqlString.append("0");
            }

            sqlString.append("' where product_id = '");
            sqlString.append(p.getProductId());
            sqlString.append("'");
          }

          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();          

    	  cat.debug(sqlString.toString());
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

    public void Remove(ProductVO p)
    {

        Statement stmt = null;
        Connection conn = null;
        try {

          StringBuffer sqlString = new StringBuffer(512);
          sqlString.append("delete from product where product_id = '");
          sqlString.append(p.getProductId());
          sqlString.append("'");

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

    public ProductVO FindByProductId(String productId)
    {

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        
  	    ProductVO p = new ProductVO();
        String sqlString = null;

        try {

    	  sqlString = "select * from product where product_id = \'" + productId + "\'";

          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();
        
          stmt = conn.createStatement();
          rs = stmt.executeQuery(sqlString);
    	  rs.next();
     	    p.setProductId(rs.getString("product_id"));
     	    p.setTitle(rs.getString("title"));
     	    p.setDesc(rs.getString("description"));
     	    p.setPrice(rs.getDouble("price"));
     	    p.setImage(rs.getString("image"));
            p.setSKU(rs.getString("sku"));
     	    p.setSalePrice(rs.getDouble("sale_price"));

            if (rs.getInt("on_sale") == 1) {
              p.setOnSale(true);
            } else {
              p.setOnSale(false);
            }
	        if (rs.getInt("visible") == 1) {
     	      p.setVisible(true);
	        } else {
     	      p.setVisible(false);
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

        return p;
    }


    public Collection FindByCategoryId(CategoryVO c)
   {
    	StringBuffer sqlString = new StringBuffer(255);
    	sqlString.append("select distinct(product.product_id), product.*, product_category.* from product, product_category ");
    	sqlString.append("where ");
    	sqlString.append("product_category.category_id = ");
     	sqlString.append(c.getId());
    	sqlString.append(" and product.product_id = product_category.product_id");
        ArrayList list = (ArrayList)ListProduct(sqlString.toString());
        return list;
    }


    public Collection  SearchProduct(ProductVO p)
    {
		String title = p.getTitle();
		String SKU = p.getSKU();
		String desc = p.getDesc();
		if (p.getTitle() == null || p.getTitle().length() <= 0) {
			title = null;
	        }
		
		if (p.getSKU() == null || p.getSKU().length() <= 0) {
			SKU = null;
		}
	    
		if (p.getDesc() == null || p.getDesc().length() <= 0) {
			desc = null;
		}
		
    	StringBuffer sqlString = new StringBuffer(255);
    	sqlString.append("select * from product ");
    	sqlString.append("where ");
        if (title != null) {
	  sqlString.append(" upper(product.title) like upper('%");
    	  sqlString.append(title);
          sqlString.append("%')");
        }
        if (desc != null) {
          if (title != null) {
            sqlString.append(" or ");
          }
          sqlString.append(" upper(product.description) like upper('%");
          sqlString.append(desc);
          sqlString.append("%')");
        }
        if (SKU != null) {
          if ((title != null) || (desc != null)) {
            sqlString.append(" or ");
          }
          sqlString.append(" upper(product.SKU) like upper('%");
          sqlString.append(SKU);
          sqlString.append("%')");
        }

        String updatedSql = "(" + sqlString.toString() + ")";
        updatedSql = "select pc.product_id, pc.title, pc.description, pc.price, pc.image, "
                + "pc.sku, pc.sale_price, pc.on_sale, pc.visible, product_category.category_id "
                + "from " + updatedSql + " pc, product_category "
                + "where pc.product_id = product_category.product_id";

        return ListProduct(updatedSql);
    }


    public Collection ListProduct(String sqlString)
    {

        ArrayList list = new ArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {

          cat.info("LISTPRODUCT SQLSTRING ------>" + sqlString);
          
          CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
          DataSource ds = (DataSource)serviceLocator.getDatasource();
          conn = ds.getConnection();

          stmt = conn.createStatement();
          rs = stmt.executeQuery(sqlString);
          while (rs.next()) {
            ProductVO p = new ProductVO();
            p.setProductId(rs.getString("product_id"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setTitle(rs.getString("title"));
            p.setDesc(rs.getString("description"));
            p.setPrice(rs.getDouble("price"));
            p.setImage(rs.getString("image"));
            p.setSKU(rs.getString("sku"));
            p.setSalePrice(rs.getDouble("sale_price"));
            if (rs.getInt("on_sale") == 1) {
              p.setOnSale(true);
            } else {
              p.setOnSale(false);
            }
            if (rs.getInt("visible") == 1) {
              p.setVisible(true);
            } else {
              p.setVisible(false);
            }
            list.add(p);

            cat.debug(p.toString());
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

        return list;

    }

}
