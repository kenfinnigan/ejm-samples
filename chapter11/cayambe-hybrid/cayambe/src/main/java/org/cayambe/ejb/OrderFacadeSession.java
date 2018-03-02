package org.cayambe.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import java.util.*;
import org.cayambe.core.*;
import org.cayambe.dao.*;
import org.cayambe.util.OrderListHandler;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public class OrderFacadeSession implements SessionBean
{
    private SessionContext context;
	private OrderListHandler orderListHandler = null;
	private OrderDAO orderDAO = null;

    //----------------------------------- Business Methods -----------------------------------------

    public void ListOrdersByStatus( String status )
    {
	   getOrderListHandler().ListOrdersByStatus( status );
    }

	public Collection getOrderList( )
    {
	   return getOrderListHandler().getOrderList();
    }

	public Hashtable getOrderStatuses( )
    {
	   return getOrderDAO().getOrderStatuses();
    }
	
	public OrderVO getOrderVO( OrderVO orderVO )
    {
	   return getOrderDAO().getOrderVO( orderVO );
    }

    private OrderListHandler getOrderListHandler()
    {
        if( orderListHandler == null ){
            orderListHandler = new OrderListHandler();
        }
        return orderListHandler;
    }

    private OrderDAO getOrderDAO()
    {
        if( orderDAO == null ){
            orderDAO = new OrderDAO();
        }
        return orderDAO;
    }
    //-------------------------------------- EJB Methods -------------------------------------------
    /**
     * Create method specified in EJB 1.1 section 6.10.3
     */
    public void ejbCreate() throws CreateException
    {
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
		orderListHandler = null;
		orderDAO = null;
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
}