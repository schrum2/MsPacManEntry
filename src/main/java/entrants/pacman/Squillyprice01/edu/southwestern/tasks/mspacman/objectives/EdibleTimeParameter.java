/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Squillyprice01.edu.southwestern.evolution.Organism;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.Network;
import entrants.pacman.Squillyprice01.oldpacman.game.Constants;

/**
 *
 * @author Jacob Schrum
 */
public class EdibleTimeParameter<T extends Network> extends MsPacManObjective<T> {

	public double fitness(Organism<T> individual) {
		return Constants.EDIBLE_TIME;
	}
}
