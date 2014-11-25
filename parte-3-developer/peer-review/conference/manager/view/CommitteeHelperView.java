package conference.manager.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import conference.manager.controller.CommitteeHelperController;
import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;
import conference.manager.model.domain.Conference;
import conference.manager.model.domain.Article;
import conference.manager.model.domain.Score;
import conference.manager.view.command.AllocationCommand;
import conference.manager.view.command.ArticlesSelectionCommand;
import conference.manager.view.command.Command;
import conference.manager.view.command.GradeArticlesCommand;

public class CommitteeHelperView {

	private final Log log = LogFactory.getLog(getClass());
	
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public int requestNumberOfReviewers() {
		return readInteger("Informe o número de Revisores",
				Conference.MIN_REVIEWERS, Conference.MAX_REVIEWERS);
	}

	public Conference requestConference(List<Conference> conferences) {
		StringBuffer container = new StringBuffer();
		container.append("Escolha a conferência:\n");
		container.append(showConferences(conferences));
		System.out.println(container);
		int selectedConference = readInteger("Informe o número da conferência",
				0, conferences.size() - 1);
		return conferences.get(selectedConference);
	}

	public int requestScore() {
		return readInteger("Informe a nota (inteiro entre " + Score.MIN_GRADE
				+ " e " + Score.MAX_GRADE + ")", Score.MIN_GRADE,
				Score.MAX_GRADE);
	}

	public Article requestArticle(List<Article> articles) {
		StringBuffer container = new StringBuffer();
		container.append("Escolha o artigo:\n");
		container.append(showArticles(articles));
		System.out.println(container);
		int selectedArticle = readInteger("Informe o número do artigo", 0,
				articles.size() - 1);
		return articles.get(selectedArticle);
	}

	public String showConferences(List<Conference> conferences) {
		int i = 0;
		StringBuffer container = new StringBuffer();
		container.append("Opção\tConferência\n");
		for (Conference conference : conferences) {
			container.append(i + "\t" + conference.getName() + "\n");
			i++;
		}
		return container.toString();
	}

	public String showArticles(List<Article> articles) {
		int i = 0;
		StringBuffer container = new StringBuffer();
		container.append("Opção\tAutor\t\tAvaliado\tTítulo\n");
		for (Article article : articles) {
			container.append(i + "\t" + article.getAuthor().getName() + "\t\t"
					+ (article.isScored() ? "Sim" : "Não") + "\t\t"
					+ article.getTitle() + "\n");
			i++;
		}
		return container.toString();
	}

	public String showScores(List<Score> scores) {
		int i = 0;
		StringBuffer container = new StringBuffer();
		container.append("Opção\tRevisor\t\tNota\n");
		for (Score score : scores) {
			container.append(i + "\t" + score.getReviewer().getName() + "\t\t"
					+ (score.isAllocated() ? score.getScore() : "-") + "\n");
			i++;
		}
		return container.toString();
	}

	public Score requestReviewer(List<Score> scores) {
		StringBuffer container = new StringBuffer();
		container.append("Escolha o revisor:\n");
		container.append(showScores(scores));
		System.out.println(container);
		int selectedReviewer = readInteger("Informe o número do revisor", 0,
				scores.size() - 1);
		return scores.get(selectedReviewer);
	}

	public Integer readInteger(String field, int min, int max) {
		Integer value = null;
		while (value == null) {
			value = readInteger(field);
			if (value < min || value > max) {
				value = null;
				System.out.println("O inteiro deve ser entre " + min + " e "
						+ max + ".");
			}
		}
		return value;
	}

	public Integer readInteger(String field) {
		Integer value = null;
		while (value == null) {
			try {
				if (field != null)
					System.out.print(field + ": ");
				value = new Integer(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Um valor numérico inteiro é esperado.");
				log.warn(nfe);
			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}

	public void handleUnexceptedError(Exception e) {
		System.out.println("Erro ineseperado.");
		log.error(e);
		e.printStackTrace();
		System.exit(-1);
	}

	public void showAcceptedArticles(List<Article> articles) {
		StringBuffer container = new StringBuffer();
		container.append("Artigos aceitos:\n");
		container.append(showArticleFinalGrades(articles));
		System.out.println(container);
	}

	public void showRejectedArticles(List<Article> articles) {
		StringBuffer container = new StringBuffer();
		container.append("Artigos rejeitados:\n");
		container.append(showArticleFinalGrades(articles));
		System.out.println(container);
	}

	public String showArticleFinalGrades(List<Article> articles) {
		StringBuffer container = new StringBuffer();
		container.append("Autor\t\tMédia Final\tTítulo\n");
		for (Article article : articles) {
			container.append(article.getAuthor().getName() + "\t\t"
					+ article.getFinalScore() + "\t\t" + article.getTitle()
					+ "\n");
		}
		return container.toString();
	}

	public String readString(String field) {
		String value = null;
		while (value == null) {
			try {
				if (field != null)
					System.out.print(field + ": ");
				value = reader.readLine();
			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}
}
