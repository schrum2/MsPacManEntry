package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives;

import java.util.List;

import entrants.pacman.Squillyprice01.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class EligibilityTimeFramesGhostScore<T extends Network> extends EligibilityTimeFramesScore<T> {

	public EligibilityTimeFramesGhostScore(int mode) {
		super(mode);
	}

	@Override
	public List<Integer> eatTimes() {
		return g.getGhostEatTimes();
	}
}
