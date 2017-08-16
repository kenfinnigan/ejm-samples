/*
 * CayambeTreeNode.java
 *
 * Created on October 16
 */

package org.cayambe.core;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author  anatr
 * @version 
 */
public interface CayambeTreeNode extends Serializable,Comparable{
    
    public static final Long EMPTY_PARENT = new Long("-1");
    
    public Collection getChildren();
    public Long getId();
    public Long getParentId();
    public String getName();
    public boolean hasChildren();
    public void setName(String name);
    public boolean equals(Object o);
    public int compareTo(Object o);


}