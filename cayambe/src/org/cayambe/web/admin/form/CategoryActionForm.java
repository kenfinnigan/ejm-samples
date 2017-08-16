/*
 * CategoryActionForm.java
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
import org.cayambe.core.CategoryVO;
import org.apache.log4j.Category;


public final class CategoryActionForm extends ActionForm {

    private static String CLASSNAME = CategoryActionForm.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    // --------------------------------------------------- Instance Variables


    private String categoryId = "";
    private String parentId = "";
    private String name = null;
    private String header = null;
    private String imagePath = null;
    private boolean selected = false;



    // --------------------------------------------------------- Public Methods
    
	public String getCategoryId() { return (this.categoryId); }
    public void setCategoryId(String _categoryId) { this.categoryId = _categoryId; }

    public String getParentId() { return (this.parentId); }
    public void setParentId(String _parentId) { this.parentId = _parentId; }

    public String getName() { return (this.name); }
    public void setName(String _name) { this.name = _name; }

    public String getHeader() { return (this.header); }
    public void setHeader(String _header) { this.header = _header; }

    public String getImagePath() { return (this.imagePath); }
    public void setImagePath(String _imagePath) { this.imagePath = _imagePath; }

    public boolean isSelected() { return (this.selected); }
    public void setSelected(boolean _selected) { this.selected = _selected; }



    /**
     * RESET all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
	
    public void setForm(CategoryVO c) {
	  setCategoryId(c.getId().toString());
	  setParentId(c.getParentId().toString());
	  setName(c.getName());
	  setHeader(c.getHeader());
	  setImagePath(c.getImagePath());
	  setSelected(c.isSelected());
    }

    public CategoryVO toVO() {
	  CategoryVO c = new CategoryVO();
	  c.setId(new Long(getCategoryId()));
	  c.setParentId(new Long(getParentId()));
	  c.setName(getName());
	  c.setHeader(getHeader());
	  c.setImagePath(getImagePath());
	  c.setSelected(isSelected());
	  return c;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {

        this.categoryId = "";
        this.parentId = "";
        this.name = null;
        this.header = null;
        this.imagePath = null;
    	this.selected = false;

    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

		cat.debug("VALIDATING: CategoryActionForm");
        ActionErrors errors = new ActionErrors();

		cat.debug("Name is: " + name);

		if( name == null || name.length()== 0 ){
		  cat.debug("setting categoryName in ActionErrors");
		  errors.add("categoryName",new ActionError("error.categoryName.required"));
        }

        return errors;

    }

}