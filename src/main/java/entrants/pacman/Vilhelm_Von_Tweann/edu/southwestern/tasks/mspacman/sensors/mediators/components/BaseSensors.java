package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.mediators.components;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.BlockLoadedInputOutputMediator;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.BiasBlock;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.GhostReversalBlock;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.HittingWallBlock;

/**
 * Contains only sensor blocks that are absolutely necessary to all other
 * mediators.
 *
 * @author Jacob Schrum
 */
public class BaseSensors extends BlockLoadedInputOutputMediator {

	public BaseSensors() {
		super();
		blocks.add(new BiasBlock());
		blocks.add(new GhostReversalBlock());
		if (!CommonConstants.eliminateImpossibleDirections) {
			blocks.add(new HittingWallBlock());
		}
	}
}
