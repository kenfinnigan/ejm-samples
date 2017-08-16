/*
 * CategoryVO.java
 *
 * Created on September 20, 2001, 9:43 PM
 */

package org.cayambe.core;

import java.io.Serializable;
import java.util.*;

/**
  * anatr:16.10: added 'implements CayambeTreeNode' for taglib usage
 *
*
 * @author  Mike Davis <mdavis@wantjava.com>
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 */
public class CategoryVO extends CayambeValueObject implements CayambeTreeNode
{
    
    private Collection children = null;
    private Long id = new Long("0");
    private Long parentId = new Long("0");
    private String name;
    private String header;
    private String imagePath;
    private boolean selected = false;

    /** Creates new CategoryVO */
    public CategoryVO()
    {
    }

    public CategoryVO(String _id)
    {
      if( _id != null ){
        id = new Long(_id);
      }
    }

    public Collection getChildren()
    {
      if( children == null ){
        children = new HashSet();
      }
      return children;
    }

    public void addChild( CategoryVO child )
    {
      if( children == null ){
        children = new HashSet();
      }
      children.add(child);
    }

    public Long getId()
    {
        return id;
    }

	public Long getCategoryId()
    {
        return id;
    }

    public void setId( Long _id )
    {
        id = _id;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId( Long _parentId )
    {
        parentId = _parentId;
    }


    public String getName()
    {
        return name;
    }

    public void setName( String _name )
    {
        name = _name;
    }

    public String getHeader()
    {
      return header;
    }

    public void setHeader( String _header )
    {
      header = _header;
    }

    public String getImagePath()
    {
      return imagePath;
    }

    public void setImagePath( String _imagePath )
    {
      imagePath = _imagePath;
    }

    public boolean hasChildren()
    {
      if( children == null || children.size() < 1 ){
        return false;
      }
      return true;
    }

    public boolean isSelected()
    {
      return selected;
    }

    public void setSelected( boolean _selected )
    {
      selected = _selected;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(255);
        sb.append("\nCategoryVO {");
        sb.append("\n\tId: " + id);
        sb.append("\n\tParent Id: " + parentId);
		sb.append("\n\tName: " + name);
		sb.append("\n\tHeader: " + header);
        sb.append("\n}");
        return sb.toString();
    }


    public boolean equals(Object o) {
        if (!(o instanceof CategoryVO))
            return false;
        CategoryVO c = (CategoryVO)o;
        return c.getId().equals(id);
    }

    public int compareTo(Object o) {
        CategoryVO c = (CategoryVO)o;
        return id.compareTo(c.getId());
    }

}
