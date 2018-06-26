/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.objectives.fitnessassignment;

import java.util.Arrays;

import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;

/**
 *
 * @author Jacob Schrum
 */
public class GeneralAllMap implements FitnessToModeMap {

	private final int members;

	public GeneralAllMap() {
		this(Parameters.parameters.integerParameter("numCoevolutionSubpops"));
	}

	public GeneralAllMap(int members) {
		this.members = members;
	}

	public int[] associatedFitnessScores() {
		int[] result = new int[members];
		Arrays.fill(result, NO_PREFERENCE);
		return result;
	}
}
