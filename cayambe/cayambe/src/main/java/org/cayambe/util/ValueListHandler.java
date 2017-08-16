/*
 * ValueListHandler.java
 *
 * Created on September 20, 2001, 10:49 PM
 */

package org.cayambe.util;
import java.util.*;
import org.apache.log4j.Category;

/**
 *
 * @author  Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */
public class ValueListHandler implements ValueIterator
{
    protected LinkedList list = new LinkedList();
    
    private static String CLASSNAME = ValueListHandler.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    /** Creates new ValueListHandler */
    public ValueListHandler()
    {
    }

    public ValueListHandler(Collection collection)
    {
        setList(collection);
    }

    public List getList()
    {
        cat.debug("ValueListHandler:getList:collection.size=" + getSize() );
        return list;
    }

    public LinkedList getLinkedList() {
        return list;
    }


    public int getSize()
    {
        if( list != null ){
            return list.size();
        }
        return 0;
    }

    public void setList(Collection collection)
    {
        cat.debug("ValueListHandler:setList:collection.size="+collection.size());
        if( collection == null ){
            //throw new IteratorExcpetion("The List is empty");
        cat.debug("List is empty");
        }
        list = new LinkedList(collection);
        cat.debug("ValueListHandler:setList:list.size="+list.size());
    }

    public void setList(LinkedList linkedList)
    {
        if( linkedList == null ){
            //throw new IteratorExcpetion("The List is empty");
            cat.debug("List is empty");
        }
        list = new LinkedList(linkedList);
        cat.debug("setList: "+list.size());
    }

}
