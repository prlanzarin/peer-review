package conference.manager.model.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
	
	@Before
	public void setUp() throws Exception {
		database = new ModelDatabase(true);
		researchers = database.getAllResearchers();
		articles = database.getAllArticles();
		conferences = database.getAllConferences();
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
	public void hasSameAffiliationTrueTest() {
		assertTrue(researchers.get(2).hasSameAffiliation(articles.get(0)));
		assertTrue(researchers.get(7).hasSameAffiliation(articles.get(1)));
	}
	
	@Test
	public void hasSameAffiliationTrueTest2() {
		assertTrue(researchers.get(0).hasSameAffiliation(articles.get(0)));
		assertTrue(researchers.get(5).hasSameAffiliation(articles.get(1)));
	}
	
	@Test
	public void hasSameAffiliationFalseTest() {
		Article nArticle = new Article(1, "", null, "");
		assertFalse(researchers.get(3).hasSameAffiliation(articles.get(0)));
		assertFalse(researchers.get(8).hasSameAffiliation(articles.get(1)));
		assertFalse(researchers.get(1).hasSameAffiliation(nArticle));
	}
	
	@Test
	public void reviewsArticleTrueTest() {
		assertTrue(researchers.get(6).
				reviewsArticle(articles.get(1)));
		assertTrue(researchers.get(5).
				reviewsArticle(articles.get(5)));
	}
	
	@Test
	public void reviewsArticleFalseTest() {
		assertFalse(researchers.get(6).
				reviewsArticle(articles.get(2)));
		assertFalse(researchers.get(0).
				reviewsArticle(articles.get(0)));
	}
	
	@Test
	public void isAbleToReviewTrueTest() {
		assertTrue(researchers.get(1).
				isAbleToReview(articles.get(0)));
		assertTrue(researchers.get(5).
				isAbleToReview(articles.get(7)));
		
	}
	
	@Test
	public void isAbleToReviewFalseTestAlreadyAllocated() {
		assertFalse(researchers.get(5).
				isAbleToReview(articles.get(4))); //já avalia o artigo
		assertFalse(researchers.get(1).
				isAbleToReview(articles.get(4))); // já avalia na conf.
	}

	@Test
	public void isAbleToReviewFalseTestDifferentInterest() {
		assertFalse(researchers.get(2).
				isAbleToReview(articles.get(1)));
		assertFalse(researchers.get(4).
				isAbleToReview(articles.get(3)));
	}
	
	@Test
	public void isAbleToReviewFalseTestSameAffiliation() {
		assertFalse(researchers.get(0).
				isAbleToReview(articles.get(0))); //mesmo autor
		assertFalse(researchers.get(2).
				isAbleToReview(articles.get(0))); //mesma universidade
	}
}
