package conference.manager.model.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import conference.manager.model.ModelException;
import conference.manager.model.domain.Article;
import conference.manager.model.domain.Conference;
import conference.manager.model.domain.Researcher;
import conference.manager.model.domain.Score;

public class ModelDatabase {

	private static final String SOFT_PROD_LINES = "Software Product Lines";

	private static final String SOFT_REUSE = "Software Reuse";

	private static final String MODULARITY = "Modularity";

	private static final String SOFT_ARCH = "Software Architecture";

	private static final String SOFT_TEST = "Software Testing";

	private static final String ASP_ORI_PROG = "Aspect-oriented Programming";

	private static final String SOFT_QUALITY = "Software Quality";

	private static final String UFRGS = "UFRGS";

	private static final String USP = "USP";

	private static final String UFRJ = "UFRJ";

	private List<Article> articles;

	private List<Conference> conferences;

	private List<Researcher> researchers;

	public ModelDatabase(boolean initData) {
		articles = new ArrayList<Article>();
		conferences = new ArrayList<Conference>();
		researchers = new ArrayList<Researcher>();
		if (initData) {
			initData();
		}
	}

	public List<Researcher> getAllResearchers() {
		return researchers;
	}

	public List<Conference> getAllConferences() {
		return conferences;
	}

	public List<Article> getAllArticles() {
		return articles;
	}

	private void initData() {
		initResearchers();
		initArticles();
		initConferences();
		initScores();
	}

	private void initResearchers() {
		researchers.add(new Researcher(1, "João", UFRGS, Arrays.asList(
				SOFT_PROD_LINES, SOFT_REUSE, MODULARITY)));
		researchers.add(new Researcher(2, "Ana", USP, Arrays.asList(SOFT_ARCH,
				MODULARITY, SOFT_REUSE)));
		researchers.add(new Researcher(3, "Manoel", UFRGS, Arrays.asList(
				SOFT_PROD_LINES, SOFT_TEST)));
		researchers.add(new Researcher(4, "Joana", UFRJ, Arrays.asList(
				SOFT_PROD_LINES, SOFT_REUSE, SOFT_ARCH, ASP_ORI_PROG)));
		researchers.add(new Researcher(5, "Miguel", UFRGS, Arrays.asList(
				SOFT_ARCH, MODULARITY, SOFT_TEST)));
		researchers.add(new Researcher(6, "Beatriz", UFRJ, Arrays.asList(
				SOFT_REUSE, SOFT_TEST, ASP_ORI_PROG)));
		researchers.add(new Researcher(7, "Suzana", UFRGS, Arrays.asList(
				ASP_ORI_PROG, MODULARITY, SOFT_REUSE)));
		researchers.add(new Researcher(8, "Natasha", UFRJ, Arrays.asList(
				MODULARITY, SOFT_REUSE, SOFT_QUALITY, SOFT_PROD_LINES)));
		researchers.add(new Researcher(9, "Pedro", USP, Arrays.asList(
				ASP_ORI_PROG, SOFT_ARCH)));
		researchers.add(new Researcher(10, "Carlos", USP, Arrays.asList(
				SOFT_REUSE, MODULARITY, SOFT_TEST)));
	}

	private void initArticles() {
		articles.add(new Article(1, "Coupling and Cohesion ", researchers
				.get(0), MODULARITY));
		articles.add(new Article(2, "Design Patterns", researchers.get(5),
				SOFT_REUSE));
		articles.add(new Article(3, "AspectJ", researchers.get(6), ASP_ORI_PROG));
		articles.add(new Article(4, "Feature Model", researchers.get(7),
				SOFT_PROD_LINES));
		articles.add(new Article(5, "Architecture Recovery",
				researchers.get(8), SOFT_ARCH));
		articles.add(new Article(6, "Functional Testing", researchers.get(9),
				SOFT_TEST));
		articles.add(new Article(7, "COTs", researchers.get(5), SOFT_REUSE));
		articles.add(new Article(8, "Pointcut", researchers.get(6),
				ASP_ORI_PROG));
		articles.add(new Article(9, "Product Derivation", researchers.get(7),
				SOFT_PROD_LINES));
		articles.add(new Article(10, "Architecture Comformance", researchers
				.get(8), SOFT_ARCH));
		articles.add(new Article(11, "Structural Testing", researchers.get(9),
				SOFT_TEST));
	}

	private void initScores(){
		try{
		Score score1 = new Score(researchers.get(7), articles.get(0));
		score1.setScore(2);
		Score score2 = new Score(researchers.get(9), articles.get(0));
		score1.getArticle().addScore(score1);
		score1.getArticle().addReviewer(score1.getReviewer());
		score2.getArticle().addScore(score2);
		score2.getArticle().addReviewer(score2.getReviewer());
		
		Score score3 = new Score(researchers.get(6), articles.get(1));
		score3.setScore(2);
		Score score4 = new Score(researchers.get(1), articles.get(1));
		score4.setScore(3);
		score3.getArticle().addScore(score3);
		score3.getArticle().addReviewer(score3.getReviewer());
		score4.getArticle().addScore(score4);
		score4.getArticle().addReviewer(score4.getReviewer());
		
		Score score5 = new Score(researchers.get(3), articles.get(2));
		score5.setScore(-1);
		Score score6 = new Score(researchers.get(5), articles.get(2));
		score6.setScore(1);
		score5.getArticle().addScore(score5);
		score5.getArticle().addReviewer(score5.getReviewer());
		score6.getArticle().addScore(score6);
		score6.getArticle().addReviewer(score6.getReviewer());
		
		Score score7 = new Score(researchers.get(0), articles.get(3));
		score7.setScore(1);
		Score score8 = new Score(researchers.get(2), articles.get(3));
		score8.setScore(0);
		score7.getArticle().addScore(score7);
		score7.getArticle().addReviewer(score7.getReviewer());
		score8.getArticle().addScore(score8);
		score8.getArticle().addReviewer(score8.getReviewer());
		
		Score score9 = new Score(researchers.get(3), articles.get(4));
		score9.setScore(-3);
		Score score10 = new Score(researchers.get(4), articles.get(4));
		score10.setScore(-3);
		score9.getArticle().addScore(score9);
		score9.getArticle().addReviewer(score9.getReviewer());
		score10.getArticle().addScore(score10);
		score10.getArticle().addReviewer(score10.getReviewer());
		
		Score score11 = new Score(researchers.get(2), articles.get(4));
		score11.setScore(-1);
		Score score12 = new Score(researchers.get(5), articles.get(4));
		score12.setScore(0);
		score11.getArticle().addScore(score11);
		score11.getArticle().addReviewer(score11.getReviewer());
		score12.getArticle().addScore(score12);
		score12.getArticle().addReviewer(score12.getReviewer());
		
		
		} catch(ModelException e){
			
		}
	}
	
	private void initConferences() {
		conferences.add(new Conference("ICSE", researchers.subList(0, 7), articles.subList(0,1)));
		conferences.add(new Conference("FSE", researchers.subList(0, 7), articles.subList(1, 6)));
		conferences.add(new Conference("SBES", researchers.subList(3, 10), articles.subList(6,11)));
		conferences.get(0).setAllocatedArticles(true);
		conferences.get(1).setAllocatedArticles(true);
	}
}
