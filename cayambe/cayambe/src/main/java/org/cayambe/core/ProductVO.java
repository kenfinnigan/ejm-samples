package org.cayambe.core;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
//import org.cayambe.util.CayambeProperties;

public class ProductVO implements Serializable
{

	public ProductVO () 
	{
	}
    
    private String productId = "0";
    private String title = null;
    private String desc = null;
    private double price = 0;
    private String image = null;
    private String sku = null;
    private double salePrice = 0;
    private boolean onSale = false;
    private boolean visible = true;
    private Integer categoryId = null;

    public String getProductId() { return (this.productId); }
    public void setProductId(String _productId) { this.productId = _productId; }

    public String getTitle() { return (this.title); }
    public void setTitle(String _title) { this.title = _title; }

    public String getDesc() { return (this.desc); }
    public void setDesc(String _desc) { this.desc = _desc; }

    public double getPrice() { return (this.price); }
    public void setPrice(double _price) { this.price = _price; }       
	public String getDollarPrice() { 
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return (nf.format(price)); 
	}

    public String getImage() { return (this.image); }
    public void setImage(String _image) { this.image = _image; }

    public String getSKU() { return (this.sku); }
    public void setSKU(String _sku) { this.sku = _sku; }

    public double getSalePrice() { return (this.salePrice); }
    public void setSalePrice(double _salePrice) { this.salePrice = _salePrice; }
	public String getDollarSalePrice() { 
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return (nf.format(salePrice)); 
	}

    public boolean isOnSale() { return onSale; }
    public String getOnSale() {
        String value = "No";
        if (isOnSale()) {
          value = "Yes";
        }
        return value;
    }
    public void setOnSale(boolean _onSale) { onSale = _onSale; }


    public boolean isVisible() { return visible; }
    public String getVisible() {
      String value = "No";
      if (isVisible()) {
        value = "Yes";
      }
      return value;
    }
    public void setVisible(boolean _visible) { visible = _visible; }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer(512);
      sb.append("ProductVO {");
      sb.append("\n\t ProductId: " + getProductId());
      sb.append("\n\t Title: " + getTitle());
      sb.append("\n\t Description: " + getDesc());
      sb.append("\n\t Image: " + getImage());
      sb.append("\n\t OnSale: " + isOnSale());
      sb.append("\n\t Price: " + getPrice());
      sb.append("\n\t SalePrice: " + getSalePrice());
      sb.append("\n\t SKU: " + getSKU());
      sb.append("\n\t CategoryId: " + getCategoryId());
      sb.append("\n}" );

      return sb.toString();

    }

}