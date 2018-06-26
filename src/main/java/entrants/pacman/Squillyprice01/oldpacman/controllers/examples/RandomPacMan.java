package entrants.pacman.Squillyprice01.oldpacman.controllers.examples;

import entrants.pacman.Squillyprice01.oldpacman.controllers.Controller;
import entrants.pacman.Squillyprice01.oldpacman.game.Game;
import entrants.pacman.Squillyprice01.oldpacman.game.Constants.MOVE;

/*
 * The Class RandomPacMan.
 */
public final class RandomPacMan extends Controller<MOVE> {

	private MOVE[] allMoves = MOVE.values();

	/*
	 * (non-Javadoc)
	 * 
	 * @see pacman.controllers.Controller#getMove(pacman.game.Game, long)
	 */
	public MOVE getMove(Game game, long timeDue) {
		return allMoves[game.rnd.nextInt(allMoves.length)];
	}
}