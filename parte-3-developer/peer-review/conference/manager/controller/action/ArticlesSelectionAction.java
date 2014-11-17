package conference.manager.controller.action;

import java.util.List;

import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;
import conference.manager.model.domain.Article;
import conference.manager.model.domain.Conference;

public class ArticlesSelectionAction extends ControllerAction {

	/**
	 * Creates a new instance of ArticleSelectionAction.
	 * 
	 * @param database
	 *            The database containing the information about conferences.
	 */
	public ArticlesSelectionAction(ModelDatabase database) {
		this.database = database;
	}

	/**
	 * Given a conference, selects the articles in the conference classifying
	 * them as accepted or rejected, according to the conference rules.
	 * 
	 * @param conference
	 *            The conference to be processed.
	 * @throws ModelException
	 *             If there is an article in the conference that was not grade
	 *             yet.
	 */
	public void selectArticles(Conference conference) throws ModelException {
		conference.selectArticles();
	}

	/**
	 * Returns all of the conferences in the database.
	 * 
	 * @return a list containing the conferences.
	 */
	public List<Conference> getConferences() {
		return database.getAllConferences();
	}

	/**
	 * Returns the accepted articles in the given conference.
	 * 
	 * @param conference
	 *            The conference from which the accepted articles will be
	 *            gotten.
	 * @return A list containing the accepted articles.
	 * @throws ModelException
	 *             If the articles in the conference were not selected yet.
	 */
	public List<Article> getAcceptedArticles(Conference conference)
			throws ModelException {
		return conference.getAcceptedArticles();
	}

	/**
	 * Returns the rejected articles in the given conference.
	 * 
	 * @param conference
	 *            The conference from which the rejected articles will be
	 *            gotten.
	 * @return A list containing the rejected articles.
	 * @throws ModelException
	 *             If the articles in the conference were not selected yet.
	 */
	public List<Article> getRejectedArticles(Conference conference)
			throws ModelException {
		return conference.getRejectedArticles();
	}

}
