/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.lookahead;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob
 */
public class DirectionalEdibleGhostsStaticLookAheadBlock extends DirectionalStaticLookAheadBlock {

	@Override
	public String targetType() {
		return "Edible Ghosts";
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getEdibleGhostLocations();
	}

	@Override
	public double maxCount(GameFacade gf) {
		return gf.getNumActiveGhosts();
	}
}
