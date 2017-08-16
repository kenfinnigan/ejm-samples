/*
 * ProductActionForm.java
 *
 * Created on September 22, 2001, 1:36 AM
 */

package org.cayambe.web.admin.form;

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
import org.cayambe.core.ProductVO;
import java.text.NumberFormat;
import java.util.Locale;
import org.cayambe.util.CayambeProperties;


public final class ProductActionForm extends ActionForm {


    // --------------------------------------------------- Instance Variables
    NumberFormat nf = NumberFormat.getCurrencyInstance(CayambeProperties.LOCAL);

    private String productId = null;
    private String title = null;
    private String desc = null;
    private double price = 0;
    private String image = null;
    private String SKU = null;
    private double salePrice = 0;
    private boolean onSale = false;
    private boolean visible = true;


    // --------------------------------------------------------- Public Methods

    public String getProductId() { return (this.productId); }
    public void setProductId(String _productId) { this.productId = _productId; }

    public String getTitle() { return (this.title); }
    public void setTitle(String _title) { this.title = _title; }

    public String getDesc() { return (this.desc); }
    public void setDesc(String _desc) { this.desc = _desc; }

    public double getPrice() { return (this.price); }
    public void setPrice(double _price) { this.price = _price; }
    public String getDollarPrice() { return (nf.format(price)); }

    public String getImage() { return (this.image); }
    public void setImage(String _image) { this.image = _image; }

    public String getSKU() { return (this.SKU); }
    public void setSKU(String _SKU) { this.SKU = _SKU; }

    public double getSalePrice() { return (this.salePrice); }
    public void setSalePrice(double _salePrice) { this.salePrice = _salePrice; }
	public String getDollarSalePrice() { return (nf.format(salePrice)); }

    public boolean isOnSale() { return (this.onSale); }
    public void setOnSale(boolean _onSale) { this.onSale = _onSale; }

    public boolean isVisible() { return (this.visible); }
    public void setVisible(boolean _visible) { this.visible = _visible; }

    /**
     * RESET all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void setForm(ProductVO p) {
	 setProductId(p.getProductId());
	 setTitle(p.getTitle());
	 setDesc(p.getDesc());
	 setPrice(p.getPrice());
	 setImage(p.getImage());
	 setSKU(p.getSKU());
	 setSalePrice(p.getSalePrice());
	 setOnSale(p.isOnSale());
	 setVisible(p.isVisible());
    }

	public ProductVO toVO() {
		ProductVO productVO = new ProductVO();
		productVO.setProductId(getProductId());
		productVO.setTitle(getTitle());
		productVO.setDesc(getDesc());
		productVO.setPrice(getPrice());
		productVO.setImage(getImage());
		productVO.setSKU(getSKU());
		productVO.setSalePrice(getSalePrice());
		productVO.setVisible(isVisible());
		productVO.setOnSale(isOnSale());
		return productVO;
	}

    public void reset(ActionMapping mapping, HttpServletRequest request) {

        this.productId = null;
        this.title = null;
        this.desc = null;
        this.price = 0;
        this.image = null;
        this.SKU = null;
        this.salePrice = 0;
        this.onSale = false;
        this.visible = true;

    }


    /**
     * VALIdATE the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();
/*
        if ((Title == null) || (Title.length() < 1))
            errors.add("Title", new ActionError("error.Title.required"));
*/

        return errors;

    }


}