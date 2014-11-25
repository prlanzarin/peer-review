package conference.manager.view;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import conference.manager.controller.CommitteeHelperController;
import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;
import conference.manager.view.command.AllocationCommand;
import conference.manager.view.command.ArticlesSelectionCommand;
import conference.manager.view.command.Command;
import conference.manager.view.command.GradeArticlesCommand;

public class CommitteeView {

	public static final String ALLOCATE_CODE = "A";

	public static final String SELECT_CODE = "S";

	public static final String GRADE_CODE = "G";

	public static final String EXIT_CODE = "E";

	private final Map<String, Command> commands;
	
	private final CommitteeHelperView helperView = new CommitteeHelperView();

	

	private final Log log;

	public CommitteeView() {
		log = LogFactory.getLog(getClass());
		CommitteeHelperController controller = new CommitteeHelperController(
				new ModelDatabase(true));
		commands = new TreeMap<String, Command>();
		commands.put(ALLOCATE_CODE, new AllocationCommand(controller));
		commands.put(SELECT_CODE, new ArticlesSelectionCommand(controller));
		commands.put(GRADE_CODE, new GradeArticlesCommand(controller));
	}
	
	public void showUI() {
		String menu = getMenu();
		String option;
		do {
			System.out.println(menu);
			option = helperView.readString("Informe a opção escolhida").toUpperCase();
			Command command = commands.get(option);
			if (command != null) {
				try {
					command.execute();
				} catch (ModelException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					helperView.handleUnexceptedError(e);
				}
			}

		} while (!option.equals(EXIT_CODE));
	}

	public String getMenu() {
		StringBuffer menu = new StringBuffer();
		menu.append("\nPeer Review\n");
		menu.append(getOptions());
		return menu.toString();
	}

	public String getOptions() {
		StringBuffer options = new StringBuffer();
		options.append("Escolha uma opção:\n");
		options.append(ALLOCATE_CODE
				+ " - Alocar revisores para uma conferência.\n");
		options.append(GRADE_CODE + " - Atribuir nota a um artigo.\n");
		options.append(SELECT_CODE + " - Mostrar artigos aprovados.\n");
		options.append(EXIT_CODE + " - Sair.\n");
		return options.toString();
	}
	
	
	
}
