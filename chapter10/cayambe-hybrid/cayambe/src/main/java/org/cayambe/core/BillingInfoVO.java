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

public class BillingInfoVO implements Serializable {

  private Long billingId = null;
  private Long orderId =  null;
  private String name = null;
  private String address = null;
  private String address2 = null;
  private String city = null;
  private String state = null;
  private String zipCode = null;
  private String country = null;
  private String phone = null;
  private String email = null;

  private String cardToken = null;
  private String cardChargeId = null;

  public void setBillingId ( Long _billingId ) { billingId = _billingId; }
  public void setBillingId ( String _billingId ) { billingId = new Long ( _billingId ); }
  public Long getBillingId () { return billingId; }

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
  
  public void setPhone ( String _phone ) { phone = _phone; }
  public String getPhone () { return phone; }

  public void setEmail ( String _email ) { email = _email; }
  public String getEmail () { return email; }

  public void setCardToken ( String _cardToken ) { cardToken = _cardToken; }
  public String getCardToken () { return cardToken; }

  public void setCardChargeId ( String _cardChargeId ) { cardChargeId = _cardChargeId; }
  public String getCardChargeId () { return cardChargeId; }
}
