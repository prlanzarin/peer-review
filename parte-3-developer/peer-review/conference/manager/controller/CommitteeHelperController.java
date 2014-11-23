package conference.manager.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import conference.manager.controller.action.AllocationAction;
import conference.manager.controller.action.ArticlesSelectionAction;
import conference.manager.controller.action.GradeArticlesAction;
import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;
import conference.manager.model.domain.Article;
import conference.manager.model.domain.Conference;
import conference.manager.model.domain.Researcher;
import conference.manager.model.domain.Score;
import conference.manager.view.CommitteeHelperView;
import conference.manager.view.command.ArticlesSelectionCommand;

public class CommitteeHelperController {

	private ModelDatabase database;

	private CommitteeHelperView view;

	private Log log;

	public void onAllocationButtonClicked() throws ModelException {
		AllocationAction action = new AllocationAction(database);
		List<Conference> conferences = action.getUnallocatedConferences();
		Conference selectedConference = view.requestConference(conferences);
		int numOfReviewers = view.requestNumberOfReviewers();
		action.allocateArticles(selectedConference, numOfReviewers);
		
	}

	public void onArticlesSelectionButtonClicked() throws ModelException {
		ArticlesSelectionAction action = new ArticlesSelectionAction(database);
		List<Conference> conferences = action.getConferences();
		Conference selectedConference = view.requestConference(conferences);
		action.selectArticles(selectedConference);
		List<Article> acceptedArticles = action
				.getAcceptedArticles(selectedConference);
		List<Article> rejectedArticles = action
				.getRejectedArticles(selectedConference);
		view.showAcceptedArticles(acceptedArticles);
		view.showRejectedArticles(rejectedArticles);

	}

	public void onGradeArticlesButtonClicked() throws ModelException {
		GradeArticlesAction action = new GradeArticlesAction(database);
		List<Article> articles = action.getArticles();
		Article selectedArticle = view.requestArticle(articles);
		List<Score> scores = action.getScores(selectedArticle);
		if(scores.isEmpty())
			throw new ModelException("Não há revisores para realizar a atribuição de notas.");
		Score selectedScore = view.requestReviewer(scores);
		int score = view.requestScore();
		action.setReviewerGrade(selectedScore.getReviewer(), score, selectedArticle);
	}

	public CommitteeHelperController(ModelDatabase database,
			CommitteeHelperView view) {
		this.database = database;
		this.view = view;
		this.log = LogFactory.getLog(getClass());
	}

}
