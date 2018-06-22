package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.objectives;

import java.util.List;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class TimeFramesGhostScore<T extends Network> extends TimeFramesScore<T> {

	public TimeFramesGhostScore(int mode) {
		super(mode);
	}

	@Override
	public List<Integer> eatTimes() {
		return g.getGhostEatTimes();
	}
}
