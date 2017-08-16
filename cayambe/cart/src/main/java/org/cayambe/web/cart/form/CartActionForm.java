package org.cayambe.web.cart.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public class CartActionForm extends ActionForm
{
  private String productId;
  private int quantity;

  public String getProductId()
  {
    return productId;
  }

  public void setProductId(String _productId)
  {
    productId = _productId;
  }

  public void setQuantity(String _quantity)
  {
    quantity = Integer.parseInt(_quantity);
  }

  public int getQuantity()
  {
    return quantity;
  }

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
  {
    ActionErrors errors = new ActionErrors();
    if( getProductId() == null )
    {
      ActionError err = new ActionError("");
      errors.add("cayambe", err);
    }

    return errors;
  }


}
