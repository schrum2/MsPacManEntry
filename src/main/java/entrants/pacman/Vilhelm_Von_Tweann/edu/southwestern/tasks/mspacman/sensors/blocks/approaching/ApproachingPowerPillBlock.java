/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.approaching;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class ApproachingPowerPillBlock extends ApproachingLocationBlock {

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getActivePowerPillsIndices();
	}

	@Override
	public String typeOfTarget() {
		return "Power Pill";
	}
}
