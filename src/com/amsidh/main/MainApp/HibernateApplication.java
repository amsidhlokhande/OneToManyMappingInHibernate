/**
 * 
 */
package com.amsidh.main.MainApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.amsidh.mvc.dtos.Address;
import com.amsidh.mvc.dtos.UserDetails;
import com.amsidh.mvc.dtos.Vechile;

/**
 * @author VIRU
 *
 */
public class HibernateApplication {
	public static final Logger logger = Logger.getLogger("HibernateApplication");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
      logger.info("SessionFactory created successfully");
      UserDetails userDetails=populateUserDetails();

      Session session=sessionFactory.openSession();
      session.beginTransaction();
      session.save(userDetails);
      for(Vechile vechile: userDetails.getVechiles())
      {
    	  vechile.setUserDetails(userDetails);
    	  session.save(vechile);  
      }
      
      session.getTransaction().commit();
      session.close();
	}

	public static UserDetails populateUserDetails()
	{
		//SimpleDateFormat sdf=new SimpleDateFormat("DD-MM-YY");
		
		UserDetails userDetails=null;
		try {
			userDetails = new UserDetails( "amsidh.lokhande", new Date(), populateAddress(), populateVechile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDetails;
	}
	public static Address populateAddress()
	{
		return new Address("KatePuram","Pune","MAHARASHTRA",new Long("411061"));
		//return new Address();
	}
	public static List<Vechile> populateVechile()
	{
		List<Vechile> vechiles=new ArrayList<Vechile>();
		vechiles.add(new Vechile("Two Wheeler", "MH-14 DZ-2018"));
		vechiles.add(new Vechile("Four Wheeler", "MH-14 DZ-8754"));
		return vechiles;
		//return new Vechile();
	}
	
}
