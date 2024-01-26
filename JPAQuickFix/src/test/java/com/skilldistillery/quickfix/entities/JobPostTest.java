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

class JobPostTest {
	private static EntityManagerFactory emf;

	private EntityManager em;

	private JobPost jobPost;

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
		jobPost = em.find(JobPost.class, 1);

	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		jobPost = null;

	}

	@Test
	void basic_mapping() {
		assertNotNull(jobPost);
		assertEquals("Kitchen Remodel", jobPost.getTitle());
	}

	@Test
	void many_to_many_mapping_for_project_area() {
		assertNotNull(jobPost);
		assertTrue(jobPost.getProjectAreas().size() > 0);
	}

	@Test
	void many_to_one_mapping_for_user() {
		assertNotNull(jobPost);
		assertEquals(1, jobPost.getUser().getId());
	}

	@Test
	void one_to_many_mapping_for_bid() {
		assertNotNull(jobPost);
		assertTrue(jobPost.getBids().size() > 0);
	}

	@Test
	void MTM_Trades() {
		assertNotNull(jobPost);
		assertTrue(jobPost.getTrades().size() > 0);
	}

	@Test
	void MTM_Specialties() {
		assertNotNull(jobPost);
		assertTrue(jobPost.getSpecialties().size() > 0);
	}

}
