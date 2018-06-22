/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.evolution.Organism;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.networks.Network;
import entrants.pacman.Vilhelm_Von_Tweann.oldpacman.game.Constants;

/**
 *
 * @author Jacob Schrum
 */
public class EdibleTimeParameter<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return Constants.EDIBLE_TIME;
	}
}
