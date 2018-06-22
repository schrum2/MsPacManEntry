/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.objectives;

import entrants.pacman.Spooky.edu.southwestern.evolution.Organism;
import entrants.pacman.Spooky.edu.southwestern.networks.Network;
import entrants.pacman.Spooky.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Spooky.edu.southwestern.parameters.Parameters;

/**
 *
 * @author Jacob Schrum
 */
public class SurvivalAndSpeedTimeScore<T extends Network> extends MsPacManObjective<T> {
	private final int pacmanMaxLevel;

	public SurvivalAndSpeedTimeScore() {
		pacmanMaxLevel = Parameters.parameters.integerParameter("pacmanMaxLevel");
	}

	public double fitness(Organism<T> individual) {
		int level = g.getCurrentLevel();
		if (g.getPacmanNumberOfLivesRemaining() == 0) {
			// Pacman died, so surviving longer is rewarded
			return -(CommonConstants.pacManLevelTimeLimit * pacmanMaxLevel)
					+ (level * CommonConstants.pacManLevelTimeLimit) + g.getCurrentLevelTime();
		} else if (level < pacmanMaxLevel) {
			// Ran out of time before beating levels
			return 0;
		} else {
			// Beat all levels, so reward speed
			return (CommonConstants.pacManLevelTimeLimit * pacmanMaxLevel) - g.getTotalTime();
		}
	}
}
