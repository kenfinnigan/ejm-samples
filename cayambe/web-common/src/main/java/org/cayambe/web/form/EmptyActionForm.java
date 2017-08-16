/*
 * EmptyActionForm.java
 *
 * Created on September 22, 2001, 1:36 AM
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

public final class EmptyActionForm extends ActionForm {


    // --------------------------------------------------- Instance Variables


    // --------------------------------------------------------- Public Methods

    /**
     * RESET all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

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
        return errors;

    }


}
