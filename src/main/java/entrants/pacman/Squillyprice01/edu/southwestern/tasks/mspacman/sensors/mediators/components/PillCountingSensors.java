package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.mediators.components;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.BlockLoadedInputOutputMediator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.PowerPillsClearedBlock;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.counting.PillsRemainingBlock;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.counting.PowerPillsRemainingBlock;

/**
 * Counts of pills
 *
 * @author Jacob Schrum
 */
public class PillCountingSensors extends BlockLoadedInputOutputMediator {

	public PillCountingSensors(boolean pillsPresent, boolean canEatGhosts) {
		super();
		if (pillsPresent) {
			blocks.add(new PillsRemainingBlock(true, false));
		}
		if (canEatGhosts) {
			blocks.add(new PowerPillsRemainingBlock(true, false));
			blocks.add(new PowerPillsClearedBlock()); // redundant?
		}
	}
}
