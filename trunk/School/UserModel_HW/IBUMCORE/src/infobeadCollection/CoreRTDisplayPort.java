package infobeadCollection;

import genericInfoBead.InfoBead;
import genericInfoBead.Triplet;
import services.CoreRTDisplayService;;

/**
 * This is an info-bead that communicates with the 
 * external application "RTDisplayService"
 * that displays infoBead data.
 * 
 * @author Eyal
 * @version 1.0
 */
public class CoreRTDisplayPort extends InfoBead {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//////// invoke the display application  /////////
		CoreRTDisplayService d = new CoreRTDisplayService();

		@Override
		public void initialize() {
			// No need
			
		}

		/**
		 *   Implements the observer "handle data" for triplets
		 *   sent by other connected info-beads
		 *   @param triplet  An info-bead triplet
		 */
		@Override
		public void handleData(Triplet triplet) {
			
			d.displayTriplet(triplet);
			
		}
}
