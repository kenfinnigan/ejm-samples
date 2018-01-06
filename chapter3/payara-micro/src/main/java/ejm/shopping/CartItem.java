package ejm.shopping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ken Finnigan
 */
@XmlRootElement
public class CartItem {
    private String itemName;

    private Integer itemQuantity;

    public CartItem() {
    }

    public CartItem(String name, Integer qty) {
        this.itemName = name;
        this.itemQuantity = qty;
    }

    public String getItemName() {
        return itemName;
    }

    public CartItem setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public CartItem setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
        return this;
    }

    public CartItem increaseQuantity(Integer itemQuantity) {
        this.itemQuantity = this.itemQuantity + itemQuantity;
        return this;
    }
}
