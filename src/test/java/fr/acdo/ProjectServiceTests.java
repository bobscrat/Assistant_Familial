package fr.acdo;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.acdo.domain.Family;
import fr.acdo.domain.Project;
import fr.acdo.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcdoApplication.class)
@Transactional
public class ProjectServiceTests {

	static Project project = new Project();
	static Family family = new Family();
	@Autowired
	ProjectService service;

	@Test
	public void testGetProjectByIdOk() {
		project = this.service.getProjectById((long) 1);
		assertEquals("Vacances 2017", project.getName());
	}

	@Test
	public void testGetProjectByIdeKo() {
		assertNull(service.getProjectById((long) 300));
	}

	@Test
	public void testGetProjectByIdClassOk() {
		project = service.getProjectById((long) 1);
		assertThat(project, instanceOf(Project.class));
	}

	@Test
	// @Rollback(true) reverts the database changes
	@Rollback(true)
	public void testSaveProjectOk() {
		family.setId((long) 2);
		family.setName("Team ACDO");
		project.setName("Nouveau Projet");
		project.setFamily(family);
		assertTrue(service.saveProject(project) != null);
	}

	@Test
	public void testSaveProjectNameKo() {
		family.setId((long) 2);
		family.setName("Team ACDO");
		project.setName(" ");
		project.setFamily(family);
		project.setId((long) 2);
		assertNotSame(service.saveProject(project), project);
	}

	@Test
	public void testSaveProjectFamilyKo() {
		project.setId((long) 2);
		project.setName("Nouveau nom de Projet");
		project.setFamily(family);
		assertNotSame(service.saveProject(project), project);
	}
}
