package com.acme.criteria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.acme.common.EntityManagerUtil;

import pl.spiralarchitect.orders.model.Customer;
import pl.spiralarchitect.orders.model.CustomerId;

@RunWith(BlockJUnit4ClassRunner.class)
public class EclipselinkNamedEntityGraphTest {

    private static final String PHONES = "phoneNums";
    private static final String ORDERS = "orders";
	private static final String EMAIL = "email";
    
	private EntityManager newEntityManager;
    
    @Before
    public void init() {
        newEntityManager = EntityManagerUtil.newEntityManager(EntityManagerUtil.ECLIPSELINK_PU);
    }
    
    @After
    public void destroy() {
        newEntityManager.close();
    }
    @Test
    public void testFetchGraphWithFind() {
    	Map props = new HashMap();
    	props.put("javax.persistence.fetchgraph", newEntityManager.getEntityGraph(Customer.CUSTOMER_DATA));
    	CustomerId customerId = new CustomerId();
    	customerId.setId(1l);
    	customerId.setPnr("2001");
		Customer customer = newEntityManager.find(Customer.class, customerId, props);
    	boolean areOrderLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, ORDERS);
		assertFalse(areOrderLoaded);
    	boolean arePhonesLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, PHONES);
		assertFalse(arePhonesLoaded);
    }

    @Test
    public void testFetchGraph() {
    	TypedQuery<Customer> typedQuery = newEntityManager.createQuery("select c from Customer c", Customer.class);
    	typedQuery.setHint("javax.persistence.fetchgraph", newEntityManager.getEntityGraph(Customer.CUSTOMER_DATA));
    	List<Customer> resultList = typedQuery.getResultList();
    	Customer customer = resultList.get(0);
    	boolean areOrdersLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, ORDERS);
		assertFalse(areOrdersLoaded);
    	boolean arePhonesLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, PHONES);
		assertFalse(arePhonesLoaded);
    	boolean isEmail = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, EMAIL);
    	// 3.7.4 Use of Entity Graphs in find and query operations 
    	// The persistence provider is permitted to fetch additional entity state beyond that specified by a fetch
    	// graph or load graph. It is required, however, that the persistence provider fetch all state specified by the
    	// fetch or load graph.
    	//
    	// Because of above specification fragment EclipseLink loads this attribute even though it is not included in fetch graph
    	// also see https://java.net/jira/browse/JPA_SPEC-96
    	//
		//assertFalse(isEmail);
		assertTrue(isEmail);
    }
    
    @Test
    public void testFetchGraphWithPhones() {
    	TypedQuery<Customer> typedQuery = newEntityManager.createQuery("select c from Customer c", Customer.class);
    	typedQuery.setHint("javax.persistence.fetchgraph", newEntityManager.getEntityGraph(Customer.CUSTOMERS_WITH_PHONES));
    	List<Customer> resultList = typedQuery.getResultList();
    	Customer customer = resultList.get(0);
    	boolean areOrdersLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, ORDERS);
		assertFalse(areOrdersLoaded);
    	boolean arePhonesLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, PHONES);
		assertTrue(arePhonesLoaded);
    	boolean isEmail = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, EMAIL);
		assertTrue(isEmail);
    }
    
    @Test
    public void testLoadGraph() {
    	TypedQuery<Customer> typedQuery = newEntityManager.createQuery("select c from Customer c", Customer.class);
    	typedQuery.setHint("javax.persistence.loadgraph", newEntityManager.getEntityGraph(Customer.CUSTOMER_DATA));
    	List<Customer> resultList = typedQuery.getResultList();
    	Customer customer = resultList.get(0);
    	boolean areOrdersLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, ORDERS);
		assertFalse(areOrdersLoaded);
    	boolean arePhonesLoaded = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, PHONES);
    	assertFalse(arePhonesLoaded);
    	boolean isEmail = newEntityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(customer, EMAIL);
		assertTrue(isEmail);
    }

}
