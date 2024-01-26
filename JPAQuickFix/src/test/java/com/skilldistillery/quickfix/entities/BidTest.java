package com.skilldistillery.quickfix.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class BidTest {
	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Bid bid;

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
		bid = em.find(Bid.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		bid = null;
		
	}

	@Test
	void basic_mapping() {
		assertNotNull(bid);
		assertEquals("9000.0", bid.getAmount());
	}
		@Test
		void many_to_one_mapping_for_job_post() {
			assertNotNull(bid);
			assertEquals(1, bid.getJobPost().getId());
	}

}
