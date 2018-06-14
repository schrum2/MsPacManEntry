/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.southwestern.tasks.mspacman.sensors.blocks.distance;

import edu.southwestern.parameters.CommonConstants;
import edu.southwestern.tasks.mspacman.facades.GameFacade;
import java.util.Arrays;

/**
 *
 * @author Jacob Schrum
 */
public class AverageThreatGhostDistanceBlock extends AverageDistanceBlock {

	@Override
	public int[] getTargets(GameFacade gf) {
		int[] presentThreats = gf.getThreatGhostLocations();
		int[] totalThreats = new int[CommonConstants.numActiveGhosts];
		Arrays.fill(totalThreats, -1);
		System.arraycopy(presentThreats, 0, totalThreats, 0, presentThreats.length);
		return totalThreats;
	}

	@Override
	public String getType() {
		return "Threat Ghost";
	}
}
