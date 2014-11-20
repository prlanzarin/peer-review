package conference.manager.model.domain;

import java.util.List;

public class Article {

	private int id;

	private String title;

	private Researcher author;

	private String researchTopic;

	private List<Researcher> reviewers;

	private List<Score> scores;

	public Article(int id, String title, Researcher author, String researchTopic) {

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

	// TODO não esquecer de sobreescrever o método equals, o Eclipse faz isso
	// automaticamente (alt+shift+s -> Generate hashCode() and equals()...)

}
