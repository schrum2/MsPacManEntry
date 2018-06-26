package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.distancedifference;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class PowerPillDistanceDifferenceBlock extends PacManVsThreatDistanceDifferencesBlock {

	public PowerPillDistanceDifferenceBlock() {
		this(false, false, 0, false, false);
	}

	public PowerPillDistanceDifferenceBlock(boolean ghostDistances, boolean pacmanDistances, int simulationDepth,
			boolean futurePillsEaten, boolean futureGhostsEaten) {
		super(ghostDistances, pacmanDistances, simulationDepth, futurePillsEaten, futureGhostsEaten, true);
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getActivePowerPillsIndices();
	}

	@Override
	public String typeOfTarget() {
		return "Power Pill";
	}
}
