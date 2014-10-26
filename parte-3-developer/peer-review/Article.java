import java.util.List;

public class Article {

	private int id;

	private String title;

	private String researchTopic;

	private Article.Researcher[] researcher;

	private Score[] score;

	public int getId() {
		return 0;
	}

	public String getResearchTopic() {
		return null;
	}

	public void addResearcher() {

	}

	public Article() {

	}

	public class Researcher {

		private int id;

		private String name;

		private String university;

		private List<String> researchTopics;

		public List<String> getResearchTopics() {
			return null;
		}

		public Researcher(int id, String name, String university, List<String> researchTopics) {

		}

	}

}
