package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades;

import pacman.game.GameView;


/**
 *
 * @author Jacob Schrum
 */
public class ExecutorFacade {

	//ORIGINAL
	//poPacman.Executor poE = null;
	//pacman.CustomExecutor poE = null;
	
	//TO GET IT TO RUN
	pacman.Executor poE = null;
	public GameView forceGameView = null;

	/**
	 * Constructor that contains excecutor
	 * (thus why called facade)
	 * @param e
	 */
//	public ExecutorFacade(poPacman.Executor e) {
//		poE = e;
//	}
	
	/**
	 * Used for Partially Observable Pacman
	 * Constructor that contains excecutor
	 * (thus why called facade)
	 * @param e
	 */
	public ExecutorFacade(pacman.Executor e) {
		poE = e;
	}

	/**
	 * creates log of given name
	 * @param string name of log
	 */
	public void log(String string) {
//		if(poE == null) {
//			//TODO: test
//			poE.log(string);
//		} else {
//			poE.log(string);
//		}
	}

	/**
	 * runs a game timed
	 * @param mspacman facade contianing controller for ms pacman
	 * @param ghosts facade containing controller for ghosts
	 * @param game facade containing game
	 */
	public void runGameTimed(PacManControllerFacade mspacman, GhostControllerFacade ghosts, GameFacade game) {
		poE.runGameTimed(mspacman.poP, ghosts.poG);
	}

	/**
	 * runs an experiment
	 * @param mspacman facade contianing controller for ms pacman
	 * @param ghosts facade containing controller for ghosts
	 * @param game facade containing game
	 */
	public void runExperiment(PacManControllerFacade mspacman, GhostControllerFacade ghosts, GameFacade game) {
		poE.runExperiment(mspacman.poP, ghosts.poG, 1, "");
	}

	/**
	 * runs a timed game and records it
	 * @param game facade of game 
	 * @param mspacman facade of controller 
	 * @param ghosts facade of ghots 
	 * @param visual whether or not to visualize run
	 * @param fileName name of file to store
	 */
	public void runGameTimedRecorded(GameFacade game, PacManControllerFacade mspacman, GhostControllerFacade ghosts, boolean visual, String fileName) {
		poE.runGameTimedRecorded(mspacman.poP, ghosts.poG, fileName);
	}

	/**
	 * Replays given game
	 * @param fileName name of game to replay
	 * @param visual whether or not to visualize game
	 */
	public void replayGame(String fileName, boolean visual) {
		poE.replayGame(fileName, visual);
	}

	/**
	 * runs a timed game with visuals off
	 * @param game facade of game
	 * @param mspacman facade of controller
	 * @param ghosts facade of ghosts
	 */
	public void runGameTimedNonVisual(GameFacade game, PacManControllerFacade mspacman, GhostControllerFacade ghosts) {
		poE.runGameTimedSpeedOptimised(mspacman.poP, ghosts.poG, false, "");
	}
	
//	public void forceGame(GameFacade game, PacManControllerFacade mspacman, GhostControllerFacade ghosts, MOVE move) {
//		assert poE != null : "This method is only for the CustomExecutor class";
//		if(poE != null) {
//			forceGame(mspacman, ghosts, game, move, Parameters.parameters.booleanParameter("watch"));
//		} else {
//			throw new UnsupportedOperationException("This method is only for the CustomExecutor class");
//		}
//	}
//	
//	private void forceGame(PacManControllerFacade mspacman, GhostControllerFacade ghosts, GameFacade game,  MOVE move, boolean visuals) {
//		forceGameView = poE.forceGame(mspacman.poP, ghosts.poG, game.poG, move, visuals, forceGameView);
//	}
}
