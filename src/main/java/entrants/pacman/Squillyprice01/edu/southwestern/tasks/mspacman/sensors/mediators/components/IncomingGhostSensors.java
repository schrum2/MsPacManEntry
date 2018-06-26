/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.mediators.components;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.BlockLoadedInputOutputMediator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.IncomingGhostSensorBlock;

/**
 *
 * @author Jacob Schrum
 */
public class IncomingGhostSensors extends BlockLoadedInputOutputMediator {

	public IncomingGhostSensors(boolean senseEdibleGhosts) {
		super();
		blocks.add(new IncomingGhostSensorBlock(true));
		if (senseEdibleGhosts) {
			blocks.add(new IncomingGhostSensorBlock(false));
		}
	}
}
