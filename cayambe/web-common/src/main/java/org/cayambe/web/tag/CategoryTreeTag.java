package org.cayambe.web.tag;

import org.cayambe.core.CategoryVO;
import org.cayambe.core.CayambeTreeNode;
import org.cayambe.client.CategoryDelegate;

import java.util.*;

/**
 * @author  Anat Rozenzon <anatr@mail.com>
 * @version 0.1
 */

public class CategoryTreeTag extends TreeTag
{
  protected String DEFAULT_TOP_TREE_NAME = "Categories:";

  CategoryDelegate cDelegate = new CategoryDelegate();
  CategoryVO root;
  
  protected Collection findNodesToExpand() {
      
      Hashtable categories = cDelegate.getAllCategories();

      //at the moment, the nodes to expand are all ancestors of selectedNode
      Collection nodesToExpand = new HashSet();
      
      CategoryVO current = (CategoryVO)categories.get(selectedNodeId);
      //check that this has children
      if (current.hasChildren())
        nodesToExpand.add(current);
      
      //it is assumed that every node has node =0 as an ancestor
      // just to make sure I added a counter
      int counter = 0;

      while (current.getId().intValue() != 0 && counter++ <= 1000) {
          Long parentId = current.getParentId();
          //take care of root node
          if (!parentId.equals(current.getId())) {
            current = (CategoryVO)categories.get(parentId);
            nodesToExpand.add(current);
          }
          else
            break;
      }
      
      if (current.getId().intValue() == 0)
          root = current;
      else {//endless loop
          return null;
      }
      
      return nodesToExpand;
  }
  
          
  protected CayambeTreeNode getRootNode(){
      
      if (root != null)
          return root;
      else
        return cDelegate.getCategory(new CategoryVO("0"));
  }
  
  
      /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        cDelegate = null;
        root = null;
    }
}