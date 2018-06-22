/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Spooky.edu.southwestern.evolution.Organism;
import entrants.pacman.Spooky.edu.southwestern.networks.Network;
import entrants.pacman.Spooky.edu.southwestern.util.random.RandomNumbers;

/**
 *
 * @author Jacob Schrum
 */
public class RandomScore<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return RandomNumbers.randomGenerator.nextDouble();
	}
}
