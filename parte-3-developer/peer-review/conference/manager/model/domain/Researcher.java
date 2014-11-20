package conference.manager.model.domain;

import java.util.List;

public class Researcher implements Comparable<Researcher> {

	private int id;

	private String name;

	private String university;

	private List<String> researchTopics;

	public List<String> getResearchTopics() {
		return null;
	}

	public Researcher(int id, String name, String university,
			List<String> researchTopics) {

	}

	public boolean isAbleToReview(Article article, Conference conference) {
		return false;
	}

	public boolean hasSameAffiliation(Researcher researcher) {
		return false;
	}

	public boolean hasInteresIn(String researchTopic) {
		return false;
	}

	public boolean hasPaperToReviewInConference(Conference conference) {
		return false;
	}

	@Override
	public int compareTo(Researcher arg0) {
		return 0;
	}

	// TODO não esquecer de sobreescrever o método equals, o Eclipse faz isso
	// automaticamente (alt+shift+s -> Generate hashCode() and equals()...)

}
