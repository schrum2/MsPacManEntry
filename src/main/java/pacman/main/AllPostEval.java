package pacman.main;

import java.util.EnumMap;

import entrants.pacman.Squillyprice01.MyPacMan;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.examples.StarterGhost.POGhost;
import pacman.game.Constants.GHOST;
import pacman.game.util.Stats;

public class AllPostEval {
	public static void main(String[] args) {
		CustomExecutor executor = new CustomExecutor.Builder()
				//.setVisual(true)
				.setTickLimit(8000)
				.build();		

		boolean[] ghostsArePO = new boolean[] {true, false};
		for(boolean poGhosts : ghostsArePO) {
			for(MyPacMan.MODULE_TYPE modules: MyPacMan.MODULE_TYPE.values()) {        	
				for(int run = 0; run < 20; run++) {
					EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);
					StatsRun.doStats = true;
					MyPacMan badboy = new MyPacMan(poGhosts, modules, run);

					controllers.put(GHOST.INKY, new POGhost(GHOST.INKY));
					controllers.put(GHOST.BLINKY, new POGhost(GHOST.BLINKY));
					controllers.put(GHOST.PINKY, new POGhost(GHOST.PINKY));
					controllers.put(GHOST.SUE, new POGhost(GHOST.SUE));

					Stats[] stats = executor.runExperiment(badboy, new MASController(controllers), 2, "post evals");
					System.out.println((poGhosts ? "POGhosts" : "COGhosts") + " " + modules.name() + " " + run + ": " + stats[0]);
				}
			}
		}
	}
}
