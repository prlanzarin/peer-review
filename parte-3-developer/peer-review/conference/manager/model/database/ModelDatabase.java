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
			Conference conference1 = conferences.get(0);
			conference1.addScoreToArticle(articles.get(0), researchers.get(7), 2);
			conference1.addScoreToArticle(articles.get(0), researchers.get(9));
			
			Conference conference2 = conferences.get(1);
			conference2.addScoreToArticle(articles.get(1), researchers.get(6), 2);
			conference2.addScoreToArticle(articles.get(1), researchers.get(1), 3);
			conference2.addScoreToArticle(articles.get(2), researchers.get(3), -1);
			conference2.addScoreToArticle(articles.get(2), researchers.get(5), 1);
			conference2.addScoreToArticle(articles.get(3), researchers.get(0), 1);
			conference2.addScoreToArticle(articles.get(3), researchers.get(2), 0);
			conference2.addScoreToArticle(articles.get(4), researchers.get(3), -3);
			conference2.addScoreToArticle(articles.get(4), researchers.get(4), -3);
			conference2.addScoreToArticle(articles.get(5), researchers.get(2), -1);
			conference2.addScoreToArticle(articles.get(5), researchers.get(2), 0);
		
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
