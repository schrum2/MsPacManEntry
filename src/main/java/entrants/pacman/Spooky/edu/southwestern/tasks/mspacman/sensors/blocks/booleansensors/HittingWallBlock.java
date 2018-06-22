package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors;

import java.awt.Color;

import entrants.pacman.Spooky.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.facades.GameFacade;

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
