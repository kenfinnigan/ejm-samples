package org.cayambe.client;

import java.util.List;
import org.cayambe.ejb.*;
import org.cayambe.core.OrderVO;
import org.cayambe.util.*;
import org.apache.log4j.Category;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;


/**
 *
 * @author  Jon Rose <jonr@wantjava.com>
 * @version 0.1
 **/
public class CheckOutDelegate {
    
    CheckOutFacade facade;
    private static String CLASSNAME = CheckOutDelegate.class.getName();
    private Category cat = (Category)Category.getInstance(CLASSNAME);

    public CheckOutDelegate()
    {
      try{
        CheckOutFacadeHome home = (CheckOutFacadeHome)CayambeServiceLocator.getInstance()
                               .getHome( "CheckOutFacade", CheckOutFacadeHome.class );
        facade = (CheckOutFacade)home.create(); 
      }catch(Exception e){
        e.printStackTrace();
        cat.info(e.getMessage());
      }
    }

    public void Save( OrderVO orderVO )
    throws Exception
    {
      try{
        facade.Save( orderVO );
      }
      catch( RemoteException re ){
         re.printStackTrace();
         cat.info(re.getMessage());
         throw new Exception(re.getMessage());
      }
    }

}
