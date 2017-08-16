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

  private String nameOnCard = null;
  private String cardType = null;
  private String cardNumber = null;
  private int cardExpirationMonth = 0;
  private int cardExpirationYear = 0;
  private String authorizationCode = null;

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

  public void setNameOnCard ( String _nameOnCard ) { nameOnCard = _nameOnCard; }
  public String getNameOnCard () { return nameOnCard; }

  public void setCardType ( String _cardType ) { cardType = _cardType; }
  public String getCardType () { return cardType; }

  public void setCardNumber ( String _cardNumber ) { cardNumber = _cardNumber; }
  public String getCardNumber () { return cardNumber; }

  public void setCardExpirationMonth ( int _cardExpirationMonth ) { cardExpirationMonth = _cardExpirationMonth; }
  public void setCardExpirationMonth ( String _cardExpirationMonth ) { 
	if (_cardExpirationMonth != null)
	{
	   cardExpirationMonth = Integer.parseInt ( _cardExpirationMonth ); 
	}
  }
  public int getCardExpirationMonth () { return cardExpirationMonth; }

  public void setCardExpirationYear ( int _cardExpirationYear ) { cardExpirationYear = _cardExpirationYear; }
  public void setCardExpirationYear ( String _cardExpirationYear ) { 
	if (_cardExpirationYear != null)
	{
      cardExpirationYear = Integer.parseInt ( _cardExpirationYear ); 
	}	
  }
  public int getCardExpirationYear () { return cardExpirationYear; }

  public void setAuthorizationCode ( String _authorizationCode ) { authorizationCode = _authorizationCode; }
  public String getAuthorizationCode () { return authorizationCode; }  

}
