package com.HomeBudget.Sessions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionFactory {

	private EntityManagerFactory emfactory ;
	private EntityManager entitymanager;
	public SessionFactory()
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
