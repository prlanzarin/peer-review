package conference.manager.model.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.*;

import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;

public class ScoreTest {
	Score score1;
	Score score2;
	Score score3;
	Score score4;
	ModelDatabase database;
	List<Researcher> researchers;
	List<Article> articles;

	@Before
	public void setUp() throws Exception {
		database = new ModelDatabase(true);
		researchers = database.getAllResearchers();
		articles = database.getAllArticles();
		score1 = new Score(researchers.get(7), articles.get(0), 2);
		score2 = new Score(researchers.get(9), articles.get(0), -3);
		score3 = new Score(researchers.get(6), articles.get(1));
		score4 = new Score(researchers.get(0), articles.get(1));
	}

	@Test
	public void getArticleTest() {
		Article article1 = new Article(1, "Coupling and Cohesion ", researchers
				.get(0), "Modularity");
		Article article2 = new Article(2, "Design Patterns", researchers.get(5),
				"Software Reuse");
		assertEquals(article1, score1.getArticle());
		assertEquals(article1, score2.getArticle());
		assertEquals(article2, score3.getArticle());
		assertEquals(article2, score4.getArticle());
	}

	@Test
	public void getReviewersTest() {
		Researcher researcher8 = new Researcher(8, "Natasha", "UFRJ", Arrays.asList(
				"Modularity", "Software Reuse", "Software Quality", "Software Product Lines"));
		Researcher researcher10 = new Researcher(10, "Carlos", "USP", Arrays.asList(
				"Software Reuse", "Modularity", "Software Testing"));
		Researcher researcher7 = new Researcher(7, "Suzana", "UFRGS", Arrays.asList(
				"Aspect-oriented Programming", "Modularity", "Software Reuse"));
		Researcher researcher1 = new Researcher(1, "João", "UFRGS", Arrays.asList(
				"Software Product Lines", "Software Reuse", "Modularity"));
		assertEquals(researcher8, score1.getReviewer());
		assertEquals(researcher10, score2.getReviewer());
		assertEquals(researcher7, score3.getReviewer());
		assertEquals(researcher1, score4.getReviewer());
	}
	
	@Test
	public void getScoreTest(){
		assertEquals(2, score1.getScore());
		assertEquals(-3, score2.getScore());
	}

	@Test
	public void setScoreSuccessTest() {
		try {
			score3.setScore(2);
			score4.setScore(0);
		} catch (ModelException e) {
			fail();
		}
		assertEquals(2, score3.getScore());
		assertEquals(0, score4.getScore());
	}

	@Test(expected = ModelException.class)
	public void setScoreTooBigScoreFailTest() throws ModelException {
		score1.setScore(4);
	}

	@Test(expected = ModelException.class)
	public void setScoreTooSmallScoreFail2Test() throws ModelException {
		score1.setScore(-4);
	}

	@Test
	public void isAllocatedTest() throws ModelException {
		score3.setScore(0);
		assertTrue(score1.isAllocated());
		assertTrue(score3.isAllocated());
	}

	@Test
	public void isNotAllocatedTest() {
		assertFalse(score3.isAllocated());
	}

}
