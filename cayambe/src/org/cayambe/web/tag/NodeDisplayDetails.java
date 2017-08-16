/*
 * NodeDisplayDetails.java
 *
 * Created on 16 ? 2001, 06:54
 */

package org.cayambe.web.tag;

import org.cayambe.core.CayambeTreeNode;

/**
 *Helper class for TreeTag and NodesTag
 * @author  anatr
 * @version 
 */
public class NodeDisplayDetails implements Comparable {


  //constants for node type
  public static final Integer LEAF = new Integer(1);
  public static final Integer EXPANDING_BRANCH = new Integer(2);
  public static final Integer CLOSED_BRANCH = new Integer(3);

  private Long nodeId;
  private String nodeName;
  private Integer nodeType;
  private Long parentId;
  private boolean selected;
  private CayambeTreeNode node;
      

      public NodeDisplayDetails (CayambeTreeNode _node, Integer _nodeType, 
                                 boolean _selected){
          nodeId = _node.getId();
          if (_node.getName() != null)
            nodeName = _node.getName();
          else
            nodeName = "";
          nodeType = _nodeType;
          if (_node.getParentId() != null)
            parentId = _node.getParentId();
          else
            parentId = new Long("0");
          selected = _selected;
      }
      
      public Long getNodeId () {
          return nodeId;
      }
      
      public String getNodeName () {
          return nodeName;
      }
      
      public Integer getNodeType () {
          return nodeType;
      }   
      public Long getParentId () {
          return parentId;
      }
      
      public boolean isSelected() {
          return selected;
      }   
      public boolean isLeaf () {
          return (nodeType.intValue() == LEAF.intValue()) ? true : false;
      }    
      public boolean isExpandingBranch () {
          return (nodeType.intValue() == EXPANDING_BRANCH.intValue()) ? true : false;
      }    
      public boolean isClosedBranch () {
          return (nodeType.intValue() == CLOSED_BRANCH.intValue()) ? true : false;
      }    

      //needed inorder to use it as key of Map
      public int compareTo(Object o) {
        NodeDisplayDetails n = (NodeDisplayDetails)o;
        return nodeName.compareTo(n.getNodeName());          
      }
      //just to complete Comparable recommended requirements 
      public boolean equals(Object o) {
        if (!(o instanceof NodeDisplayDetails))
            return false;
        NodeDisplayDetails n = (NodeDisplayDetails)o;
        return nodeName.equals(n.getNodeName());
      }    
      
      public String toString() {
          return "NodeDisplayDetails: id="+nodeId+",name="+nodeName+",type="+nodeType+",parent="+parentId;
      }
}
