package com.HomeBudget.Sessions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.descriptors.DescriptorEventManager;

import com.entities.models.*;
import com.DES;
import com.dataObject.CategoryVO;
import com.dataObject.CountryVO;
import com.dataObject.PurchaseVO;
import com.dataObject.UserVO;


public class UserSession extends SessionFactory{
//	


	public UserSession()
	{
	 super();
	}
	
	
	

	public ArrayList<UserVO> getAllUsers()
	{
		try
		{
		ArrayList<UserVO>userVOS=new ArrayList<UserVO>();
		Query query = (Query) getEntitymanager().createNamedQuery("User.findAll");
	    List<UserVO> userList =  query.getResultList();
		for (UserVO user : userList) {
			UserVO userVO=new UserVO();
			userVO.setAddress(user.getAddress());
			userVO.setName(user.getName());
			userVO.setId(user.getId());
			userVO.setCountryId(user.getCountryId());
			userVO.setCurrencyId(user.getCurrencyId());
			userVO.setEmail(user.getEmail());
			userVO.setPassword(user.getPassword());
			userVOS.add(userVO);
		}

	
		return userVOS;
	}catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}
	public void addUser(UserVO userVo) throws Exception
	{
		
		 User user =new User();
		try
		{
	    Country country=getEntitymanager().find(Country.class, userVo.getCountryId());
	    user.setCountry(country);
	    user.setStatusId(userVo.getStatusId());
	    user.setGenderId(userVo.getGenderId());
	    user.setMobileNumber(Integer.parseInt(userVo.getMobileNumebr()));
	    if(country==null)
	    {
	    	throw new Exception("There is No Country with ID "+userVo.getCountryId());
	    }else
	    {
					if(userVo.getName()!=null)
					{
						user.setName(userVo.getName());
					}else
					{
						throw new Exception("Name Should not be Null");
					}
					if(userVo.getCurrencyId()!=0)
					{
					  Currency currency=getEntitymanager().find(Currency.class, userVo.getCurrencyId());
					  user.setCurrency(currency);
					}else
					{
						throw new Exception("Currency  Should not be Null or 0");
					}
					if(userVo.getAddress()!=null)
					{
						user.setAddress(userVo.getAddress());
					}else
					{
						throw new Exception("Purchase English Description Should not be Null");
					}
					if(userVo.getEmail()!=null)
					{
						user.setEmail(userVo.getEmail());
					}else
					{
						throw new Exception("Email Should not be Null");
					}
					if(userVo.getPassword()!=null)
					{
						DES des=new DES();
						userVo.setPassword(new String(des.encrypt(userVo.getPassword().getBytes())));
						user.setPassword(userVo.getPassword());
					}else
					{
						throw new Exception("Password Should not be Null");
					}
					user.setCreationDate(new Date());
					getEntitymanager().getTransaction().begin();
					getEntitymanager().persist(user);
		
					getEntitymanager().getTransaction().commit();
	    }
		}catch(Exception e)
		{
			throw new Exception(e);
			
		}
	}
		public boolean checkMail(String mail) throws Exception
		{
			try
			{
				Query query = (Query) getEntitymanager().createNamedQuery("User.checkMail");
				query.setParameter("email", mail);
				List<User> user=(List<User>)query.getResultList();
				if(user.size()==0)
				{
					return false;
				}else
				{
					return true;
				}
				
				
			}catch(Exception e)
			{
				throw new Exception(e);
			}
			
		
		}
		
		/*/
*/		

}
