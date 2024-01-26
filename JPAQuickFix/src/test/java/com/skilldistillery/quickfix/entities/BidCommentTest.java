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

class BidCommentTest {
	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private BidComment bidComment;

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
		bidComment = em.find(BidComment.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		bidComment = null;
		
	}

	@Test
	void test() {
		assertNotNull(bidComment);
		assertEquals("comment", bidComment.getContent());
		
		
	}
	@Test
	void mto_connection_bid_table() {
		assertNotNull(bidComment);
		assertEquals(9000.0, bidComment.getBid().getAmount());
		
	}
	@Test
	void mto_connection_user_table() {
		assertNotNull(bidComment);
		assertEquals("Joe", bidComment.getUser().getFirstName());
		
	}

}
