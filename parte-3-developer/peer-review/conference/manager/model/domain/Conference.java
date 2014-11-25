package conference.manager.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import conference.manager.model.ModelException;

public class Conference {

	private static final int LIST_FIRST_INDEX = 0;

	public static final int MIN_REVIEWERS = 2;

	public static final int MAX_REVIEWERS = 5;

	private String name;

	private boolean allocatedArticles;

	private List<Article> articles;

	private List<Researcher> reviewers;

	private List<Score> scores;

	private List<Article> acceptedArticles;

	private List<Article> rejectedArticles;

	private Log log;

	public Conference(String name, List<Researcher> reviewers,
			List<Article> articles) {
		this.name = name;
		this.allocatedArticles = false;
		this.articles = articles;
		this.reviewers = reviewers;
		this.scores = new ArrayList<Score>();
		this.acceptedArticles = null;
		this.rejectedArticles = null;
		this.log = LogFactory.getLog(getClass());
	}

	/**
	 * Returns the name of the conference.
	 * 
	 * @return the name of the conference.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the list of articles of the conference.
	 * 
	 * @return the list of articles of the conference.
	 */
	public List<Article> getArticles() {
		return articles;
	}

	/**
	 * Returns the list of reviewers of the conference.
	 * 
	 * @return the list of reviewers of the conference.
	 */
	public List<Researcher> getReviewers() {
		return reviewers;
	}

	/**
	 * Returns the list of accepted articles.
	 * 
	 * @return the list of accepted articles.
	 * @throws ModelException
	 *             if the articles were not selected yet.
	 */
	public List<Article> getAcceptedArticles() throws ModelException {
		if (acceptedArticles == null) {
			throw new ModelException(
					"Os artigos dessa conferência ainda não foram selecionados.");
		}
		return acceptedArticles;
	}

	/**
	 * Returns the list of rejected articles.
	 * 
	 * @return the list of rejected articles.
	 * @throws ModelException
	 *             if the articles were not selected yet.
	 */
	public List<Article> getRejectedArticles() throws ModelException {
		if (rejectedArticles == null) {
			throw new ModelException(
					"Os artigos dessa conferência ainda não foram selecionados.");
		}
		return rejectedArticles;
	}

	/**
	 * Selects the articles in the conference classifying them as accepted or
	 * rejected, according to the conference rules.
	 * 
	 * @throws ModelException
	 *             if not all the articles of the conference were graded.
	 */
	public void selectArticles() throws ModelException {
		if (!isScored())
			throw new ModelException(
					"Há artigos não avaliados nessa conferência.");

		this.acceptedArticles = new ArrayList<Article>();
		this.rejectedArticles = new ArrayList<Article>();
		for (Article article : articles) {
			if (article.isAccepted()) {
				addAcceptedArticle(article);
			} else {
				addRejectedArticle(article);
			}
		}
		Collections.sort(acceptedArticles, new Article.ByDescendingGrade());
		Collections.sort(rejectedArticles, new Article.ByAscendingGrade());
	}

	/**
	 * Checks if the number of reviewers is valid.
	 * 
	 * @param numReviewers
	 *            the number of reviewers to be tested.
	 * @return true if numReviewers is in range [MIN_REVIEWERS, MAX_REVIEWERS]
	 */
	private boolean isNumReviewersValid(int numReviewers) {
		return numReviewers >= MIN_REVIEWERS && numReviewers <= MAX_REVIEWERS;
	}

	/**
	 * Adds a score to the conference.
	 * 
	 * @param score
	 *            the score to be added to the conference.
	 */
	private void addScore(Score score) {
		scores.add(score);
	}

	/**
	 * Adds an article to the list of accepted articles.
	 * 
	 * @param article
	 *            the article to be added to the accepted list.
	 */
	private void addAcceptedArticle(Article article) {
		acceptedArticles.add(article);
	}

	/**
	 * Adds an article to the list of rejected articles.
	 * 
	 * @param article
	 *            the article to be added to the rejected list.
	 */
	private void addRejectedArticle(Article article) {
		rejectedArticles.add(article);
	}

	/**
	 * Sets the conference to allocated or unallocated.
	 * 
	 * @param allocated
	 *            true if allocated, false if unallocated.
	 */
	public void setAllocatedArticles(boolean allocated) {
		this.allocatedArticles = allocated;
	}

	/**
	 * Tests if the conference is allocated.
	 * 
	 * @return true if allocated, otherwise false.
	 */
	public boolean isAllocated() {
		return allocatedArticles;
	}

	/**
	 * Allocates reviewers to the articles of the conference.
	 * 
	 * @param numReviewers
	 *            the number of reviewers for each paper.
	 * @throws ModelException
	 */
	public void allocate(int numReviewers) throws ModelException {
		if (isAllocated())
			throw new ModelException("A conferência já foi alocada.");
		if (!isNumReviewersValid(numReviewers))
			throw new ModelException("O número de revisores deve ser entre "
					+ MIN_REVIEWERS + " e " + MAX_REVIEWERS + ".");
		log.info("Início da alocação.");
		for (Article article : articles) {
			allocateArticle(article, numReviewers);
		}
		setAllocatedArticles(true);
		log.info("Fim da alocação.");
	}

	/**
	 * Allocates reviewers to a single article in the conference.
	 * 
	 * @param article
	 *            the article to be allocated.
	 * @param numReviewers
	 *            the number of reviewers to be allocated to the article.
	 * @throws ModelException
	 */
	private void allocateArticle(Article article, int numReviewers)
			throws ModelException {
		List<Researcher> selectedReviewers = selectReviewers(article,
				numReviewers);
		addScoresToArticle(article, selectedReviewers);

	}

	private void revertAllocationChanges() {
		for (Score score : scores) {
			Article article = score.getArticle();
			Researcher reviewer = score.getReviewer();
			reviewer.removeArticle(article);
			article.removeScore(score);
		}
		scores.clear();
	}

	/**
	 * Add a score to the conference.
	 * 
	 * @param article
	 *            The article to be graded.
	 * @param reviewers
	 *            The reviewer which will grade the article.
	 */
	private void addScoresToArticle(Article article, List<Researcher> reviewers) {
		for (Researcher reviewer : reviewers) {
			addScoreToArticle(article, reviewer);
			log.info("Artigo id " + article.getId() + " alocado ao revisor "
					+ reviewer.getId() + ".");
		}
	}

	public void addScoreToArticle(Article article, Researcher reviewer) {
		Score newScore = new Score(reviewer, article);
		reviewer.addArticle(article);
		addScore(newScore);
		article.addScore(newScore);
	}

	public void addScoreToArticle(Article article, Researcher reviewer,
			int score) throws ModelException {
		Score newScore = new Score(reviewer, article, score);
		reviewer.addArticle(article);
		addScore(newScore);
		article.addScore(newScore);
	}

	/**
	 * Selects the reviewers that can review an article.
	 * 
	 * @param article
	 *            The article to be allocated.
	 * @param numReviewers
	 *            The number of reviewers to be allocated to the article.
	 * @return The list of reviewers that are able to review the article.
	 */
	private List<Researcher> selectReviewers(Article article, int numReviewers)
			throws ModelException {
		List<Researcher> ableReviewers = new ArrayList<Researcher>();
		for (Researcher reviewer : reviewers) {
			if (reviewer.isAbleToReview(article))
				ableReviewers.add(reviewer);
		}
		if (ableReviewers.size() < numReviewers) {
			revertAllocationChanges();
			log.info("Alocação abortada.");
			throw new ModelException(
					"Não há revisores o suficiente para realizar "
							+ "a alocação de artigos corretamente.");
		}
		Collections.sort(ableReviewers);
		List<Researcher> selectedReviewers = ableReviewers.subList(
				LIST_FIRST_INDEX, numReviewers);
		return selectedReviewers;
	}

	/**
	 * Tests if all of the articles in the conference are graded.
	 * 
	 * @return True if all the articles are graded, otherwise false.
	 */
	public boolean isScored() {
		if (!isAllocated())
			return false;
		for (Article article : articles) {
			if (!article.isScored())
				return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conference other = (Conference) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
