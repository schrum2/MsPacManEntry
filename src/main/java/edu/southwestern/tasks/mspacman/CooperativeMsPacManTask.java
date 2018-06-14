package edu.southwestern.tasks.mspacman;

import edu.southwestern.evolution.genotypes.Genotype;
import edu.southwestern.evolution.nsga2.tug.TUGTask;
import edu.southwestern.networks.Network;
import edu.southwestern.networks.NetworkTask;
import edu.southwestern.scores.Score;
import edu.southwestern.tasks.GroupTask;
import edu.southwestern.tasks.SinglePopulationTask;
import java.util.ArrayList;

/**
 * Variant of the pacman task that takes several networks and combines them into
 * one organism. This is very similar to having a single organism that is a
 * hierarchical network, but since each component network needs to come from a
 * separate subpopulation that is evolving, fitness information needs to be
 * propagated back to each network, which requires this different setup and
 * inheritance pattern.
 *
 * @author Jacob Schrum
 * @param <T> phenotype
 */
public abstract class CooperativeMsPacManTask<T extends Network> extends GroupTask implements NetworkTask, SinglePopulationTask<T>, TUGTask {

	public MsPacManTask<T> task;

        @Override
	public ArrayList<Score<T>> evaluateAll(ArrayList<Genotype<T>> population) {
		throw new UnsupportedOperationException(
                                  "This method should not actually be called when using coevolution. "
				+ "It is merely here to satisfy type requirements and allow the code " 
                                + "to compile");
	}

        @Override
	public double[] startingGoals() {
		return task.startingGoals();
	}

	public CooperativeMsPacManTask() {
		task = new MsPacManTask<T>();
	}

        @Override
	public int numObjectives() {
		return task.numObjectives();
	}

        @Override
	public double getTimeStamp() {
		return task.getTimeStamp();
	}

        @Override
	public String[] sensorLabels() {
		return task.sensorLabels();
	}

        @Override
	public String[] outputLabels() {
		return task.outputLabels();
	}

        @Override
	public abstract int numberOfPopulations();

        @Override
	public void finalCleanup() {
	}
}
