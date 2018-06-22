/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Spooky.edu.southwestern.evolution.Organism;
import entrants.pacman.Spooky.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class GhostRegretScore<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return -g.getGhostRegret();
	}
}
