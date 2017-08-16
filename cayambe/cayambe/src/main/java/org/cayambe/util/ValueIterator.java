/*
 * ValueIterator.java
 *
 * Created on September 20, 2001, 10:43 PM
 */

package org.cayambe.util;
import java.util.*;
import java.rmi.RemoteException;

/**
 *
 * @author  Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */
public interface ValueIterator {
    public int getSize();
    public List getList();
    public void setList( Collection collection );
    public void setList( LinkedList linkedList );
}

