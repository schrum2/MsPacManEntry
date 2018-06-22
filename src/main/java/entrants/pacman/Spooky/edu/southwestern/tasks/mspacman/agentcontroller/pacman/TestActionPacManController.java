package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.agentcontroller.pacman;

import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.agentcontroller.pacman.actions.MsPacManAction;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.agentcontroller.pacman.actions.ToFarthestSafeLocationAction;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.data.JunctionNodes;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.data.NodeCollection;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.facades.GhostControllerFacade;
import entrants.pacman.Spooky.oldpacman.controllers.NewPacManController;
import entrants.pacman.Spooky.oldpacman.controllers.examples.AggressiveGhosts;
import entrants.pacman.Spooky.oldpacman.game.Game;

/**
 *
 * @author Jacob Schrum
 */
public class TestActionPacManController extends NewPacManController {

	private final GhostControllerFacade ghostModel;
	private final NodeCollection escapeNodes;
	private final MsPacManAction action;

	public TestActionPacManController(int depth) {
		this.ghostModel = new GhostControllerFacade(new AggressiveGhosts());
		this.escapeNodes = new JunctionNodes();
		this.action = new ToFarthestSafeLocationAction(depth, escapeNodes, ghostModel);
	}

	public int getAction(final Game gs, long timeDue) {
		return getAction(new GameFacade(gs), timeDue);
	}

	public int getAction(GameFacade gs, long timeDue) {
		escapeNodes.updateNodes(gs, gs.getPacmanCurrentNodeIndex(), false);
		int move = action.getMoveAction(gs);
		return move;
	}

	@Override
	public void reset() {
		super.reset();
		escapeNodes.reset();
	}

	@Override
	public void logEvaluationDetails() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
