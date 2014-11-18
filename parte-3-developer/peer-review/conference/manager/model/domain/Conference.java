package conference.manager.model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import conference.manager.model.ModelException;

public class Conference {

	private static final int LIST_FIRST_INDEX = 0;

	private String name;

	private boolean allocatedArticles;

	private List<Article> articles;

	private List<Researcher> reviewers;

	private List<Score> scores;

	private List<Article> acceptedArticles;

	private List<Article> rejectedArticles;

	public Conference(String name, List<Researcher> reviewers,
			List<Article> articles) {
		this.name = name;
		this.allocatedArticles = false;
		this.articles = articles;
		this.reviewers = reviewers;
		this.scores = new ArrayList<Score>();
		this.acceptedArticles = null;
		this.rejectedArticles = null;
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
			throw new ModelException("The articles were not selected yet");
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
			throw new ModelException("The articles were not selected yet");
		}
		return rejectedArticles;
	}

	/**
	 * Selects the articles in the conference classifying them as accepted or
	 * rejected, according to the conference rules.
	 * 
	 * @throws ModelException
	 *             if not all the grades of the conference were graded.
	 */
	public void selectArticles() throws ModelException {
		if (!isScored())
			throw new ModelException(
					"There are articles not graded in this conference");

		this.acceptedArticles = new ArrayList<Article>();
		this.rejectedArticles = new ArrayList<Article>();
		for (Article article : articles) {
			if (article.isAccepted()) {
				addAcceptedArticle(article);
			} else {
				addRejectedArticle(article);
			}
		}
	}

	/**
	 * Adds a score to the conference.
	 * 
	 * @param score
	 *            the score to be added to the conference.
	 */
	public void addScore(Score score) {
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
	private void setAllocatedArticles(boolean allocated) {
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
	 */
	public void allocate(int numReviewers) {
		for (Article article : articles) {
			allocateArticle(article, numReviewers);
		}
		setAllocatedArticles(true);
	}

	/**
	 * Allocates reviewers to a single article in the conference.
	 * 
	 * @param article
	 *            the article to be allocated.
	 * @param numReviewers
	 *            the number of reviewers to be allocated to the article.
	 */
	private void allocateArticle(Article article, int numReviewers) {
		for (int i = 0; i < numReviewers; i++) {
			List<Researcher> selectedReviewers = selectReviewers(article,
					numReviewers);
			addScoresToArticle(article, selectedReviewers);
		}
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
			Score newScore = new Score(reviewer, article);
			scores.add(newScore);
			article.addScore(newScore);
		}
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
	private List<Researcher> selectReviewers(Article article, int numReviewers) {
		List<Researcher> ableReviewers = new ArrayList<Researcher>();
		for (Researcher reviewer : reviewers) {
			if (reviewer.isAbleToReview(article, this))
				ableReviewers.add(reviewer);
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
		for (Article article : articles) {
			if (!article.isScored())
				return false;
		}
		return true;
	}

}
