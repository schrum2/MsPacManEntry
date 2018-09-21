package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.multitask.po;
import java.util.ArrayList;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.multitask.MsPacManModeSelector;
import entrants.pacman.Squillyprice01.edu.southwestern.util.datastructures.Quad;
import pacman.game.Constants.MOVE;

/**
 * Has three modes. 0) there are no visible or predicted ghosts. 1) there
 * are only edible visible or predicted ghosts. 2) there are only threat visible
 * or predicted ghosts. 4) there are mixed visible or predicted ghosts.
 * @author pricew
 *
 */
public class POProbableGhostStateModeSelector4Mod extends MsPacManModeSelector {

	public static final int NO_GHOSTS_VISIBLE = 0;
	public static final int EDIBLE_GHOSTS_VISIBLE = 1;
	public static final int THREAT_GHOSTS_VISIBLE = 2;
	public static final int MIXED_GHOSTS_VISIBLE = 3;
	ArrayList<Quad<Integer, MOVE, Double, Double>> predictedGhostInfo;
	
	public POProbableGhostStateModeSelector4Mod() {
		super();
	}
	
	@Override
	public int mode() {
		predictedGhostInfo = gs.getPossibleGhostInfo();
	
		//there are no predicted ghosts
		if(predictedGhostInfo.size() == 0) {
			return NO_GHOSTS_VISIBLE;
		}
		
		//if the only ghosts we have predicted are those we can see are edible
		if(containsOnlyEdibleGhosts(predictedGhostInfo)) {
			return EDIBLE_GHOSTS_VISIBLE;
		}
		
		//if we can't see these ghosts, assume they are threats
		if(!containsOnlyEdibleGhosts(predictedGhostInfo)) {
			return THREAT_GHOSTS_VISIBLE;
		}
		
		//if we can see edible ghosts and have possible threats
		if(!containsOnlyEdibleGhosts(predictedGhostInfo) && containsAnEdibleGhost(predictedGhostInfo)) {
			return MIXED_GHOSTS_VISIBLE;
		}
		
		throw new UnsupportedOperationException("How did we get here?");
		
	}
	
	/**
	 * Takes an ArrayList of predicted ghost information and determines whether all preidcted ghosts are edible or not.
	 * @param predictedGhostInfo
	 * @return
	 */
	private boolean containsOnlyEdibleGhosts(ArrayList<Quad<Integer, MOVE, Double, Double>> predictedGhostInfo) {
		//for each predicted ghost location
		for(Quad<Integer, MOVE, Double, Double> q : predictedGhostInfo) {
			//if the probability that it is edible is 0
			if(q.t4 == 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean containsAnEdibleGhost(ArrayList<Quad<Integer, MOVE, Double, Double>> predictedGhostInfo) {
		//for each predicted ghost location
		for(Quad<Integer, MOVE, Double, Double> q : predictedGhostInfo) {
			//if the probability that it is edible is not 0 (we can eat it)
			if(q.t4 != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int numModes() {
		//We have four modes
		return 4;
	}

	@Override
	public int[] associatedFitnessScores() {
		throw new UnsupportedOperationException("We don't do that in these here parts");
	}

}
