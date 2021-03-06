package entrants.pacman.Squillyprice01.oldpacman.controllers.examples;

import java.util.EnumMap;

import entrants.pacman.Squillyprice01.oldpacman.controllers.NewGhostController;
import entrants.pacman.Squillyprice01.oldpacman.game.Game;
import entrants.pacman.Squillyprice01.oldpacman.game.Constants.DM;
import entrants.pacman.Squillyprice01.oldpacman.game.Constants.GHOST;
import entrants.pacman.Squillyprice01.oldpacman.game.Constants.MOVE;

/*
 * The Class Legacy.
 */
public class BetterLegacy extends NewGhostController {

	@Override
	public void reset() {
		super.reset();
		myMoves = new EnumMap<GHOST, MOVE>(GHOST.class);
		moves = MOVE.values();
	}

	EnumMap<GHOST, MOVE> myMoves = new EnumMap<GHOST, MOVE>(GHOST.class);
	MOVE[] moves = MOVE.values();

	/*
	 * (non-Javadoc)
	 * 
	 * @see pacman.controllers.Controller#getMove(pacman.game.Game, long)
	 */
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		myMoves.clear();

		int targetNode = game.getPacmanCurrentNodeIndex();

		if (game.doesGhostRequireAction(GHOST.BLINKY)) {
			myMoves.put(GHOST.BLINKY, game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.BLINKY),
					targetNode, game.getGhostLastMoveMade(GHOST.BLINKY), DM.PATH));
		}

		if (game.doesGhostRequireAction(GHOST.INKY)) {
			myMoves.put(GHOST.INKY, game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.INKY), targetNode,
					game.getGhostLastMoveMade(GHOST.INKY), DM.MANHATTAN));
		}

		if (game.doesGhostRequireAction(GHOST.PINKY)) {
			myMoves.put(GHOST.PINKY, game.getNextMoveTowardsTarget(game.getGhostCurrentNodeIndex(GHOST.PINKY),
					targetNode, game.getGhostLastMoveMade(GHOST.PINKY), DM.EUCLID));
		}

		if (game.doesGhostRequireAction(GHOST.SUE)) {
			myMoves.put(GHOST.SUE, moves[game.rnd.nextInt(moves.length)]);
		}

		return myMoves;
	}
}