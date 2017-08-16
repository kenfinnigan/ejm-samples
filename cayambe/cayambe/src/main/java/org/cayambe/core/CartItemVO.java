package org.cayambe.core;

import java.io.Serializable;

public class CartItemVO implements Serializable
{
  private ProductVO product;
  private int quantity;

  public CartItemVO( ProductVO _product, int _quantity )
  {
    product = _product;
    quantity = _quantity;
  }

  public CartItemVO( ProductVO _product )
  {
    this( _product, 1 );
  }

  public ProductVO getProduct()
  {
    return product;
  }

  public void setProduct(ProductVO _product)
  {
    product = _product;
  }

  public void setQuantity(int _quantity)
  {
    quantity = _quantity;
  }

  public int getQuantity()
  {
    return quantity;
  }

}
