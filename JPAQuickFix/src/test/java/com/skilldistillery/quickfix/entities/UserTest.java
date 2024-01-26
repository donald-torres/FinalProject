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

class UserTest {
	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private User user;

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
		user = em.find(User.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
		
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("(456)-456-4562",user.getPhone());
	}
	
	@Test
	void ManyToOne_Address_User() {
		assertNotNull(user);
		assertEquals("Denver", user.getAddress().getCity());
	}

}
