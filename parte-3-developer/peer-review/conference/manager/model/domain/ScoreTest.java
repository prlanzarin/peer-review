package conference.manager.model.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.*;

import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;

public class ScoreTest {
	Score allocatedScore1;
	Score allocatedScore2;
	Score unallocatedScore1;
	Score unallocatedScore2;
	ModelDatabase database;
	List<Researcher> researchers;
	List<Article> articles;

	@Before
	public void setUp() throws Exception {
		database = new ModelDatabase(true);
		researchers = database.getAllResearchers();
		articles = database.getAllArticles();
		allocatedScore1 = new Score(researchers.get(7), articles.get(0), 2);
		allocatedScore2 = new Score(researchers.get(9), articles.get(0), -3);
		unallocatedScore1 = new Score(researchers.get(6), articles.get(1));
		unallocatedScore2 = new Score(researchers.get(0), articles.get(1));
	}

	@Test
	public void getArticleTest() {
		Article article1 = new Article(1, "Coupling and Cohesion ", researchers
				.get(0), "Modularity");
		Article article2 = new Article(2, "Design Patterns", researchers.get(5),
				"Software Reuse");
		assertEquals(article1, allocatedScore1.getArticle());
		assertEquals(article1, allocatedScore2.getArticle());
		assertEquals(article2, unallocatedScore1.getArticle());
		assertEquals(article2, unallocatedScore2.getArticle());
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
		assertEquals(researcher8, allocatedScore1.getReviewer());
		assertEquals(researcher10, allocatedScore2.getReviewer());
		assertEquals(researcher7, unallocatedScore1.getReviewer());
		assertEquals(researcher1, unallocatedScore2.getReviewer());
	}
	
	@Test
	public void getScoreTest(){
		assertEquals(2, allocatedScore1.getScore());
		assertEquals(-3, allocatedScore2.getScore());
	}

	@Test
	public void setScoreSuccessTest() {
		try {
			unallocatedScore1.setScore(2);
			unallocatedScore2.setScore(0);
		} catch (ModelException e) {
			fail();
		}
		assertEquals(3, unallocatedScore1.getScore());
		assertEquals(-3, unallocatedScore2.getScore());
	}

	@Test(expected = ModelException.class)
	public void setScoreTooBigScoreFailTest() throws ModelException {
		allocatedScore1.setScore(4);
	}

	@Test(expected = ModelException.class)
	public void setScoreTooSmallScoreFail2Test() throws ModelException {
		allocatedScore1.setScore(-4);
	}

	@Test
	public void isAllocatedTest() throws ModelException {
		unallocatedScore1.setScore(0);
		assertTrue(allocatedScore1.isAllocated());
		assertTrue(unallocatedScore1.isAllocated());
	}

	@Test
	public void isNotAllocatedTest() {
		assertFalse(unallocatedScore1.isAllocated());
	}

}
