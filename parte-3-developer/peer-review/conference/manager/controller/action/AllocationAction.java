package conference.manager.controller.action;

import conference.manager.model.ModelException;
import conference.manager.model.database.ModelDatabase;
import conference.manager.model.domain.Conference;

import java.util.ArrayList;
import java.util.List;

public class AllocationAction extends ControllerAction {

	public AllocationAction(ModelDatabase database) {
		this.database = database;
	}

	/**
	 * Performs the process of allocating the articles for evaluation
	 * for the given conference, taking into account the number of reviewers.
	 * 
	 * @param conference
	 *         	The conference to be allocated
	 * @param numOfReviewers
	 * 			The number of reviewers
	 * @throws ModelException 
	 */
	public void allocateArticles(Conference selectedConference, int numOfReviewers) 
			throws ModelException {
		selectedConference.allocate(numOfReviewers);
	}

	/**
	 * Retrieves all the conferences from the database.
	 * 
	 * @return
	 * 		a list of conferences from the database
	 * 
	 */
	public List<Conference> getUnallocatedConferences() {
		List<Conference> conferences = database.getAllConferences();
		List<Conference> unallocatedConferences = new ArrayList<Conference>();
		for(Conference conference : conferences){
			if(!(conference.isAllocated()))
				unallocatedConferences.add(conference);
		}
		return unallocatedConferences;
	}

}
