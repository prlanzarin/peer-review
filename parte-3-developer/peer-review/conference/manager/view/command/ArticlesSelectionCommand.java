package conference.manager.view.command;

import conference.manager.controller.CommitteeHelperController;
import conference.manager.model.ModelException;

public class ArticlesSelectionCommand extends Command {

	public ArticlesSelectionCommand(CommitteeHelperController controller) {
		super(controller);
	}
	
	@Override
	public void execute() throws ModelException {
		controller.onArticlesSelectionButtonClicked();
	}

}
