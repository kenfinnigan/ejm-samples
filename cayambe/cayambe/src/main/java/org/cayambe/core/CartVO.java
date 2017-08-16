package org.cayambe.core;

import java.io.Serializable;
import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @author Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */

public class CartVO extends CayambeValueObject
{  

  private String cartId = null;
  private HashMap items;
  private double totalCost = 0.0;

  public CartVO()
  {
    items = new HashMap(3);
  }

  public String getCartId () { return cartId; }
  public void setCartId ( String _cartId ) { this.cartId = _cartId; }

  public void addItem( ProductVO product, int quantity )
  {	 
    double unitCost = product.isOnSale()?product.getSalePrice():product.getPrice();
    addCost( unitCost, quantity );
    items.put( product.getProductId(), new CartItemVO(product, quantity) );
  }

  public void addItem( ProductVO product )
  {
    addItem( product, 1 );
  }

  public double getTotalCost()
  {
    return totalCost;
  }

  public void removeItem( String productId )
  {
    ProductVO product = ((CartItemVO)items.get(productId)).getProduct();
    double unitCost = product.isOnSale()?product.getSalePrice():product.getPrice();
    CartItemVO item = (CartItemVO)items.get( productId );
    subtractCost( unitCost, item.getQuantity() );
    items.remove( product.getProductId() );
  }

  public int getSize()
  {
    return items.size();
  }

  /*
     What does this method do Jon? I know what it does,
     but the method name is not descriptive at all. It should be renamed.
  */
  public Set getKeySet()
  {
    return items.keySet();
  }

  public Iterator getItemIterator() 
  {
	 return items.keySet().iterator();
  }

  public Collection getCartItems()
  {
    return items.values();
  }

  public CartItemVO getCartItem( String key )
  {
    return ( CartItemVO ) items.get( key );
  }


  public void updateQuanity( String productId, int quantity )
  {
    CartItemVO item = (CartItemVO)items.get(productId);
    subtractCost(
      item.getProduct().isOnSale()?item.getProduct().getSalePrice():item.getProduct().getPrice(),
      item.getQuantity()
    );

    item.setQuantity( quantity );

    addCost(
      item.getProduct().isOnSale()?item.getProduct().getSalePrice():item.getProduct().getPrice(),
      item.getQuantity()
    );
  }

  private void addCost( double unitCost, int quantity )
  {
    totalCost += (unitCost*quantity);
  }

  private void subtractCost( double unitCost, int quantity )
  {
    totalCost -= (unitCost*quantity);
  }


}
