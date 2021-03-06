package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Squillyprice01.edu.southwestern.evolution.Organism;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class FastPillEatingScore<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return g.getTimePillReward();
	}
}
