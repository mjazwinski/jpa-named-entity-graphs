package com.acme.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	public static final String HIBERNATE_PU = "HibernatePU"; 
	
	public static final String ECLIPSELINK_PU = "EclipseLinkPU";
    
    public static EntityManager newEntityManager(String puName) {
        EntityManagerFactory eMF = Persistence.createEntityManagerFactory(puName);
        return eMF.createEntityManager();
    }

}
