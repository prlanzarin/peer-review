package conference.manager;

import org.apache.log4j.PropertyConfigurator;

import conference.manager.view.CommitteeHelperView;

public class PeerReview {

	public static final String PROPERTIES_FILE_LOG4J = "log4j.properties";
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(PeerReview.class
				.getResource(PROPERTIES_FILE_LOG4J));
		CommitteeHelperView view = new CommitteeHelperView();
		view.showUI();
	}

}
