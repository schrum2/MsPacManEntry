package edu.southwestern.tasks.mspacman.sensors;

import edu.southwestern.parameters.CommonConstants;
import edu.southwestern.tasks.mspacman.facades.GameFacade;
import edu.southwestern.tasks.mspacman.sensors.blocks.MsPacManSensorBlock;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A sensor input block that is broken up into various sensor blocks then added
 * together to the overall total of sensor inputs and labels
 * 
 * @author Jacob Schrum
 */
public class BlockLoadedInputOutputMediator extends MsPacManControllerInputOutputMediator {

	public ArrayList<MsPacManSensorBlock> blocks;
	private int numSensors = 0;

	public BlockLoadedInputOutputMediator() {
		super();
		blocks = new ArrayList<MsPacManSensorBlock>();
	}

	@Override
	/**
	 * retrieves and returns the sensor values based on all of the different
	 * sensor blocks provided/requested
	 * 
	 * @param gs,
	 *            the game instance
	 * @param currentDir,
	 *            the current direction
	 * @return the sensor inputs
	 */
	public double[] getInputs(GameFacade gs, int currentDir) {
		double[] inputs = new double[numIn()];
		int in = 0;
		for (int i = 0; i < blocks.size(); i++) {
			in = CommonConstants.pacManSensorCaching ? blocks.get(i).retrieveSensors(inputs, in, gs, currentDir)
					: blocks.get(i).incorporateSensors(inputs, in, gs, currentDir);
		}
		assert(in == numIn()) : "Improper inputs for Ms Pac-Man. Only " + in + " inputs: " + Arrays.toString(inputs);
		return inputs;
	}

	@Override
	/**
	 * retrieves and returns the sensor labels based on all of the different
	 * sensor blocks provided/requested
	 */
	public String[] sensorLabels() {
		String[] labels = new String[numIn()];
		int in = 0;
		for (int i = 0; i < blocks.size(); i++) {
			in = blocks.get(i).incorporateLabels(labels, in);
		}
		assert(in == numIn()) : "Improper inputs for Ms Pac-Man. Only " + in + " inputs: " + Arrays.toString(labels);
		return labels;
	}

	/**
	 * Save result of calculation and reuse instead of repeating
	 *
	 * @return
	 */
	@Override
	public int numIn() {
		if (numSensors == 0) {
			for (int i = 0; i < blocks.size(); i++) {
				numSensors += blocks.get(i).numberAdded();
			}
		}
		return numSensors;
	}

	@Override
	/**
	 * clear and reset the arrayList of MsPacManSensorBlock's
	 */
	public void reset() {
		super.reset();
		for (MsPacManSensorBlock b : blocks) {
			b.reset();
		}
	}
}
