package conference.manager.model.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import conference.manager.model.database.ModelDatabase;

public class ConferenceTest {
	Conference confICSE;
	Conference confFSE;
	Conference confSBES;

	@Before
	public void setUp() throws Exception {
		ModelDatabase dataBase = new ModelDatabase(true);
		List<Conference> conferences = dataBase.getAllConferences();
		confICSE = conferences.get(0);
		confFSE = conferences.get(1);
		confSBES = conferences.get(2);
	}

	@Test
	public void getNameTest() {
		assertTrue(confICSE.getName().equals("ICSE"));
		assertTrue(confFSE.getName().equals("FSE"));
		assertTrue(confSBES.getName().equals("SBES"));
	}

	@Test
	public void getArticlesTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getReviewersTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getAcceptedArticlesSuccessTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getAcceptedArticlesFailTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getRejectedArticlesSuccessTest() {
		fail("Not yet implemented");
	}

	@Test
	public void getRejectedArticlesFailTest() {
		fail("Not yet implemented");
	}

	@Test
	public void selectSuccessTest() {
		fail("Not yet implemented");
	}

	@Test
	public void selectFailTest() {
		fail("Not yet implemented");
	}

	@Test
	public void addScoreTest() {
		fail("Not yet implemented");
	}

	@Test
	public void addAcceptedArticleTest() {
		fail("Not yet implemented");
	}

	@Test
	public void addRejectedArticleTest() {
		fail("Not yet implemented");
	}

	@Test
	public void setAllocatedArticleTest() {
		fail("Not yet implemented");
	}

	@Test
	public void addScoresToArticleTest() {
		fail("Not yet implemented");
	}

	@Test
	public void selectReviewersTest() {
		fail("Not yet implemented");
	}

	@Test
	public void isScoredTest() {
		fail("Not yet implemented");
	}

}
