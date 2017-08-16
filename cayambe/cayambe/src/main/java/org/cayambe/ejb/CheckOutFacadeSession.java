package org.cayambe.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import org.cayambe.core.*;
import org.cayambe.dao.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public class CheckOutFacadeSession implements SessionBean
{
    private SessionContext context;
	private OrderDAO orderDAO = null;

    //----------------------------------- Business Methods -----------------------------------------
    public void Save( OrderVO orderVO )
    {
	   getOrderDAO().Save(orderVO);
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
		orderDAO = null;
    }

    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
}