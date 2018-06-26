package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Squillyprice01.edu.southwestern.evolution.Organism;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.Network;
import entrants.pacman.Squillyprice01.oldpacman.game.Game;

/**
 * Sloppy way of tracking the size of the path cache used by ms pacman.
 *
 * @author Jacob Schrum
 */
public class PathCacheSize<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return Game.amountCachedPathData();
	}
}
