package conference.manager.model.domain;

import java.util.List;

public class Article {

	private int id;

	private String title;

	private String researchTopic;

	private List<Researcher> reviewers;

	private List<Score> scores;

	public Article() {

	}

	public int getId() {
		return 0;
	}

	public String getResearchTopic() {
		return null;
	}

	public void addReviewer(Researcher reviewer) {

	}

	public List<Researcher> getReviewers() {
		return null;
	}

	public boolean isAccepted() {
		return false;
	}

	public void addScore(Score score) {

	}

	public void setScore(Researcher reviewer, int scoreValue) {

	}

	public Score getScore(Researcher reviewer) {
		return null;
	}

	public boolean isAllocated() {
		return false;
	}

	public boolean isScored() {
		return false;
	}

}
