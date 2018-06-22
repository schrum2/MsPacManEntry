package entrants.pacman.Spooky.oldpacman.controllers;

import java.util.EnumMap;

import entrants.pacman.Spooky.oldpacman.game.Constants;

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
