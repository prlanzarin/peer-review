package conference.manager.model.domain;

import conference.manager.model.ModelException;

/**
 * 
 * @author Marcos Henrique Backes
 *
 */
public class Score {

	private Researcher reviewer;

	private Article article;

	private int score;

	private boolean allocated;

	/**
	 * Creates a new instance of Score, in which the score was not given yet.
	 * 
	 * @param reviewer
	 *            The reviewer assigned to review the article.
	 * @param article
	 *            The article to be reviewed by the reviewer.
	 */
	public Score(Researcher reviewer, Article article) {
		this.reviewer = reviewer;
		this.article = article;
		this.allocated = false;
		this.score = 0;
	}

	/**
	 * Returns the article which this score refers to.
	 * 
	 * @return the article which this score refers to.
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * Returns the score given by the reviewer to the paper.
	 * 
	 * @return the score given by the reviewer to the paper.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns the reviewer assigned to review the article associated with this
	 * grade
	 * 
	 * @return the reviewer assigned to review the article associated with this
	 *         grade
	 */
	public Researcher getReviewer() {
		return reviewer;
	}

	/**
	 * Sets the score given by the reviewer to the article. Also, changes the
	 * status of this score to allocated.
	 * 
	 * @param score
	 *            The score between -3 and 3 given by the reviewer.
	 */
	public void setScore(int score) throws ModelException {
		if(score < -3 || score > 3)
			throw new ModelException("Invalid Arguments");
		this.score = score;
		this.allocated = true;
	}

	/**
	 * Returns true if a score was already given by the reviewer, otherwise
	 * returns false.
	 * 
	 * @return The status of the Score.
	 */
	public boolean isAllocated() {
		return allocated;
	}

}
