package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.evolution.Organism;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class FastGhostEatingScore<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return g.getTimeGhostReward();
	}
}
