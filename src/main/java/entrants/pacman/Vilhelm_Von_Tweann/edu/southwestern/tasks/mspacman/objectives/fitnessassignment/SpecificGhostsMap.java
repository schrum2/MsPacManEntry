/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.objectives.fitnessassignment;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.parameters.CommonConstants;

/**
 *
 * @author Jacob Schrum
 */
public class SpecificGhostsMap implements FitnessToModeMap {

	public int[] associatedFitnessScores() {
		int[] result = new int[CommonConstants.numActiveGhosts];
		System.arraycopy(SPECIFIC_GHOSTS, 0, result, 0, CommonConstants.numActiveGhosts);
		return result;
	}
}
