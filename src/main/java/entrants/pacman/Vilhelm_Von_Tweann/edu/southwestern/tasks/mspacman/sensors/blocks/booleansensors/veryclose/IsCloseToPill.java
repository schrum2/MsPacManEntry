/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.veryclose;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class IsCloseToPill extends IsCloseBlock {

	private static final int CLOSE_PILL_DISTANCE = 10;

	public IsCloseToPill() {
		super(CLOSE_PILL_DISTANCE);
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getActivePillsIndices();
	}

	@Override
	public String getType() {
		return "Pill";
	}
}
