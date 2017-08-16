/*
 * OrderActionForm.java
 *
 * Created on May 29th, 2002, 1:36 AM
 */

package org.cayambe.web.form;

/**
 *
 * @author  jon rose
 * @version 0.1
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.cayambe.core.*;
import org.apache.log4j.Category;
import java.util.ArrayList;
import java.util.Collection;

public final class OrderActionForm extends ActionForm {

    private static String CLASSNAME = OrderActionForm.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    // --------------------------------------------------- Instance Variables
	private String date = null;
    private String status = "1";
    private String statusName = null;
    private String salesTaxDesc = null;
    private double salesTaxTotal = 0;
    private ArrayList lineItems = new ArrayList();

	private String shippingName = null;
    private String shippingAddress = null;
    private String shippingAddress2 = null;
    private String shippingCity = null;
	private String shippingState = null;
    private String shippingZipCode = null;
	private String shippingCountry = null;
    private String shippingPhoneNumber = null;
    private String shippingMethod = null;
    private double shippingAmount = 0;
    private String shippingInstructions = null;
    
	private String billingName = null;
    private String billingAddress = null;
    private String billingAddress2 = null;
    private String billingCity = null;
    private String billingState = null;
    private String billingZipCode = null;
    private String billingCountry = null;
    private String billingPhoneNumber = null;
    private String billingEmail = null;
    
	private String nameOnCard = null;
    private String cardNumber = null;
    private String cardType = null;
    private int cardExpirationMonth = 0;
    private int cardExpirationYear = 0;



    // --------------------------------------------------------- Public Methods
	public void setDate ( String _date ) 
	{
		date = _date;
	}
	public String getDate()
	{
		return date;
	}

	public void setStatus ( String _status ) 
	{
		status = _status;
	}
	public String getStatus()
	{
		return status;
	}

	public void setStatusName ( String _statusName ) 
	{
		statusName = _statusName;
	}
	public String getStatusName()
	{
		return statusName;
	}

	public void setSalesTaxDesc ( String _salesTaxDesc ) 
	{
		salesTaxDesc = _salesTaxDesc;
	}
	public String getSalesTaxDesc()
	{
		return salesTaxDesc;
	}

	public void setSalesTaxTotal ( double _salesTaxTotal ) 
	{
		salesTaxTotal = _salesTaxTotal;
	}
	public double getSalesTaxTotal()
	{
		return salesTaxTotal;
	}

	public void setLineItems ( Collection _lineItems ) 
	{
		lineItems = (ArrayList)_lineItems;
	}
	public Collection getLineItems()
	{
		return lineItems;
	}

    public void setShippingName( String _shippingName ) 
	{
		shippingName = _shippingName;
	}
    public String getShippingName( ) 
	{
		return shippingName;
	}

    public void setShippingAddress( String _shippingAddress ) 
	{
		shippingAddress = _shippingAddress;
	}
    public String getShippingAddress( ) 
	{
		return shippingAddress;
    }

    public void setShippingAddress2( String _shippingAddress2 ) 
	{
		shippingAddress2 = _shippingAddress2;
	}
    public String getShippingAddress2( ) 
	{
		return shippingAddress2;
	}

    public void setShippingCity( String _shippingCity ) 
	{
		shippingCity = _shippingCity;
	}
    public String getShippingCity( )
	{
		return shippingCity;
	}

    public void setShippingState( String _shippingState ) 
	{
		shippingState = _shippingState;
	}
    public String getShippingState( ) 
	{
		return shippingState;
	}

    public void setShippingZipCode( String _shippingZipCode ) 
	{
		shippingZipCode = _shippingZipCode;
	}
    public String getShippingZipCode( ) 
	{
		return shippingZipCode;
	}

    public void setShippingCountry( String _shippingCountry ) 
	{
		shippingCountry = _shippingCountry;
	}
    public String getShippingCountry( ) 
	{
		return shippingCountry;
	}

    public void setShippingPhoneNumber( String _shippingPhoneNumber ) 
	{
		shippingPhoneNumber = _shippingPhoneNumber;
	}
    public String getShippingPhoneNumber( ) 
	{
		return shippingPhoneNumber;
	}

    public void setShippingMethod( String _shippingMethod ) 
	{
		shippingMethod = _shippingMethod;
	}
    public String getShippingMethod( ) 
	{
		return shippingMethod;
	}
	
    public void setShippingAmount( double _shippingAmount ) 
	{
		shippingAmount = _shippingAmount;
	}
    public double getShippingAmount( ) 
	{
		return shippingAmount;
	}
    
	public void setShippingInstructions( String _shippingInstructions ) 
	{
		shippingInstructions = _shippingInstructions;
	}
    public String getShippingInstructions( ) 
	{
		return shippingInstructions;
	}

	
	
    public void setBillingName( String _billingName )
    {
        billingName = _billingName;
    }
    public String getBillingName( )
    {
        return billingName;
    }

    public void setBillingAddress( String _billingAddress )
    {
        billingAddress = _billingAddress;
    }
    public String getBillingAddress( )
    {
        return billingAddress;
    }

    public void setBillingAddress2( String _billingAddress2 )
    {
        billingAddress2 = _billingAddress2;
    }
    public String getBillingAddress2( )
    {
        return billingAddress2;
    }

    public void setBillingCity( String _billingCity )
    {
        billingCity = _billingCity;
    }
    public String getBillingCity( )
    {
        return billingCity;
    }

    public void setBillingState( String _billingState )
    {
        billingState = _billingState;		
    }
    public String getBillingState( )
    {
        return billingState;
    }

    public void setBillingZipCode( String _billingZipCode )
    {
        billingZipCode = _billingZipCode;
    }
    public String getBillingZipCode( )
    {
        return billingZipCode;
    }

    public void setBillingCountry( String _billingCountry )
    {
        billingCountry = _billingCountry;
    }
    public String getBillingCountry( )
    {
        return billingCountry;
    }

    public void setBillingPhoneNumber( String _billingPhoneNumber )
    {
        billingPhoneNumber = _billingPhoneNumber;
    }
    public String getBillingPhoneNumber( )
    {
        return billingPhoneNumber;
    }

	public void setBillingEmail( String _billingEmail ) 
	{
		billingEmail = _billingEmail;
	}
    public String getBillingEmail( ) 
	{
		return billingEmail;
	}

	

	public void setNameOnCard( String _nameOnCard ) 
	{
		nameOnCard = _nameOnCard;
	}
    public String getNameOnCard( ) 
	{
		return nameOnCard;
	}
	
	public void setCardNumber( String _cardNumber ) 
	{
		cardNumber = _cardNumber;
	}
    public String getCardNumber( ) 
	{
		return cardNumber;
	}
	
	public void setCardType( String _cardType ) 
	{
		cardType = _cardType;
	}
    public String getCardType( ) 
	{
		return cardType;
	}
	
	public void setCardExpirationMonth( int _cardExpirationMonth ) 
	{
		cardExpirationMonth = _cardExpirationMonth;
	}
    public int getCardExpirationMonth( ) 
	{
		return cardExpirationMonth;
	}
	
	public void setCardExpirationYear( int _cardExpirationYear ) 
	{
		cardExpirationYear = _cardExpirationYear;
	}
    public int getCardExpirationYear( ) 
	{
		return cardExpirationYear;
	}

    public ShippingInfoVO toShippingInfoVO() {
	    ShippingInfoVO s = new ShippingInfoVO();

		s.setName ( getShippingName() );
		s.setAddress ( getShippingAddress() );
		s.setAddress2 ( getShippingAddress2() );
		s.setCity ( getShippingCity() );
		s.setState ( getShippingState() );
		s.setZipCode( getShippingZipCode() );
		s.setCountry ( getShippingCountry() );
		s.setMethod ( getShippingMethod() );
		s.setAmount ( getShippingAmount() );
		s.setInstructions ( getShippingInstructions() ); 
		s.setPhone ( getShippingPhoneNumber() );
  
	    return s;
    }

	public BillingInfoVO toBillingInfoVO() 
	{
		BillingInfoVO b = new BillingInfoVO();

		b.setName ( getBillingName() );
		b.setAddress ( getBillingAddress() );
		b.setAddress2 ( getBillingAddress2() );
		b.setCity ( getBillingCity() );
		b.setState ( getBillingState() );
		b.setZipCode ( getBillingZipCode() );
		b.setCountry ( getBillingCountry() );
        b.setPhone ( getBillingPhoneNumber() );
		b.setEmail ( getBillingEmail() );

		b.setNameOnCard( getNameOnCard() );
		b.setCardType( getCardType() );
		b.setCardNumber ( getCardNumber() );
		b.setCardExpirationMonth ( getCardExpirationMonth() );
		b.setCardExpirationYear ( getCardExpirationYear() );		

		return b;
	}

    public void setForm( OrderVO orderVO ) {
		ShippingInfoVO s = orderVO.getShippingInfoVO();
		BillingInfoVO b = orderVO.getBillingInfoVO();

		setShippingName( s.getName() );
		setShippingAddress( s.getAddress() );
		setShippingAddress2( s.getAddress2() );
		setShippingCity( s.getCity () );
		setShippingState( s.getState() );
		setShippingZipCode( s.getZipCode() );
		setShippingCountry( s.getCountry() );
		setShippingMethod( s.getMethod() );
		setShippingAmount( s.getAmount() );
		setShippingInstructions( s.getInstructions() ); 
		setShippingPhoneNumber( s.getPhone() );

		setBillingName( b.getName() );
		setBillingAddress( b.getAddress() );
		setBillingAddress2( b.getAddress2() );
		setBillingCity( b.getCity() );
		setBillingState( b.getState() );
		setBillingZipCode( b.getZipCode() );
		setBillingCountry( b.getCountry() );
        setBillingPhoneNumber( b.getPhone() );
		setBillingEmail( b.getEmail() );

		setNameOnCard( b.getNameOnCard() );
		setCardType( b.getCardType() );
		setCardNumber( b.getCardNumber() );
		setCardExpirationMonth( b.getCardExpirationMonth() );
		setCardExpirationYear( b.getCardExpirationYear() );		

	}


	public OrderVO toOrderVO() 
	{
		OrderVO o = new OrderVO();
		o.setShippingInfoVO( toShippingInfoVO() );
		o.setBillingInfoVO( toBillingInfoVO() );
		return o;
	}

	/**
     * RESET all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */


    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();
        return errors;

    }

}
