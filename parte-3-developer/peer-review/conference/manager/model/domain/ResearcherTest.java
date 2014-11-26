package conference.manager.model.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;

public class ResearcherTest {

	ModelDatabase database;
	List<Researcher> researchers;
	List<Article> articles;
	List<Researcher> articleReviewers1;
	List<Researcher> articleReviewers2;
	List<Conference> conferences;
	Score score1;
	Article article1;
	Article nArticle;
	
	@Before
	public void setUp() throws Exception {
		database = new ModelDatabase(true);
		researchers = database.getAllResearchers();
		articles = database.getAllArticles();
		conferences = database.getAllConferences();
		nArticle = new Article(1, "", null, "");
	}
	
	@Test
	public void getResearcherIdTest() {
		assertEquals(researchers.get(0).getId(), 1);
		assertEquals(researchers.get(1).getId(), 2);
		assertEquals(researchers.get(2).getId(), 3);
	}
	
	@Test
	public void getNamet() {
		assertEquals(researchers.get(1).getName(), "Ana");
		assertEquals(researchers.get(2).getName(), "Manoel");
		assertEquals(researchers.get(3).getName(), "Joana");
	}
	
	@Test
	public void getResearchTopicTest() {
		assertEquals(researchers.get(0).
				getResearchTopics(), Arrays.
				asList("Software Product Lines", "Software Reuse", "Modularity"));
		assertEquals(researchers.get(1).
				getResearchTopics(), Arrays.
				asList("Software Architecture", "Modularity", "Software Reuse"));
	}
	
	@Test
	public void hasInterestInTrueTest() {
		assertTrue(researchers.get(0).hasInterestIn("Modularity"));
		assertTrue(researchers.get(0).hasInterestIn("Software Reuse"));
		assertTrue(researchers.get(0).hasInterestIn("Software Product Lines"));
		assertTrue(researchers.get(1).hasInterestIn("Software Architecture"));
		assertTrue(researchers.get(1).hasInterestIn("Modularity"));
		assertTrue(researchers.get(1).hasInterestIn("Software Reuse"));
	}
	
	@Test
	public void hasInterestInFalseTest() {
		assertFalse(researchers.get(0).hasInterestIn("Sotware Quality"));
		assertFalse(researchers.get(1).hasInterestIn("Software Testing"));
		assertFalse(researchers.get(2).hasInterestIn(""));
	}
	
	@Test
	public void hasInterestInFalseEmptyStringTest() {
		assertFalse(researchers.get(2).hasInterestIn(""));
	}
	
	@Test
	public void hasSameAffiliationTrueSameUniversityTest() {
		assertTrue(researchers.get(2).hasSameAffiliation(articles.get(0)));
		assertTrue(researchers.get(7).hasSameAffiliation(articles.get(1)));
	}
	
	@Test
	public void hasSameAffiliationTrueSameAuthorTest() {
		assertTrue(researchers.get(0).hasSameAffiliation(articles.get(0)));
		assertTrue(researchers.get(5).hasSameAffiliation(articles.get(1)));
	}
	
	@Test
	public void hasSameAffiliationFalseTest() {
		assertFalse(researchers.get(3).hasSameAffiliation(articles.get(0)));
		assertFalse(researchers.get(8).hasSameAffiliation(articles.get(1)));

	}
	
	public void hasSameAffiliationNullArticleTest() {
		assertFalse(researchers.get(1).hasSameAffiliation(nArticle));
	}
	
	@Test
	public void reviewsArticleTrueTest() throws ModelException {
		assertTrue(researchers.get(6).
				reviewsArticle(articles.get(1)));
		assertTrue(researchers.get(5).
				reviewsArticle(articles.get(5)));
	}
	
	@Test
	public void reviewsArticleFalseTest() throws ModelException {
		assertFalse(researchers.get(6).
				reviewsArticle(articles.get(2)));
		assertFalse(researchers.get(0).
				reviewsArticle(articles.get(0)));
	}
	
	@Test(expected = ModelException.class)
	public void reviewsArticleNullTest() throws ModelException {
		assertFalse(researchers.get(6).
				reviewsArticle(null));
	}
	
	@Test
	public void isAbleToReviewTrueTest() throws ModelException {
		assertTrue(researchers.get(1).
				isAbleToReview(articles.get(0)));
		assertTrue(researchers.get(5).
				isAbleToReview(articles.get(7)));
		
	}
	
	@Test
	public void isAbleToReviewFalseTestAlreadyAllocated() throws ModelException {
		assertFalse(researchers.get(5).
				isAbleToReview(articles.get(4)));
		assertFalse(researchers.get(1).
				isAbleToReview(articles.get(4)));
	}

	@Test
	public void isAbleToReviewFalseTestDifferentInterest() throws ModelException {
		assertFalse(researchers.get(2).
				isAbleToReview(articles.get(1)));
		assertFalse(researchers.get(4).
				isAbleToReview(articles.get(3)));
	}
	
	@Test
	public void isAbleToReviewFalseTestSameAffiliation() throws ModelException {
		assertFalse(researchers.get(0).
				isAbleToReview(articles.get(0)));
		assertFalse(researchers.get(2).
				isAbleToReview(articles.get(0)));
	}
	
	@Test(expected = ModelException.class)
	public void isAbleToReviewFalseNullReferenceTest() throws ModelException {
		assertFalse(researchers.get(0).
				isAbleToReview(null));
	}
}
