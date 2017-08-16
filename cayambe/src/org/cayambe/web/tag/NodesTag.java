package org.cayambe.web.tag;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.TreeMap;
import java.util.Stack;
import java.io.IOException;
import org.apache.log4j.Category;


/*
 * NodesTag
 * --------
 * After getting the iterator  from the outer TreeTag (an exception is thrown
 * if not inside one), maintains the current node, for reference by the inner
 * node attribute tags, then processes the body once for every node in the
 * tree.
 *
 * @author  Anat Rozenzon <anatr@mail.com>
 * @version 0.1
 */

public class NodesTag extends BodyTagSupport {

  private static String CLASSNAME = NodesTag.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);    
    
  private TreeMap tree = null;
  private NodeDisplayDetails nodeDetails = null;
  
  //For stack of trees maintenance
  private Stack expandedTrees = new Stack();
  boolean afterExpanding = false;

  public int doStartTag() throws JspTagException {

    // check if items tag is in list tag
    TreeTag treeTag = (TreeTag) findAncestorWithClass(this, TreeTag.class);
    if (treeTag == null) {
      throw new JspTagException("NodesTag: NodesTag not inside TreeTag");
    }
    tree = treeTag.getTree();

    if (tree == null) return(SKIP_BODY);
    
    nodeDetails = (NodeDisplayDetails)tree.firstKey();
        
    return(EVAL_BODY_TAG);

  }

  // process the body again with the next item if it exists
  public int doAfterBody() {
      
      //Now manage the tree & stack of branches
     
    // if this item is expanding branch 
      if (nodeDetails.isExpandingBranch()) {
     
          //check if this node is already in waiting stack (i.e. we finished expanding it)
          if (afterExpanding) {
              
              //Finished this node
              tree.remove(nodeDetails);
              
              //check for other nodes in this tree
              if (tree != null && !tree.isEmpty()) {
                nodeDetails = (NodeDisplayDetails)tree.firstKey();
                afterExpanding = false;
              }
              //check for waiting trees in stack
              else {
                if (expandedTrees.isEmpty()){
                    return SKIP_BODY;
                }

                //get last inserted tree
                tree = (TreeMap)expandedTrees.pop();
            
                //this the node we just finished expanded,
                // go over it again to get the 'AFTER_CHILDREN' part
                nodeDetails = (NodeDisplayDetails)tree.firstKey();
              }
          }
          else {
            
            //brothers of this node should wait
            expandedTrees.push(tree);

            //now tree contains the children of this node
            tree = (TreeMap)tree.get(nodeDetails);
            nodeDetails = (NodeDisplayDetails)tree.firstKey();
        
          }

    }
    else {
        //remove previous node from tree
        tree.remove(nodeDetails);

        // if this tree has still items, continue
        if (tree != null && !tree.isEmpty()) {            
            nodeDetails = (NodeDisplayDetails)tree.firstKey();
        }
        //this tree is finished
        else {
            if (expandedTrees.isEmpty()){
                return SKIP_BODY;
            }
            
            //get last inserted tree
            tree = (TreeMap)expandedTrees.pop();
            
            //this the node we just finished expanded,
            // go over it again to get the 'AFTER_CHILDREN' part
            nodeDetails = (NodeDisplayDetails)tree.firstKey();
            afterExpanding = true;
        }
    }
    return(EVAL_BODY_TAG);
           
  }

  // print out the resulting body content to the JSP page and evaluate the
  // rest of the page
  public int doEndTag() {
    try {
      BodyContent body = getBodyContent();
      if (body != null) {
        JspWriter out = body.getEnclosingWriter();
        out.print(body.getString());
      }
    } catch(IOException ioe) {
      cat.info("Error handling NodesTag: " + ioe);
    }
    return(EVAL_PAGE);
  }
  
  
  // getters for inner tags
  public boolean isAfterExpanding() {
    return afterExpanding;
  }
  public NodeDisplayDetails getNodeDisplayDetails() {
      return nodeDetails;
  }
  
  /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        tree = null;
        nodeDetails = null;
        expandedTrees = null;
    }  
        
}


