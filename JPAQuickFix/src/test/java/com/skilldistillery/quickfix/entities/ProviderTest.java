package com.skilldistillery.quickfix.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ProviderTest {
	private static EntityManagerFactory emf;

	private EntityManager em;

	private Provider provider;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAQuickFix");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();

	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		provider = em.find(Provider.class, 1);

	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		provider = null;

	}

	@Test
	void test() {
		assertNotNull(provider);
		assertEquals("Gills Handy Service", provider.getCompany());
	}
	@Test
	void test_user() {
		assertNotNull(provider);
		assertNotNull(provider.getUser());
	}

	@Test
	void OTM_Bid() {
		assertNotNull(provider);
		assertTrue(provider.getBids().size() > 0);
	}

	@Test
	void MTM_Trade() {
		assertNotNull(provider);
		assertTrue(provider.getTrades().size() > 0);
	}
	
	@Test
	void MTM_Specialties() {
		assertNotNull(provider);
		assertTrue(provider.getSpecialties().size() > 0);
	}
	

}
