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

class SpecialtyTest {
	private static EntityManagerFactory emf;

	private EntityManager em;

	private Specialty specialty;

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
		specialty = em.find(Specialty.class, 1);

	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		specialty = null;

	}

	@Test
	void test() {
		assertNotNull(specialty);
		assertEquals("General", specialty.getName());
	}

	@Test
	void MTM_Providers() {
		assertNotNull(specialty);
		assertTrue(specialty.getProviders().size() > 0);
	}

	@Test
	void MTO_Trade() {
		assertNotNull(specialty);
		assertEquals("General", specialty.getTrade().getName());
	}

}
