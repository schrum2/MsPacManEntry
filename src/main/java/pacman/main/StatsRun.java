package pacman.main;

import java.util.EnumMap;

import entrants.pacman.Squillyprice01.MyPacMan;
import pacman.Executor;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.examples.StarterGhost.POGhost;
import pacman.game.Constants.GHOST;
import pacman.game.util.Stats;

public class StatsRun {
	public static boolean doStats = false;
	
	public static void main(String[] args) {
        Executor executor = new Executor.Builder()
        		//.setVisual(true)
        	  	.setTickLimit(8000)
        	  	.build();		

    	EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);
    	doStats = true;
        MyPacMan badboy = new MyPacMan(true, MyPacMan.MODULE_TYPE.THREE_MULTITASK, 0);
        
        controllers.put(GHOST.INKY, new POGhost(GHOST.INKY));
        controllers.put(GHOST.BLINKY, new POGhost(GHOST.BLINKY));
        controllers.put(GHOST.PINKY, new POGhost(GHOST.PINKY));
        controllers.put(GHOST.SUE, new POGhost(GHOST.SUE));

        Stats[] stats = executor.runExperiment(badboy, new MASController(controllers), 50, "post evals");
        System.out.println(stats[0]);
	}
}
