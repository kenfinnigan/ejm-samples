/*
 * OrderDAO.java
 *
 * Created on September 29, 2001, 1:09 AM
 */

package org.cayambe.dao;

import org.cayambe.core.*;
import org.cayambe.ejb.*;
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

public class OrderDAO {

    private static String CLASSNAME = OrderDAO.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    /** Creates new OrderDAO */
    public OrderDAO() {
    }

    public void Save(OrderVO orderVO)
    {

       Statement stmt = null;
       Connection conn = null;
       try { 

        StringBuffer sqlString = new StringBuffer(512);
        sqlString.append("insert into orders ");
        sqlString.append("(status,sales_tax_desc,sales_tax_total) ");
        sqlString.append("values ('" );
        sqlString.append(orderVO.getStatus());
        sqlString.append("','");
        sqlString.append(orderVO.getSalesTaxDesc());
        sqlString.append("','");
        sqlString.append(orderVO.getSalesTaxTotal());
        sqlString.append("')");

        cat.debug(sqlString.toString());
        
        CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
        DataSource ds = (DataSource)serviceLocator.getDatasource();
        conn = ds.getConnection();
        
        stmt = conn.createStatement();
    	stmt.executeUpdate(sqlString.toString());
	
	    String sqlGetOrderId = "select max(order_id) from orders";
	    ResultSet rs = null;
	    rs = stmt.executeQuery(sqlGetOrderId);
	    String orderId = null;
	    rs.next();
	     orderId = rs.getString("max(order_id)");
	    rs.close();


 	    StringBuffer sqlShippingInfo = new StringBuffer(512);
        sqlShippingInfo.append("insert into shipping_info ");
        sqlShippingInfo.append("(order_id,name,address1,address2,city,zipcode,state,country,method,amount,instructions,phone) ");
        sqlShippingInfo.append("values ('" );
	    sqlShippingInfo.append(orderId);
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getName());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getAddress());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getAddress2());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getCity());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getZipCode());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getState());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getCountry());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getMethod());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getAmount());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getInstructions());
        sqlShippingInfo.append("','");
	    sqlShippingInfo.append(orderVO.getShippingInfoVO().getPhone());
        sqlShippingInfo.append("')");
		cat.debug( sqlShippingInfo.toString() );
    	stmt.executeUpdate(sqlShippingInfo.toString());


	    StringBuffer sqlBillingInfo = new StringBuffer(512);
        sqlBillingInfo.append("insert into billing_info ");
        sqlBillingInfo.append("(order_id,name,address1,address2,city,state,zipcode,country,name_on_card,");
	    sqlBillingInfo.append("card_charge_id,phone,email) ");
        sqlBillingInfo.append("values ('" );
	    sqlBillingInfo.append(orderId);
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getName());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getAddress());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getAddress2());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getCity());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getState());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getZipCode());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getCountry());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getCardChargeId());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getPhone());
        sqlBillingInfo.append("','");
	    sqlBillingInfo.append(orderVO.getBillingInfoVO().getEmail());
        sqlBillingInfo.append("')");
		cat.debug( sqlBillingInfo.toString() );
    	stmt.executeUpdate(sqlBillingInfo.toString());

	    Iterator itemsIterator = orderVO.getCartVO().getItemIterator(); 
	    while ( itemsIterator.hasNext() ) 
 	    {
	     CartItemVO cartItemVO = orderVO.getCartVO().getCartItem( (String) itemsIterator.next() );
	     ProductVO product = cartItemVO.getProduct();
	     StringBuffer sqlItems = new StringBuffer(255);
         sqlItems.append("insert into order_line_items ");
         sqlItems.append(" (order_id,product_id,sale_price,quantity) ");
         sqlItems.append(" values ");
         sqlItems.append("('");
         sqlItems.append( orderId );
         sqlItems.append("','");
         sqlItems.append( product.getProductId() );
         sqlItems.append("','");
         sqlItems.append(  product.isOnSale()?product.getSalePrice():product.getPrice() );
         sqlItems.append("','");
         sqlItems.append( cartItemVO.getQuantity() );
         sqlItems.append("')");
	     stmt.executeUpdate(sqlItems.toString());
        }

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

    public Collection ListOrdersByStatus( String status )
    {

	   ArrayList list = new ArrayList();
       Statement stmt = null;
       Connection conn = null;
	   ResultSet rs = null;
       try { 
		
        StringBuffer sqlString = new StringBuffer(512);
		sqlString.append("select orders.*, billing_info.*, ");
		sqlString.append("order_statuses.name, order_statuses.id ");
		sqlString.append("from ");
		sqlString.append("orders, billing_info, order_statuses ");
		sqlString.append("where ");
		sqlString.append("billing_info.order_id = orders.order_id and ");
		sqlString.append("orders.status = order_statuses.id and ");
		sqlString.append("orders.status = '");
		sqlString.append( status );
		sqlString.append("' ");

        cat.debug(sqlString.toString());
        
        CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
        DataSource ds = (DataSource)serviceLocator.getDatasource();
        conn = ds.getConnection();
        
        stmt = conn.createStatement();
	    rs = stmt.executeQuery( sqlString.toString() );
	    
	    while ( rs.next() )
	    {
			OrderVO o = new OrderVO();
			o.setOrderId ( rs.getLong("order_id") );

			BillingInfoVO b = new BillingInfoVO();
			b.setBillingId( rs.getString("billing_info.billing_id") );
            b.setName( rs.getString("billing_info.name") );
            b.setAddress( rs.getString("billing_info.address1") );
            b.setAddress2( rs.getString("billing_info.address2") );
            b.setCity( rs.getString("billing_info.city") );
            b.setState( rs.getString("billing_info.state") );
            b.setZipCode( rs.getString("billing_info.zipcode") );
            b.setCountry( rs.getString("billing_info.country") );
	        b.setPhone( rs.getString("billing_info.phone") );
            b.setEmail( rs.getString("billing_info.email") );

			o.setStatus( rs.getString("order_statuses.id") );
			o.setStatusName( rs.getString("order_statuses.name") );
		    o.setDate( rs.getString("orders.date") );
			o.setBillingInfoVO( b );

			list.add( o );

	    }

    	stmt.close();
    	conn.close();
		rs.close();

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

    public Hashtable getOrderStatuses( )
    {

	   Hashtable statuses = new Hashtable();

       Statement stmt = null;
       Connection conn = null;
	   ResultSet rs = null;
       try { 

        StringBuffer sqlString = new StringBuffer(512);
        sqlString.append("select * from order_statuses order by id");

        cat.debug(sqlString.toString());
        
        CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
        DataSource ds = (DataSource)serviceLocator.getDatasource();
        conn = ds.getConnection();
        
        stmt = conn.createStatement();
	    rs = stmt.executeQuery( sqlString.toString() );
	    
	    while ( rs.next() )
	    {
			statuses.put( rs.getString("id"), rs.getString("name") );
	    }

    	stmt.close();
    	conn.close();
		rs.close();

      } catch (Exception e) {
    	cat.info(e.getMessage());
  	  } finally {          
        try {
          if (conn != null) conn.close();     
          if (stmt != null) stmt.close();     
          if (rs != null) rs.close(); 
        } catch (SQLException ignored) {}
      }      

	  return statuses;

   }

    public OrderVO getOrderVO( OrderVO orderVO )
    {

       Statement stmt = null;
       Connection conn = null;
	   ResultSet rs = null;

       try { 

        StringBuffer sqlLineItems = new StringBuffer(512);
		sqlLineItems.append("select order_line_items ");
		sqlLineItems.append("from ");
		sqlLineItems.append("order_line_items ");
		sqlLineItems.append("where ");
		sqlLineItems.append("order_line_items.order_id = '");
		sqlLineItems.append( orderVO.getOrderId() );
		sqlLineItems.append("' ");

        StringBuffer sqlString = new StringBuffer(512);
		sqlString.append("select orders.*, billing_info.*, shipping_info.*, ");
		sqlString.append("order_statuses.name, order_statuses.id ");
		sqlString.append("from ");
		sqlString.append("orders, billing_info, shipping_info, order_statuses ");
		sqlString.append("where ");
		sqlString.append("billing_info.order_id = orders.order_id and ");
		sqlString.append("shipping_info.order_id = orders.order_id and ");
		sqlString.append("orders.status = order_statuses.id and ");
		sqlString.append("orders.order_id = '");
		sqlString.append( orderVO.getOrderId() );
		sqlString.append("' ");

        cat.debug(sqlString.toString());
        
        CayambeServiceLocator serviceLocator = CayambeServiceLocator.getInstance();
        DataSource ds = (DataSource)serviceLocator.getDatasource();
        conn = ds.getConnection();
        
        stmt = conn.createStatement();
	    rs = stmt.executeQuery( sqlString.toString() );
	    
	    rs.next();			
			
			ShippingInfoVO s = new ShippingInfoVO();
			BillingInfoVO b = new BillingInfoVO();

			s.setShippingId( rs.getString("shipping_info.shipping_id") );
            s.setName( rs.getString("shipping_info.name") );
            s.setAddress( rs.getString("shipping_info.address1") );
            s.setAddress2( rs.getString("shipping_info.address2") );
            s.setCity( rs.getString("shipping_info.city") );
            s.setState( rs.getString("shipping_info.state") );
            s.setZipCode( rs.getString("shipping_info.zipcode") );
            s.setCountry( rs.getString("shipping_info.country") );
            s.setMethod( rs.getString("shipping_info.method") );
            s.setAmount( rs.getDouble("shipping_info.amount") );
            s.setInstructions( rs.getString("shipping_info.instructions") );
            s.setPhone( rs.getString("shipping_info.phone") );

			b.setBillingId( rs.getString("billing_info.billing_id") );
            b.setName( rs.getString("billing_info.name") );
            b.setAddress( rs.getString("billing_info.address1") );
            b.setAddress2( rs.getString("billing_info.address2") );
            b.setCity( rs.getString("billing_info.city") );
            b.setState( rs.getString("billing_info.state") );
            b.setZipCode( rs.getString("billing_info.zipcode") );
            b.setCountry( rs.getString("billing_info.country") );
	        b.setPhone( rs.getString("billing_info.phone") );
            b.setEmail( rs.getString("billing_info.email") );

            b.setCardChargeId( rs.getString("billing_info.card_charge_id") );

			orderVO.setStatus( rs.getString("order_statuses.id") );
			orderVO.setStatusName( rs.getString("order_statuses.name") );
		    orderVO.setDate( rs.getString("orders.date") );
			orderVO.setSalesTaxDesc( rs.getString("sales_tax_desc") );
			orderVO.setSalesTaxTotal( rs.getDouble("sales_tax_total") );
			orderVO.setShippingInfoVO( s );
			orderVO.setBillingInfoVO( b );

		rs.close();

        cat.debug(sqlLineItems.toString());              
        stmt = conn.createStatement();
	    rs = stmt.executeQuery( sqlLineItems.toString() );
	    
	    while (rs.next() ) 
		{
        	ProductFacadeHome home = (ProductFacadeHome)CayambeServiceLocator.getInstance()
				.getHome( "ProductFacade", ProductFacadeHome.class );
            ProductFacade productFacade = (ProductFacade)home.create();
		    ProductVO p = (ProductVO) productFacade.getProduct ( rs.getString("order_line_items.product_id") );

			orderVO.addLineItem( p, 
				rs.getInt("order_line_items.quantity"), 
				rs.getDouble("order_line_items.sale_price") );
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

	  return orderVO;

   }

}