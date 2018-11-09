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
	public static void main(String[] args) {
        Executor executor = new Executor.Builder()
        		//.setVisual(true)
        	  	.setTickLimit(8000)
        	  	.build();		

    	EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);

        MyPacMan badboy = new MyPacMan();
        
        controllers.put(GHOST.INKY, new POGhost(GHOST.INKY));
        controllers.put(GHOST.BLINKY, new POGhost(GHOST.BLINKY));
        controllers.put(GHOST.PINKY, new POGhost(GHOST.PINKY));
        controllers.put(GHOST.SUE, new POGhost(GHOST.SUE));

        Stats[] stats = executor.runExperiment(badboy, new MASController(controllers), 100, "post evals");
        System.out.println(stats[0]);
	}
}
