package org.cayambe.util;

import java.util.*;
import javax.naming.*;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.rmi.PortableRemoteObject;
import java.io.*;
import javax.sql.DataSource;
import org.apache.log4j.Category;

public class CayambeServiceLocator
{
  InitialContext context = null;
  private static CayambeServiceLocator me;
  private final String DATASOURCE = "java:/Climb";

  private static final String JNDI_EJB_PREFIX = "java:global/cayambe/cayambe/";

  private static String CLASSNAME = CayambeServiceLocator.class.getName();
  private Category cat = (Category)Category.getInstance(CLASSNAME);

  public EJBHome getHome(String name, Class clazz)
    throws Exception
  {
    try {
      Object objref = context.lookup(JNDI_EJB_PREFIX + name + "!" + clazz.getName());
      cat.debug("ServiceHandler: Getting Home..." + name + " " + clazz);
      EJBHome home = (EJBHome)PortableRemoteObject.narrow(objref, clazz);
      cat.debug("ServiceHandler: (EJBHome classloader == myclassloader) : "     
        + (home.getClass().getClassLoader() == getClass().getClassLoader()));
      return home;
    }
    catch(NamingException ex) {
      cat.info(ex.getMessage());
      throw new Exception("Cannot find service : " + name + " because " + ex.getMessage());
    }
  }

  public EJBObject getService(String id)
  throws Exception
  {
    if(id == null)
      throw new Exception("Service ID cannot be null");
    try {
      byte[] bytes = new String(id).getBytes();
      InputStream io = new ByteArrayInputStream(bytes);
      ObjectInputStream os = new ObjectInputStream(io);
      javax.ejb.Handle handle = (javax.ejb.Handle)os.readObject();
      ;
      return handle.getEJBObject();
    }
    catch(Exception ex) {
      cat.info(ex.getMessage());
      throw new Exception("Exception. Problem getting handle : ");
    }
  }

  public DataSource getDatasource()
  {
    DataSource ds = null;
    try
    {
      ds = ( DataSource )context.lookup( DATASOURCE );
    }
    catch( NamingException ne )
    {
      cat.info(ne.getMessage());
      ne.printStackTrace();
    }
    return ds;
  }

  public InitialContext getContext() {
	  return context;
  }

  public static CayambeServiceLocator getInstance() throws Exception
  {
    if(me == null)
      me = new CayambeServiceLocator();
    return me;
  }

  private CayambeServiceLocator() throws Exception
  {
    try {
      context = new InitialContext();
    }
    catch(NamingException ne) {           
      cat.info(ne.getMessage());
      throw new Exception("Error in making JNDI initial context.");
    }
  }
}
