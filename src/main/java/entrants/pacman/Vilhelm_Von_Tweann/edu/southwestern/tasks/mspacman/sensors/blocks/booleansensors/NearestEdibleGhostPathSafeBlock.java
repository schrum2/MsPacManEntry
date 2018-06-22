package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GhostControllerFacade;

/**
 *
 * @author Jacob Schrum
 */
public class NearestEdibleGhostPathSafeBlock extends PathSafeBlock {

	public NearestEdibleGhostPathSafeBlock(GhostControllerFacade ghostModel) {
		super(ghostModel);
	}

	@Override
	public String targetLabel() {
		return "Nearest Edible Ghost";
	}

	@Override
	public int getTarget(GameFacade gf, int lastDirection) {
		return gf.getClosestNodeIndexFromNodeIndex(gf.getPacmanCurrentNodeIndex(), gf.getEdibleGhostLocations());
	}
}
