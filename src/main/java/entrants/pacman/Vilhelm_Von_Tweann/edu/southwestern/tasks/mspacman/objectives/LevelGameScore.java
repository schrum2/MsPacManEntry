/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.evolution.Organism;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class LevelGameScore<T extends Network> extends MsPacManObjective<T> {

	private final int level;

	public LevelGameScore(int level) {
		this.level = level;
	}

	public double fitness(Organism<T> individual) {
		return g.getScore(level);
	}
}
