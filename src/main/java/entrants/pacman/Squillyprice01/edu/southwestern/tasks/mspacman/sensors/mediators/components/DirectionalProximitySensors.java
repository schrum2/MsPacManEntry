package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.mediators.components;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.BlockLoadedInputOutputMediator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.proximity.EdibleGhostDirectionalProximityBlock;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.proximity.PillDirectionalProximityBlock;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.proximity.PowerPillDirectionalProximityBlock;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.proximity.ThreatGhostDirectionalProximityBlock;

/**
 * directional proximity to objects of interest
 *
 * @author Jacob Schrum
 */
public class DirectionalProximitySensors extends BlockLoadedInputOutputMediator {

	public DirectionalProximitySensors(boolean sensePills, boolean senseEdibleGhosts, boolean sensePowerPills) {
		super();
		blocks.add(new ThreatGhostDirectionalProximityBlock());
		// blocks.add(new JunctionDirectionalProximityBlock());
		if (sensePills) {
			blocks.add(new PillDirectionalProximityBlock());
		}
		if (senseEdibleGhosts) {
			blocks.add(new EdibleGhostDirectionalProximityBlock());
		}
		if (sensePowerPills) {
			blocks.add(new PowerPillDirectionalProximityBlock());
		}
	}
}
