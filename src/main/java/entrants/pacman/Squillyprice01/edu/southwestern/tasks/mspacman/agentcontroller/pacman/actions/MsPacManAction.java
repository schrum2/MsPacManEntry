package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.agentcontroller.pacman.actions;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 * A high-level action for Ms Pacman to follow.
 *
 * @author Jacob Schrum
 */
public interface MsPacManAction {

	public int getMoveAction(GameFacade gf);
}
