/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Squillyprice01.edu.southwestern.evolution.Organism;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.Network;

/**
 * Only reward eating of power pills if all ghosts were threats when the power
 * pill was eaten (maximizes score potential).
 *
 * @author Jacob Schrum
 */
public class ProperlyEatenPowerPillScore<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return g.getProperlyEatenPowerPills();
	}
}
