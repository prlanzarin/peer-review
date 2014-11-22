package conference.manager.view.command;

import conference.manager.controller.CommitteeHelperController;
import conference.manager.model.ModelException;


public class AllocationCommand extends Command {

	public AllocationCommand(CommitteeHelperController controller) {
		super(controller);
	}
	
	@Override
	public void execute() throws ModelException {
		controller.onAllocationButtonClicked();		
	}

}
