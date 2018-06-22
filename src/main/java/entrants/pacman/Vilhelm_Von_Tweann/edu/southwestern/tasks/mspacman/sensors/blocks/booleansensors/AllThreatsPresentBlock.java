/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 * supports popacman (TODO: test)
 * @author Jacob
 */
public class AllThreatsPresentBlock extends BooleanSensorBlock {

	@Override
	public String senseLabel() {
		return "All Threats Present";
	}

	@Override
	/**
	 * supports popacman (TODO: test)
	 */
	public boolean predicate(GameFacade gf, int lastDirection) {
		return gf.getThreatGhostLocations().length == CommonConstants.numActiveGhosts;
	}
}
