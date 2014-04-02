package architectureUtil;

/**
 * 
 * @author Eyal Dim
 * Etup parameters for the Info-bead User Model
 *
 */

public class IBUMSetup {
	
	
	// prevent or allow connecting link between info-beads within two different sub user models in a group model 
	
	Boolean allowUserModelToUserModelLink = false;

	public Boolean getAllowUserModelToUserModelLink() {
		return allowUserModelToUserModelLink;
	}

	public void setAllowUserModelToUserModelLink(Boolean allowUserToUserLink) {
		this.allowUserModelToUserModelLink = allowUserToUserLink;
	}
	
	
	// prevent or allow presenting edge labels
	
	static Boolean allowLinkLabels = true;

	public static Boolean getAllowLinkLabels() {
		return allowLinkLabels;
	}

	public void setAllowLinkLabels(Boolean allowlinkLabels) {
		IBUMSetup.allowLinkLabels = allowlinkLabels;
	}

	
	

}
