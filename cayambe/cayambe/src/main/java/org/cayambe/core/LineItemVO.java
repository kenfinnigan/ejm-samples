package org.cayambe.core;

import java.io.Serializable;

public class LineItemVO implements Serializable
{
  private ProductVO product;
  private int quantity;
  private double salePrice;

  public LineItemVO( ProductVO _product, int _quantity, double _salePrice )
  {
    product = _product;
    quantity = _quantity;
	salePrice = _salePrice;
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


  public void setSalePrice(double _salePrice)
  {
    salePrice = _salePrice;
  }
  public double getSalePrice()
  {
    return salePrice;
  }

}
