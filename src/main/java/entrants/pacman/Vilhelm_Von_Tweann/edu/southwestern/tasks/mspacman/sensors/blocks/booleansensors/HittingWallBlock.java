package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;

import java.awt.Color;

/**
 *
 * @author Jacob Schrum
 */
public class HittingWallBlock extends BooleanSensorBlock {

	@Override
	public String senseLabel() {
		return "Hitting Wall";
	}

	@Override
	public boolean predicate(GameFacade gf, int lastDirection) {
		boolean hittingWall = gf.pacmanHittingWall();
		if (CommonConstants.watch && hittingWall) {
			gf.addLines(Color.yellow, gf.getPacmanCurrentNodeIndex(), gf.getGhostInitialNodeIndex());
		}
		return hittingWall;
	}
}
