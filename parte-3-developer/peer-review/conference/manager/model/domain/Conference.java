package conference.manager.model.domain;

import java.util.List;

public class Conference {

	private String name;

	private int reviewerNumber;

	private boolean allocatedArticles;

	private List<Article> articles;

	private List<Researcher> reviewers;

	private List<Score> scores;

	private List<Article> acceptedArticles;

	private List<Article> rejectedArticles;



	public List<Article> getArticles() {
		return null;
	}

	public List<Article> getReviewers() {
		return null;
	}

	public void addScore(Score score) {

	}

	public void addAcceptedArticle(Article article) {

	}

	public void addRejectedArticle(Article article) {

	}

	public Conference(String name, int reviewerNumber, boolean allocatedArticles) {

	}

	public void setAllocatedArticles(boolean allocated) {

	}

	public boolean isAllocated() {
		return false;
	}

	public void allocate(int numReviewers) {

	}

	public void allocateArticle(Article article, int numReviwers) {

	}

	public List<Researcher> selectReviewers(Article article, int numReviewers) {
		return null;
	}

	public boolean isScored() {
		return false;
	}

	public void selectArticles() {

	}

	public List<Article> getAcceptedArticles() {
		return null;
	}

	public List<Article> getRejectedArticles() {
		return null;
	}

}
