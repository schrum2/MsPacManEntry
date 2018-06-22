package entrants.pacman.Spooky.oldpacman.controllers.examples;

import java.util.EnumMap;

import entrants.pacman.Spooky.oldpacman.controllers.NewGhostController;
import entrants.pacman.Spooky.oldpacman.game.Game;
import entrants.pacman.Spooky.oldpacman.game.Constants.GHOST;
import entrants.pacman.Spooky.oldpacman.game.Constants.MOVE;

/*
 * The Class RandomGhosts.
 */
public final class RandomGhosts extends NewGhostController {

	@Override
	public void reset() {
		super.reset();
		moves = new EnumMap<GHOST, MOVE>(GHOST.class);
		allMoves = MOVE.values();
	}

	private EnumMap<GHOST, MOVE> moves = new EnumMap<GHOST, MOVE>(GHOST.class);
	private MOVE[] allMoves = MOVE.values();

	/*
	 * (non-Javadoc)
	 * 
	 * @see pacman.controllers.Controller#getMove(pacman.game.Game, long)
	 */
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		moves.clear();

		for (GHOST ghostType : GHOST.values()) {
			if (game.doesGhostRequireAction(ghostType)) {
				moves.put(ghostType, allMoves[game.rnd.nextInt(allMoves.length)]);
			}
		}

		return moves;
	}
}