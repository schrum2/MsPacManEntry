package entrants.pacman.Vilhelm_Von_Tweann.oldpacman.entries.ghosts;

import java.util.EnumMap;

import entrants.pacman.Vilhelm_Von_Tweann.oldpacman.controllers.Controller;
import entrants.pacman.Vilhelm_Von_Tweann.oldpacman.game.Game;
import entrants.pacman.Vilhelm_Von_Tweann.oldpacman.game.Constants.GHOST;
import entrants.pacman.Vilhelm_Von_Tweann.oldpacman.game.Constants.MOVE;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST, MOVE>> {

	private EnumMap<GHOST, MOVE> myMoves = new EnumMap<GHOST, MOVE>(GHOST.class);

	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue) {
		myMoves.clear();

		// Place your game logic here to play the game as the ghosts

		return myMoves;
	}
}