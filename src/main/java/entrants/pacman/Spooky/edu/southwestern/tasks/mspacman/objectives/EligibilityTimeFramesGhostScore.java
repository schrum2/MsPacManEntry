package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.objectives;

import java.util.List;

import entrants.pacman.Spooky.edu.southwestern.networks.Network;

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
