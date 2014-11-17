package conference.manager.controller.action;

import conference.manager.model.domain.Researcher;
import conference.manager.model.domain.Article;
import conference.manager.model.database.ModelDatabase;
import java.util.List;

public class GradeArticlesAction extends ControllerAction {

	public void setReviewerGrade(Researcher reviewer, int score, Article article) {

	}

	public GradeArticlesAction(ModelDatabase database) {

	}

	public List<Researcher> getReviewers(Article article) {
		return null;
	}

	public List<Article> getArticles() {
		return null;
	}

}
