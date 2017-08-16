package org.cayambe.web.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Category;

/*
 * NodeTag
 *
 * @author  Anat Rozenzon <anatr@mail.com>
 * @version 0.1
 */
public class NodeTag extends TagSupport {
    
  private static String CLASSNAME = NodeTag.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);
    
  private static final int BEFORE_CHILDREN = 0;
  private static final int AFTER_CHILDREN = 1;
  
    // tag attributes
  protected Integer nodeType = new Integer("-1");
  protected int part = -1;
  protected NodeDisplayDetails node;
  

  public int doStartTag() throws JspTagException {
      

    // make sure attributes are valid
    if (nodeType.intValue() < 0)
      throw new JspTagException("NodeTag: invalid nodeType");

    // check if items tag is in list tag
    NodesTag nodesTag = (NodesTag) findAncestorWithClass(this, NodesTag.class);
    if (nodesTag == null) {
      throw new JspTagException("NodeTag: NodeTag not inside NodesTag");
    }
    node = nodesTag.getNodeDisplayDetails();
    if (node == null) {
      throw new JspTagException("NodeTag: NodeTag not inside NodesTag (node==null)");
    }
    
    //if node is not of requested type just ignore
    if (node.getNodeType().intValue() != nodeType.intValue())
        return(SKIP_BODY);
    
    pageContext.setAttribute("node", node);
    //add/remove isSelected to request
    if (node.isSelected())
        pageContext.setAttribute( "isSelected","true", pageContext.PAGE_SCOPE);
    else
        pageContext.removeAttribute("isSelected",pageContext.PAGE_SCOPE);
    
    //if node is an expanding branch look for part attribute
    if (node.isExpandingBranch()){
        // make sure part attribute is valid
        if (part < 0)
            throw new JspTagException("NodeTag: invalid part");
        //if part match expanding state
        if ((part == BEFORE_CHILDREN && !nodesTag.isAfterExpanding()) || 
            (part == AFTER_CHILDREN && nodesTag.isAfterExpanding()))
            return(EVAL_BODY_INCLUDE);
        else return(SKIP_BODY);
            
    }
    
    return(EVAL_BODY_INCLUDE);
  }


  // print out the resulting body content to the JSP page and evaluate the
  // rest of the page
  public int doEndTag() {
    return(EVAL_PAGE);
  }
  // setters
  public void setNodeType(String nodeTypeStr) {
      if (nodeTypeStr.equalsIgnoreCase("closedBranch"))
          nodeType = NodeDisplayDetails.CLOSED_BRANCH;
      else if (nodeTypeStr.equalsIgnoreCase("expandingBranch"))
          nodeType = NodeDisplayDetails.EXPANDING_BRANCH;
      else 
          nodeType = NodeDisplayDetails.LEAF;
  }
  public void setPart(String _part) {
      if (_part.equalsIgnoreCase("before"))
          part = BEFORE_CHILDREN;
      if (_part.equalsIgnoreCase("after"))
          part = AFTER_CHILDREN;
  }
  
  public NodeDisplayDetails getNode() {
      return node;
  }
  /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        node = null;
        nodeType = null;
    }  
}


