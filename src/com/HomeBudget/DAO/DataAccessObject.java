package com.HomeBudget.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataAccessObject {

	private EntityManagerFactory emfactory ;
	private EntityManager entitymanager;
	public DataAccessObject(EntityManagerFactory entityManagerFactory)
	{
		this.emfactory=entityManagerFactory;
		entitymanager = emfactory.createEntityManager();
	}
	public DataAccessObject()
	{
		if(emfactory==null)
		{
		emfactory = Persistence.createEntityManagerFactory("WebServices");
		entitymanager = emfactory.createEntityManager();
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
}
