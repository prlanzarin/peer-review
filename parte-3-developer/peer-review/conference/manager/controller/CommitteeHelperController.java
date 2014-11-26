package conference.manager.controller;

import java.util.List;

import conference.manager.controller.action.AllocationAction;
import conference.manager.controller.action.ArticlesSelectionAction;
import conference.manager.controller.action.GradeArticlesAction;
import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;
import conference.manager.model.domain.Article;
import conference.manager.model.domain.Conference;
import conference.manager.model.domain.Score;
import conference.manager.view.CommitteeHelperView;

public class CommitteeHelperController {

	private ModelDatabase database;

	private CommitteeHelperView helperView;

	public CommitteeHelperController(ModelDatabase database) {
		this.database = database;
		this.helperView = new CommitteeHelperView();
	}

	public void onAllocationButtonClicked() throws ModelException {
		AllocationAction action = new AllocationAction(database);
		List<Conference> conferences = action.getUnallocatedConferences();
		if(conferences.isEmpty())
			throw new ModelException("Não há conferências disponíveis para serem alocadas.");
		Conference selectedConference = helperView.requestConference(conferences);
		int numOfReviewers = helperView.requestNumberOfReviewers();
		action.allocateArticles(selectedConference, numOfReviewers);
		
	}

	public void onArticlesSelectionButtonClicked() throws ModelException {
		ArticlesSelectionAction action = new ArticlesSelectionAction(database);
		List<Conference> conferences = action.getConferences();
		Conference selectedConference = helperView.requestConference(conferences);
		action.selectArticles(selectedConference);
		List<Article> acceptedArticles = action
				.getAcceptedArticles(selectedConference);
		List<Article> rejectedArticles = action
				.getRejectedArticles(selectedConference);
		helperView.showAcceptedArticles(acceptedArticles);
		helperView.showRejectedArticles(rejectedArticles);

	}

	public void onGradeArticlesButtonClicked() throws ModelException {
		GradeArticlesAction action = new GradeArticlesAction(database);
		List<Article> articles = action.getArticles();
		Article selectedArticle = helperView.requestArticle(articles);
		List<Score> scores = action.getScores(selectedArticle);
		if(scores.isEmpty())
			throw new ModelException("Não há revisores alocados para esse artigo.");
		Score selectedScore = helperView.requestReviewer(scores);
		int score = helperView.requestScore();
		action.setReviewerGrade(selectedScore.getReviewer(), score, selectedArticle);
	}

}
