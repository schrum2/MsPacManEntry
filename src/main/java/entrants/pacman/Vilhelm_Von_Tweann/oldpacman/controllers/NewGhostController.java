package entrants.pacman.Vilhelm_Von_Tweann.oldpacman.controllers;

import java.util.EnumMap;

import entrants.pacman.Vilhelm_Von_Tweann.oldpacman.game.Constants;

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
