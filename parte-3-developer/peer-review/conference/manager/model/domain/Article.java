package conference.manager.model.domain;

import java.util.ArrayList;
import java.util.List;

import conference.manager.model.ModelException;

public class Article {

	private int id;

	private String title;
	
	private Researcher author;
	
	private String researchTopic;

	private List<Researcher> reviewers;

	private List<Score> scores;

	public Article(int id, String title, Researcher author, String researchTopic) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.researchTopic = researchTopic;
		this.reviewers = new ArrayList<Researcher>();
		this.scores = new ArrayList<Score>();
	}
	
	/**
	 * Returns the article's author
	 * 
	 * @return the article's author
	 */
	public Researcher getAuthor(){
		return author;
	}
	
	/**
	 * Returns the article's title
	 * 
	 * @return the article's title
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Returns the article's ID
	 * 
	 * @return the article's ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the article's research topic
	 * 
	 * @return the article's research topic
	 */
	public String getResearchTopic() {
		return researchTopic;
	}
	
	public List<Score> getScores(){
		return scores;
	}

	/**
	 * Returns the article's list of reviewers
	 * 
	 * @return the article's list of reviewers
	 */
	public List<Researcher> getReviewers() {
		return reviewers;
	}

	/**
	 * Sets the article's score
	 * 
	 * @param reviewer
	 * 			the reviewer who has given the score
	 * @param scoreValue
	 * 			integer value for the score
	 * 
	 * @throws ModelException
	 */
	public void setScore(Researcher reviewer, int scoreValue) throws ModelException {
		Score score;
		score = getScore(reviewer);
		score.setScore(scoreValue);
	}
	
	/**
	 * Returns this article's score
	 * 
	 * @return this article's score
	 */
	public Score getScore(Researcher reviewer) {
		for(Score score : scores){
			if((score.getReviewer()).equals(reviewer))
				return score;
		}
		return null;
	}

	/**
	 * Adds a reviewer to the article's list of reviewers
	 * 
	 * @param reviewer
	 * 			the reviewer to be added to the article's list of reviewers
	 */
	public void addReviewer(Researcher reviewer) {
		reviewers.add(reviewer);
	}

	/**
	 * Adds a score to the article's list of scores
	 * 
	 * @param reviewer
	 * 			the score to be added to the article's list of scores
	 */
	public void addScore(Score score) {
		scores.add(score);
		addReviewer(score.getReviewer());
	}

	/**
	 * Returns true if the article's average score is >= 0, false otherwise
	 * 
	 * @return true if the article's average score is >= 0, false otherwise
	 */
	public boolean isAccepted() {
		double sum = 0;
		
		for(Score score : scores){
			sum = sum + score.getScore();
			sum = sum / scores.size();
		}
		if(sum >= 0)
			return true;
		
		return false;
	}

	/**
	 * Returns true if all the reviewers have been allocated for this article,
	 * false otherwise
	 * 
	 * @return true if allocated, false otherwise
	 */
	public boolean isAllocated() {
		return !reviewers.isEmpty();
	}

	/**
	 * Returns true if all the scores have been allocated for this article,
	 * false otherwise
	 * 
	 * @return true if the scores have been allocated, false otherwise
	 */
	public boolean isScored() { //TODO FIX
		if(!(this.isAllocated()))
			return false;
		for(Score score : scores){
			if(!(score.isAllocated()) || score.equals(null))
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
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
