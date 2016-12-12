package com.HomeBudget.DAO.JPA;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.dataObject.DataAccessException;
 

public class JPADataAccessObject implements DataAccessObject1 {

	private EntityManagerFactory emfactory ;
//	@PersistenceContext(unitName="WebServices" , type=PersistenceContextType.EXTENDED) 
	 private EntityManager entitymanager;
    /**
     * Construct new JPADataAccessObject instance. This method will
     * open new JPA transactional session.
     */
    public JPADataAccessObject() {
        try {
            // open new session
         	if(emfactory==null)
     		{
     		emfactory = Persistence.createEntityManagerFactory("WebServices");
     		entitymanager = emfactory.createEntityManager();
     		}
                                         
            this.entitymanager.getTransaction().begin();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DataAccessException(
                   "Failed to create JPADataAccessObject", ex);
        }
    }
    /**
     * Construct and initialize new HibernateDataAccessObject instance.
     * 
     * @param dao Data access object used to get transaction object.
     */
    public JPADataAccessObject(JPADataAccessObject dao) {
        if (dao == null) {
            throw new DataAccessException("NULL dao parameter");
        }

        this.emfactory = dao.getEmfactory();
        this.entitymanager=dao.getEntitymanager();
    }

    
	/**
     * Construct and initialize new HibernateDataAccessObject instance.
     * 
     * @param daoSession Hibernate session object.
     */
    public JPADataAccessObject(EntityManagerFactory emfactory ,EntityManager entitymanager) {
        if (emfactory == null ||entitymanager==null) {
            throw new DataAccessException("NULL session parameter");
        }
        this.emfactory = emfactory;
        this.entitymanager=entitymanager;
    }
	@Override
	public void close() {
		// TODO Auto-generated method stub
		 try {
			  this.entitymanager.close();
	        } catch (Exception ex) {
	            throw new DataAccessException("Failed to close connection", ex);
	        }
		 }
	

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		  try {
			  this.entitymanager.getTransaction().commit();
	        } catch (Exception ex) {
	            throw new DataAccessException("Failed to commit transaction", ex);
	        }
		 }


	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		 try {       
			 this.entitymanager.getTransaction().rollback();
		 } catch (Exception ex) {
	            throw new DataAccessException("Failed to rollback transaction", ex);
	        }
	}

	public EntityManagerFactory getEmfactory() {
		return emfactory;
	}
	public void setEmfactory(EntityManagerFactory emfactory) {
		this.emfactory = emfactory;
	}
	public EntityManager getEntitymanager() {
		return entitymanager;
	}
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}
	@Override
	public void connect(DataAccessObject1 dao) {
		JPADataAccessObject accessObject=(JPADataAccessObject)dao;
		this.emfactory = accessObject.getEmfactory();
        this.entitymanager=accessObject.getEntitymanager();
		// TODO Auto-generated method stub
		 // throw new DataAccessException("connect operation is not supported");
		
	}
	@Override
	public void connect(Connection connection) {
		// TODO Auto-generated method stub
		 throw new DataAccessException("connect operation is not supported");
	}
}
