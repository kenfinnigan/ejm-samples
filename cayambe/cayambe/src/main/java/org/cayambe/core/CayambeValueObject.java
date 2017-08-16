package org.cayambe.core;
import java.util.Hashtable;
import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Cayambe
 * @author Mike Davis <mdavis@wantjava.com>
 * @version 0.1
 */

public class CayambeValueObject extends Hashtable implements Serializable
{
  public String getAdditionalField( String key )
  {
    return (String)get( key );
  }

  public void setAddtionalField( String key, String value )
  {
    put( key, value );
  }
}