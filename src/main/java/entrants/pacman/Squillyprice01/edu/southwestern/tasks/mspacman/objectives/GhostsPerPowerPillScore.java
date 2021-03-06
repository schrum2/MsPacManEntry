/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Squillyprice01.edu.southwestern.evolution.Organism;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.Network;

/**
 *
 * @author Jacob Schrum
 */
public class GhostsPerPowerPillScore<T extends Network> extends MsPacManObjective<T> {
	private final boolean punishUneatenPowerPills;

	public GhostsPerPowerPillScore(boolean punishUneatenPowerPills) {
		this.punishUneatenPowerPills = punishUneatenPowerPills;
	}

	public double fitness(Organism<T> individual) {
		return g.averageGhostsEatenPerPowerPill(punishUneatenPowerPills);
	}
}
