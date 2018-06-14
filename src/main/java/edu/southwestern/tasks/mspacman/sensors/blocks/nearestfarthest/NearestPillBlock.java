package edu.southwestern.tasks.mspacman.sensors.blocks.nearestfarthest;

import edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class NearestPillBlock extends NearestFarthestIndexBlock {

	public NearestPillBlock() {
		super();
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getActivePillsIndices();
	}

	@Override
	public String typeOfTarget() {
		return "Pill";
	}
}
