/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.evolution.Organism;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.networks.Network;

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
