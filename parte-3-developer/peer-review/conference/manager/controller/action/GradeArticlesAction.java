package conference.manager.controller.action;

import conference.manager.model.ModelException;
import conference.manager.model.domain.Researcher;
import conference.manager.model.domain.Article;
import conference.manager.model.database.ModelDatabase;

import java.util.List;

public class GradeArticlesAction extends ControllerAction {

	public GradeArticlesAction(ModelDatabase database) {
		this.database = database;
	}

	/**
	 * Performs the process of grading the articles .
	 * 
	 * @param reviewer
	 *         	The reviewer who is scoring a paper.
	 * @param score
	 * 			The score given by the reviewer.
	 * @param article
	 * 			The article being graded.
	 * 
	 * @throws ModelException
	 */
	public void setReviewerGrade(Researcher reviewer, int score, Article article) throws ModelException {
		article.setScore(reviewer, score);
	}
	
	/**
	 * Retrieves all the reviewers from the article.
	 * 
	 * @return
	 * 		a list of reviewers from the article
	 * 
	 */
	public List<Researcher> getReviewers(Article article) {
		return article.getReviewers();
	}

	/**
	 * Retrieves all the articles from the database.
	 * 
	 * @return
	 * 		a list of articles from the database
	 * 
	 */
	public List<Article> getArticles() {
		return database.getAllArticles();
	}

}
