/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.distancedifference;

import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class PillDistanceDifferenceBlock extends PacManVsThreatDistanceDifferencesBlock {

	public PillDistanceDifferenceBlock() {
		this(false, false, 0, false);
	}

	public PillDistanceDifferenceBlock(boolean ghostDistances, boolean pacmanDistances, int simulationDepth,
			boolean futurePillsEaten) {
		super(ghostDistances, pacmanDistances, simulationDepth, futurePillsEaten,
				!Parameters.parameters.booleanParameter("noPowerPills"),
				!Parameters.parameters.booleanParameter("noPowerPills"));
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
