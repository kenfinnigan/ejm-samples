package org.cayambe.client;

import java.util.List;
import java.util.Hashtable;
import org.cayambe.ejb.*;
import org.cayambe.core.OrderVO;
import org.cayambe.util.*;
import org.apache.log4j.Category;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;


/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 **/
public class OrderDelegate {
    
    OrderFacade facade;
    private static String CLASSNAME = OrderDelegate.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public OrderDelegate()
    {
      try{
        OrderFacadeHome home = (OrderFacadeHome)CayambeServiceLocator.getInstance()
                               .getHome( "OrderFacade", OrderFacadeHome.class );
        facade = (OrderFacade)home.create(); 
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public void ListOrdersByStatus( String status )
    throws Exception
    {
      try{
        facade.ListOrdersByStatus( status );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
    }

    public List getOrderList () {
      List list = null;
      try {
        list = (List)facade.getOrderList();
        cat.debug("OrderDelegate.getOrderList(): size: "
          + list.size());
      }catch(Exception e){
        cat.info(e.getMessage());
        e.printStackTrace();
      }
      return list;
    }

    public Hashtable getOrderStatuses( )
    throws Exception
    {
	  Hashtable statuses = null;
      try{
        statuses = facade.getOrderStatuses( );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
	  return statuses;
    }

    public OrderVO getOrderVO( OrderVO orderVO )
    throws Exception
    {
      try{
        orderVO = facade.getOrderVO( orderVO );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
	  return orderVO;
    }

}