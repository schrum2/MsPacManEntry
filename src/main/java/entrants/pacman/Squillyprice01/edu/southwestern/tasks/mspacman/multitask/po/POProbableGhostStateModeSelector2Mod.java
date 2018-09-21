package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.multitask.po;

import java.util.ArrayList;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.multitask.MsPacManModeSelector;
import entrants.pacman.Squillyprice01.edu.southwestern.util.datastructures.Quad;
import pacman.game.Constants.MOVE;

/**
 * Has three modes. 0) there are no visible or predicted ghosts. 1) there
 * are only edible visible or predicted ghosts. 2) there are only threat visible
 * or predicted ghosts.
 * @author pricew
 *
 */
public class POProbableGhostStateModeSelector2Mod extends MsPacManModeSelector {

	public static final int NO_GHOSTS_VISIBLE = 0;
	public static final int ANY_GHOSTS_VISIBLE = 1;

	ArrayList<Quad<Integer, MOVE, Double, Double>> predictedGhostInfo;
	
	public POProbableGhostStateModeSelector2Mod() {
		super();
	}
	
	@Override
	public int mode() {
		predictedGhostInfo = gs.getPossibleGhostInfo();
		
		//there are no predicted ghosts
		if(predictedGhostInfo.size() == 0) {
			return NO_GHOSTS_VISIBLE;
		}
		
		return ANY_GHOSTS_VISIBLE;
		
	}

	@Override
	public int numModes() {
		//We have four modes
		return 3;
	}

	@Override
	public int[] associatedFitnessScores() {
		throw new UnsupportedOperationException("We don't do that in these here parts");
	}

}
