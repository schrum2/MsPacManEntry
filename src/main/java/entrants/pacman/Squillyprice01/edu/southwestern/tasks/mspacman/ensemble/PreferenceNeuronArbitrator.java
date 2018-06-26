/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.ensemble;

import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Squillyprice01.edu.southwestern.util.datastructures.ArrayUtil;
import entrants.pacman.Squillyprice01.edu.southwestern.util.stats.StatisticsUtilities;

/**
 *
 * @author Jacob Schrum
 */
public class PreferenceNeuronArbitrator extends MsPacManEnsembleArbitrator {

	public PreferenceNeuronArbitrator() {
		Parameters.parameters.setBoolean("externalPreferenceNeurons", true);
	}

	/**
	 * The last index in each preference list will be the actual preference
	 * neuron output. Pick action for whichever of these is the largest.
	 */
	public double[] newDirectionalPreferences(GameFacade game, double[][] preferences) {
		double[] preferenceOutputs = ArrayUtil.column(preferences, preferences.length - 1);
		int netChoice = StatisticsUtilities.argmax(preferenceOutputs);
		double[] actionPreferences = ArrayUtil.portion(preferences[netChoice], 0, preferences[netChoice].length - 2);
		// int action = StatisticsUtilities.argmax(actionPreferences);
		return actionPreferences;
	}
}
