package conference.manager.view.command;

import conference.manager.controller.CommitteeHelperController;
import conference.manager.model.ModelException;

public abstract class Command {
	
	protected CommitteeHelperController controller;

	public Command(CommitteeHelperController controller) {
		this.controller = controller;
	}
	
	public abstract void execute() throws ModelException;

}
