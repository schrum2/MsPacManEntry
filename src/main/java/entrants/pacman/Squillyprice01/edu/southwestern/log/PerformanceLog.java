package entrants.pacman.Squillyprice01.edu.southwestern.log;

import java.util.ArrayList;

import entrants.pacman.Squillyprice01.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Squillyprice01.edu.southwestern.scores.Score;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.agentcontroller.pacman.NNPacManController;

/**
 *
 *
 * @author Jacob Schrum
 */
public class PerformanceLog<T> extends StatisticsLog<Score<T>> {

	public static ArrayList<String> getLabels() {
		ArrayList<String> result = new ArrayList<String>(2);
		result.add("Total Eval Time");
		result.add("Average Eval Time");
		result.add("Trials");
		result.add("Times All Levels Beaten");
		result.add("Times Time Limit Reached");
		result.add("Times Died");
		return result;
	}

	public PerformanceLog(String prefix) {
		super(prefix, getLabels());
	}

	@Override
	public void log(ArrayList<Score<T>> scores, int generation) {
		double[][] nextStage = new double[scores.size()][];
		for (int i = 0; i < scores.size(); i++) {
			nextStage[i] = new double[6];

			nextStage[i][0] = scores.get(i).totalEvalTime;
			nextStage[i][1] = scores.get(i).averageEvalTime;
			nextStage[i][2] = CommonConstants.trials;
			nextStage[i][3] = NNPacManController.timesAllLevelsBeaten;
			nextStage[i][4] = NNPacManController.timesTimeLimitReached;
			nextStage[i][5] = NNPacManController.timesDied;
			NNPacManController.resetTimes();
		}
		logAverages(nextStage, generation);
	}
}
