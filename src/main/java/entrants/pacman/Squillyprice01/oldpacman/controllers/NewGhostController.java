package entrants.pacman.Squillyprice01.oldpacman.controllers;

import java.util.EnumMap;

import entrants.pacman.Squillyprice01.oldpacman.game.Constants;

/**
 * TODO
 *
 * @author Jacob
 */
public abstract class NewGhostController extends Controller<EnumMap<Constants.GHOST, Constants.MOVE>> {

	/**
	 * TODO
	 */
	public void reset() {
		super.threadRevive();
	}
}
