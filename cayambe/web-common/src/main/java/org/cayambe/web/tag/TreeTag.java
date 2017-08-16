package org.cayambe.web.tag;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.*;

import org.cayambe.core.CayambeTreeNode;
import org.apache.log4j.Category;

/**
 * @author  Anat Rozenzon <anatr@mail.com>
 * @version 0.1
 */

public abstract class TreeTag extends TagSupport
{
    
  private static String CLASSNAME = TreeTag.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);    
  
  protected String DEFAULT_TOP_TREE_NAME = "";
  
  protected TreeMap tree;
  protected String topTreeName;
    
    // tag attributes
  protected Long selectedNodeId = new Long("0");

  public int doStartTag()
  {
    
    try {
        tree = new TreeMap();
        
        // make sure attributes are valid
        if (selectedNodeId.intValue() < 0)
            selectedNodeId = new Long ("0");
        // make sure attributes are valid
        if (topTreeName == null)
            topTreeName = DEFAULT_TOP_TREE_NAME;

        Collection nodesToExpand = findNodesToExpand();
        // make sure nodesToExpand was set (should at least include selectedNode)
        if (nodesToExpand == null|| nodesToExpand.isEmpty()){
            cat.debug("TreeTag: Could not find nodesToExpand, skipping body");
            return(SKIP_BODY);
        }
    
       
        CayambeTreeNode root = getRootNode();
        if (root == null) {
            cat.debug("TreeTag: Could not find root node, skipping body");
            return(SKIP_BODY);
        }
        
        root.setName(topTreeName);
    
        /* tree structure is: 
                 *   key = Long nodeId,
                  *   value = NodeDisplayDetails details
                   *   nodeTypes: 1 - An expanding branch, 2- A branch that not expanding, 3 - A leaf
                */
        //put root and all his children in tree, expand the parents of selectedId
        TreeMap childrenTree = buildChildrenTree (root, nodesToExpand, selectedNodeId);
        tree.put(new NodeDisplayDetails (root, getNodeType(root, nodesToExpand),  
                                         (selectedNodeId.equals(root.getId())?true:false)),
                 childrenTree);
         
        
    }catch( Exception e ){
      e.printStackTrace();
    }
    
    return EVAL_BODY_INCLUDE;
     
  }
  
  // setters for attributes
  public void setSelectedNodeId(Long _selectedNodeId) {
        selectedNodeId = _selectedNodeId;
  }
  public void setTopTreeName(String _topTreeName) {
    topTreeName = _topTreeName;
  }

  // getter for inner tags
  public TreeMap getTree() {
    return tree;
  }
  

 /**
   * This method iteratively adds a node to a tree and if it 
   * should be expaneded, do the same for his children
  *TreeMap contains: key=child NodeDisplayDetails, value=child's childrenTreeMap
   */
  private TreeMap buildChildrenTree (CayambeTreeNode node, 
                          Collection nodesToExpand, Long selectedNodeId) {
                  
      TreeMap childrenTree = null;      
      
      Integer nodeType = getNodeType (node,nodesToExpand);

      //If this is expanding branch build the children tree
      if ( nodeType.equals(NodeDisplayDetails.EXPANDING_BRANCH) &&
           node.hasChildren() ){
          
          childrenTree = new TreeMap();

          //now add his children too (in what order??)
          Iterator children = node.getChildren().iterator();
          while (children.hasNext()) {
              CayambeTreeNode child = (CayambeTreeNode)children.next();
              if (node.equals(child)) {
                  cat.debug("TreeTag:buildChildrenTree:node==child WHY???, breaking...");              
                  break;
              }
              boolean isSelected = (child.getId()==selectedNodeId?true:false);
              Integer childNodeType = getNodeType (child,nodesToExpand);
              TreeMap childTree = buildChildrenTree (child, nodesToExpand, selectedNodeId);
              childrenTree.put(new NodeDisplayDetails (child, childNodeType,  isSelected),
                               childTree);
          } 
      }
      
      return childrenTree;
  }
   
  private Integer getNodeType (CayambeTreeNode node,Collection nodesToExpand) {
      
      if ( nodesToExpand.contains(node)) 
          return NodeDisplayDetails.EXPANDING_BRANCH;
      
      if (node.hasChildren())
          return NodeDisplayDetails.CLOSED_BRANCH;
      
      else
          return NodeDisplayDetails.LEAF;
  }
  
  /**
   * This method finds a Collection that holds thepath in tree from rootNode to selectedNode
   */                  
  protected abstract Collection findNodesToExpand();
  
  /**
   * This method get the rootNode of the tree
   */                  
  protected abstract CayambeTreeNode getRootNode();

  
  /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        tree = null;
    }


}