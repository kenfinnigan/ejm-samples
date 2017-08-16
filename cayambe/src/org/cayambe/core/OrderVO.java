package org.cayambe.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */


public class OrderVO  implements Serializable {

  private long orderId = 0;
  private CartVO cartVO = null;
  private BillingInfoVO billingInfoVO = null;
  private ShippingInfoVO shippingInfoVO = null;
  //private date date = null;
  private String date = null;
  private String status = "1";
  private String statusName = null;
  private String salesTaxDesc = null;
  private double salesTaxTotal = 0;
  private ArrayList lineItems = new ArrayList();

  public void setOrderId ( String _orderId ) { orderId = new Long(_orderId).longValue() ; }
  public void setOrderId ( long _orderId ) { orderId = _orderId; }
  public long getOrderId() { return orderId; }

  public void setCartVO ( CartVO _cartVO ) { cartVO = _cartVO; }
  public CartVO getCartVO() { return cartVO; }

  public void setBillingInfoVO( BillingInfoVO _billingInfoVO ) { billingInfoVO  = _billingInfoVO; }
  public BillingInfoVO getBillingInfoVO() { return billingInfoVO; }

  public void setShippingInfoVO ( ShippingInfoVO _shippingInfoVO ) { shippingInfoVO = _shippingInfoVO; }
  public ShippingInfoVO getShippingInfoVO() { return shippingInfoVO; }

/*
  public void setDate ( Date _date ) { date = _date; }
  public Date getDate () { return date; }
*/
  public void setDate ( String _date ) { date = _date; }
  public String getDate () { return date; }

  public void setStatus ( String _status ) { status = _status; }
  public String getStatus () { return status; }

  public void setStatusName ( String _statusName ) { statusName = _statusName; }
  public String getStatusName () { return statusName; }

  public void setSalesTaxDesc ( String _salesTaxDesc ) { salesTaxDesc = _salesTaxDesc; }
  public String getSalesTaxDesc () { return salesTaxDesc; }

  public void setSalesTaxTotal ( double _salesTaxTotal ) { salesTaxTotal = _salesTaxTotal; }
  public double getSalesTaxTotal () { return salesTaxTotal; }

  public void addLineItem ( ProductVO p, int quantity, double salePrice )
  {
	  LineItemVO lineItemVO = new LineItemVO( p, quantity, salePrice );
	  lineItems.add( lineItemVO );
  }
  public void addLineItem ( LineItemVO lineItemVO )
  {
	  lineItems.add( lineItemVO );
  }
  public Collection getLineItems ( )
  {
	  return lineItems;
  }
	

}