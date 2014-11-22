package conference.manager.model.domain;

import java.util.Arrays;
import java.util.List;

public class Researcher implements Comparable<Researcher> {

	private int id;

	private String name;

	private String university;

	private List<String> researchTopics;

	public Researcher(int id, String name, String university, List<String> researchTopics) {
		this.id = id;
		this.name = name;
		this.university = university;
		this.researchTopics = researchTopics;
	}	
	
	public String getName(){
		return name;
	}

	/**
	 * Returns the list of research topics
	 * 
	 * @return list of research topics
	 */
	public List<String> getResearchTopics() {
		return researchTopics;
	}
	
	/**
	 * Returns true if the requirements to review a paper are met, false otherwise
	 * 
	 * @param article
	 * 			the article which is going to be evaluated
	 * 
	 * @param conference
	 * 			the conference in which the article is going to be reviewed
	 */
	public boolean isAbleToReview(Article article, Conference conference) {
		if(hasPaperToReviewInConference(conference) &&
				hasSameAffiliation(article) &&
				hasInterestIn(article.getResearchTopic()))
			return true;
		return false;
	}

	/**
	 * Returns true if either the reviewer is the article's author or if
	 * he's affiliated to the same university of the author
	 * 
	 * @param article
	 * 			the article which is going to be evaluated upon
	 */
	public boolean hasSameAffiliation(Article article) {
		Researcher author = article.getAuthor();
		if(this.equals(author) || university == author.university)
			return true;
		return false;
	}

	/**
	 * Returns true if the reviewer has interest in the article's
	 * research topic
	 * 
	 * @param researchTopic
	 * 			article's research topic
	 */
	public boolean hasInterestIn(String researchTopic) {
		if(!(Arrays.asList(researchTopics).contains(researchTopic)))
			return false;
		return true;
	}

	/**
	 * Returns true if the reviewer already has a paper to review in
	 * the given conference
	 * 
	 * @param conference
	 * 			the conference which is going to be evaluated upon
	 */
	public boolean hasPaperToReviewInConference(Conference conference) {
		List<Researcher> reviewers;
		reviewers = conference.getReviewers();
		if(!(Arrays.asList(reviewers).contains(this)))
			return false;
		return true;
	}

	//TODO
	@Override 
	public int compareTo(Researcher arg0) {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Researcher other = (Researcher) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
