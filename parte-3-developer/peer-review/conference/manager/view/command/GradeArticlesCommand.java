package conference.manager.view.command;

import conference.manager.controller.CommitteeHelperController;
import conference.manager.model.ModelException;

public class GradeArticlesCommand extends Command {

	public GradeArticlesCommand(CommitteeHelperController controller) {
		super(controller);
	}

	@Override
	public void execute() throws ModelException {
		controller.onGradeArticlesButtonClicked();
	}

}
