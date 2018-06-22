package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.agentcontroller.pacman;

import entrants.pacman.Spooky.edu.southwestern.networks.Network;
import entrants.pacman.Spooky.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 * Given the sensors, generate a utility for
 * each available direction and pick the one with the
 * highest utility.
 * 
 * @author Jacob Schrum
 */
public class ReactiveNNPacManController extends NNDirectionalPacManController {

	public ReactiveNNPacManController(Network n) {
		super(n);
	}

	@Override
	public double[] getDirectionPreferences(GameFacade gf) {
		final int current = gf.getPacmanCurrentNodeIndex();
		assert this.inputMediator != null : "Why is inputMediator null?";
		assert gf != null : "Why is gf null?";
		assert gf.getPacmanLastMoveMade() == 0 || gf.getPacmanLastMoveMade() == 1 || gf.getPacmanLastMoveMade() == 2 || gf.getPacmanLastMoveMade() == 3 : "Why is pacmans last move null?";
		double[] inputs = inputMediator.getInputs(gf, gf.getPacmanLastMoveMade());
		if (nn.isMultitask()) {
			ms.giveGame(gf);
			nn.chooseMode(ms.mode());
		}
		double[] outputs = nn.process(inputs);

		// Make directions towards walls impossible to choose
		final int referenceDir = CommonConstants.relativePacmanDirections ? gf.getPacmanLastMoveMade() : 0;
		if (CommonConstants.eliminateImpossibleDirections) {
			final int[] neighbors = gf.neighbors(current);
			for (int i = 0; i < GameFacade.NUM_DIRS; i++) {
				int dir = (referenceDir + i) % GameFacade.NUM_DIRS;
				// Disable blocked paths
				if (neighbors[dir] == -1) {
					outputs[i] = -Double.MAX_VALUE;
				}
			}
		}

		double[] absoluteDirectionPreferences = new double[GameFacade.NUM_DIRS + (externalPreferenceNeurons ? 1 : 0)];
		for (int i = 0; i < GameFacade.NUM_DIRS; i++) {
			int dir = (referenceDir + i) % GameFacade.NUM_DIRS;
			absoluteDirectionPreferences[dir] = outputs[i];
		}
		if (externalPreferenceNeurons) {
			absoluteDirectionPreferences[absoluteDirectionPreferences.length - 1] = outputs[absoluteDirectionPreferences.length - 1];
		}

		return absoluteDirectionPreferences;
	}
}
