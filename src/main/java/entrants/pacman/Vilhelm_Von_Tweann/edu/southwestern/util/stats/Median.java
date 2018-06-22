package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.util.stats;

/**
 * Compute the median of an array of doubles
 * 
 * @author Jacob Schrum
 */
public class Median implements Statistic {

	@Override
	public double stat(double[] xs) {
		return StatisticsUtilities.median(xs);
	}
}
