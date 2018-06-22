package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.agentcontroller.pacman.actions;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class FromNearestPowerPillAction extends FromNearestItemAction {

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getActivePowerPillsIndices();
	}
}
