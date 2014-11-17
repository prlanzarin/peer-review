package conference.manager.model.database;

import java.util.List;

import conference.manager.model.domain.Article;
import conference.manager.model.domain.Conference;
import conference.manager.model.domain.Researcher;
import conference.manager.model.domain.Score;

public class ModelDatabase {

	private List<Article> articles;

	private List<Conference> conferences;

	private List<Researcher> researchers;

	private List<Score> scores;

	public List<Researcher> getAllResearchers() {
		return null;
	}

	public List<Conference> getAllConferences() {
		return null;
	}

	public List<Article> getAllArticles() {
		return null;
	}

	public List<Article> getAllScores() {
		return null;
	}

	public void addScore(Score score) {

	}

	public ModelDatabase(boolean init) {

	}

	public void setScore(int articleId, int reviewerId, double score) {

	}

}
