/*
 * OrderListHandler.java
 *
 * Created on June 10th, 2002, 11:01 PM
 */

package org.cayambe.util;

import org.cayambe.dao.OrderDAO;
import org.cayambe.core.OrderVO;
import org.cayambe.core.CategoryVO;
import java.util.*;

/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */
public class OrderListHandler extends ValueListHandler {

    private OrderDAO orderDAO;

    public OrderListHandler() {
    }

    public void ListOrdersByStatus( String status )
    {
       Collection orders = getOrderDAO().ListOrdersByStatus( status );
       setList( orders );
    }

    public List getOrderList()
    {
       return (List)getList();
    }

    private OrderDAO getOrderDAO()
    {
        if( orderDAO == null ){
            orderDAO = new OrderDAO();
        }
        return orderDAO;
    }
}
