package conference.manager.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Researcher implements Comparable<Researcher> {

	private int id;

	private String name;

	private String university;

	private List<String> researchTopics;
	
	private List<Article> allocatedArticles;

	public Researcher(int id, String name, String university, List<String> researchTopics) {
		this.id = id;
		this.name = name;
		this.university = university;
		this.researchTopics = researchTopics;
		this.allocatedArticles = new ArrayList<Article>();
	}	
	
	/**
	 * Returns the researcher's name
	 * 
	 * @return the researcher's names
	 */
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
	 * Adds the allocated article to the researcher's list
	 * 
	 * @param article
	 * 			Article to be added to the list.
	 */
	public void addArticle(Article article) {
		allocatedArticles.add(article);
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
	public boolean isAbleToReview(Article article) {
		if(!(reviewsArticle(article)) &&
				!(hasSameAffiliation(article)) &&
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
		if(author == null)
			return false;
		else 
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
		for(String interest : researchTopics){
			if(interest == researchTopic)
				return true;
		}
		return false;
	}
 
	/**
	 * Returns true if the reviewer already has a paper to review in
	 * the given conference
	 * 
	 * @param conference
	 * 			the conference which is going to be evaluated upon
	 */
	public boolean reviewsArticle(Article article) {
		List<Researcher> reviewers;
		reviewers = article.getReviewers();
		for(Researcher reviewer : reviewers){
			if(reviewer.equals(this))
				return true;
		}
		return false;
	}

	@Override 
	public int compareTo(Researcher reviewer) {
		if(this.allocatedArticles.size() > reviewer.
				allocatedArticles.size())
			return 1;
			else if (this.allocatedArticles.size() < reviewer.
					allocatedArticles.size())
				return -1;
			else
				if(this.id > reviewer.id)
					return 1;
				else return -1;
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
