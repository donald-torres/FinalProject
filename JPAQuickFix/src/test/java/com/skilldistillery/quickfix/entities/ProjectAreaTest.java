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

class ProjectAreaTest {
	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private ProjectArea projectArea;

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
		projectArea = em.find(ProjectArea.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		projectArea = null;
		
	}

	@Test
	void basic_mapping() {
		assertNotNull(projectArea);
		assertEquals("Counter Tops", projectArea.getName());
	}
	
	@Test
	void many_to_many_mapping_for_job_post() {
		assertNotNull(projectArea);
		assertTrue(projectArea.getJobPosts().size() > 0);
}

}
