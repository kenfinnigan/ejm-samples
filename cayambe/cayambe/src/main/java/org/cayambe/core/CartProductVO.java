package org.cayambe.core;

import java.io.Serializable;

public class CartProductVO implements Serializable {

    private String productId = "0";
    private int quantity = 0;

    public String getProductId() { return (this.productId); }
    public void setProductId(String _productId) { this.productId = _productId; }

    public int getQuantity() { return (this.quantity); }
    public void setQuantity(int _quantity) { this.quantity = _quantity; }

}
