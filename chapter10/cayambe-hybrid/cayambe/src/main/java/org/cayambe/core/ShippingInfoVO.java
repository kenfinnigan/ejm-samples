package org.cayambe.core;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */


public class ShippingInfoVO implements Serializable {

  private Long shippingId = null;
  private Long orderId =  null;
  private String name = null;
  private String address = null;
  private String address2 = null;
  private String city = null;
  private String state = null;
  private String zipCode = null;
  private String country = null;
  private String method = null;
  private double amount = 0;
  private String instructions = null;
  private String phone = null;

  public void setShippingId ( Long _shippingId ) { shippingId = _shippingId; }
  public void setShippingId ( String _shippingId ) { shippingId = new Long ( _shippingId ); }
  public Long getShippingId () { return shippingId; }

  public void setOrderId ( Long _orderId ) { orderId = _orderId; }
  public void setOrderId ( String _orderId ) { orderId = new Long ( _orderId ); }
  public Long getOrderId () { return orderId; }

  public void setName ( String _name ) { name = _name; }
  public String getName () { return name; }

  public void setAddress ( String _address ) { address = _address; }
  public String getAddress() { return address; }

  public void setAddress2 (String _address2) { address2 = _address2; }
  public String getAddress2 () { return address2; }

  public void setCity ( String _city ) { city = _city; }
  public String getCity () { return city; }

  public void setState (String _state) { state = _state; }
  public String getState () { return state; }

  public void setZipCode(String _zipCode) { zipCode = _zipCode; }
  public String getZipCode() { return zipCode; }

  public void setCountry ( String _country ) { country = _country; }
  public String getCountry () { return country; }

  public void setMethod ( String _method ) { method = _method; }
  public String getMethod () { return method; }

  public void setAmount (double _amount) { amount = _amount; }
  public double getAmount () { return amount; }
 
  public void setInstructions ( String _instructions ) { instructions = _instructions; }
  public String getInstructions () { return instructions; }
  
  public void setPhone ( String _phone ) { phone = _phone; }
  public String getPhone () { return phone; }
  

}