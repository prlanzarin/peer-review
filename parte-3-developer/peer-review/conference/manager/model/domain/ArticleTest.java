package conference.manager.model.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;

public class ArticleTest {

	ModelDatabase database;
	List<Researcher> researchers;
	List<Article> articles;
	List<Researcher> articleReviewers1;
	List<Researcher> articleReviewers2;
	Score score1;
	Article article1;
	
	@Before
	public void setUp() throws Exception {
		database = new ModelDatabase(true);
		researchers = database.getAllResearchers();
		articles = database.getAllArticles();
		articleReviewers1 = new ArrayList<Researcher>();
		articleReviewers2 = new ArrayList<Researcher>();
		articleReviewers1.add(researchers.get(7));
		articleReviewers1.add(researchers.get(9));
		articleReviewers2.add(researchers.get(6));
		articleReviewers2.add(researchers.get(1));
		article1 = new Article(1, "Coupling and Cohesion ", researchers
				.get(0), "Modularity");
		score1 = new Score(researchers.get(7), article1);
		score1.setScore(2);
		score1.getArticle().addScore(score1);
		score1.getArticle().addReviewer(score1.getReviewer());

	}
	
	@Test
	public void getIdTest() {
		assertEquals(articles.get(0).getId(), 1);
		assertEquals(articles.get(1).getId(), 2);
		assertEquals(articles.get(2).getId(), 3);
		assertEquals(articles.get(10).getId(), 11);
	}
	
	@Test
	public void getResearchTopicTest() {
		assertEquals(articles.get(0).getResearchTopic(), "Modularity");
		assertEquals(articles.get(1).getResearchTopic(), "Software Reuse");
		assertEquals(articles.get(2).getResearchTopic(), "Aspect-oriented Programming");
		assertEquals(articles.get(10).getResearchTopic(), "Software Testing");
	}
	
	@Test
	public void getAuthorTest() {
		assertEquals(articles.get(0).getAuthor(), researchers.get(0));
		assertEquals(articles.get(1).getAuthor(), researchers.get(5));
		assertEquals(articles.get(2).getAuthor(), researchers.get(6));
		assertEquals(articles.get(10).getAuthor(), researchers.get(9));
	}
	
	@Test
	public void getReviewersTest() {	
		assertEquals(articles.get(0).getReviewers(), articleReviewers1);
		assertEquals(articles.get(1).getReviewers(), articleReviewers2);
	}
	
	@Test
	public void getScoreTest() throws ModelException{
		assertEquals(articles.get(0).getScore(researchers.get(7)), score1);
	}
	
	@Test(expected = ModelException.class)
	public void getScoreNullTest() throws ModelException{
		articles.get(0).getScore(null);
	}
	
	@Test
	public void setScoreTestSuccess() {
		try {
			article1.setScore(researchers.get(7), -3);
			article1.setScore(researchers.get(7), 3);
			article1.setScore(researchers.get(7), 0);
		} catch (ModelException e) {
			fail();
		}
	}
	
	@Test(expected = ModelException.class)
	public void setScoreFailAboveTest() throws ModelException {
		article1.setScore(researchers.get(7), 4);
	}
	
	@Test(expected = ModelException.class)
	public void setScoreFailBelowTest() throws ModelException {
		article1.setScore(researchers.get(7), -4);
	}
	
	@Test(expected = ModelException.class)
	public void setScoreFailNullTest() throws ModelException {
		article1.setScore(null, -3);
	}
	
	@Test
	public void isAcceptedTrueTest() {	
		assertTrue(articles.get(1).isAccepted());
		assertTrue(articles.get(3).isAccepted());
		assertTrue(articles.get(2).isAccepted());
	}
	
	@Test
	public void isAcceptedFalseTest() {	
		assertFalse(articles.get(4).isAccepted());
	}
	
	@Test
	public void isAllocatedTrueTest() {	
		assertTrue(articles.get(1).isAllocated());
		assertTrue(articles.get(2).isAllocated());
		assertTrue(articles.get(3).isAllocated());
	}
	
	@Test
	public void isAllocatedFalseTest() {	
		assertFalse(articles.get(7).isAllocated());
		assertFalse(articles.get(8).isAllocated());
		assertFalse(articles.get(9).isAllocated());
	}
	
}
