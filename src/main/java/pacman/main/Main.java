package pacman.main;

import java.util.EnumMap;

import entrants.pacman.Squillyprice01.MyPacMan;
import entrants.pacman.Squillyprice01.MyPacMan.MODULE_TYPE;
import pacman.Executor;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.examples.StarterGhost.POGhost;
import pacman.game.Constants.GHOST;

/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {
	
	

    public static void main(String[] args) {
    	
    	//Decide whether or not to use the competition executor or our custom debug executor
    	boolean debug = true;
    	if(debug) {
    		CustomExecutor executor = new CustomExecutor.Builder()
	    			.setVisual(true)
	              	.setTickLimit(8000)
	    			.setPO(true)
	              	.build();
    		
        	EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);

            MyPacMan badboy = new MyPacMan(true, MODULE_TYPE.THREE_MULTITASK, 2);
            
            controllers.put(GHOST.INKY, new POGhost(GHOST.INKY));
            controllers.put(GHOST.BLINKY, new POGhost(GHOST.BLINKY));
            controllers.put(GHOST.PINKY, new POGhost(GHOST.PINKY));
            controllers.put(GHOST.SUE, new POGhost(GHOST.SUE));
            
            boolean secondViewer = true; // View in PO and non-PO mode
            executor.runGameTimed(badboy, new MASController(controllers), secondViewer);
	        System.out.println("Evaluation over");
    	} else {
    		
	        Executor executor = new Executor.Builder()
	        		.setVisual(true)
	        	  	.setTickLimit(8000)
	        	  	.build();		
	        
	    	EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);

	        MyPacMan badboy = new MyPacMan();
	        
	        controllers.put(GHOST.INKY, new POGhost(GHOST.INKY));
	        controllers.put(GHOST.BLINKY, new POGhost(GHOST.BLINKY));
	        controllers.put(GHOST.PINKY, new POGhost(GHOST.PINKY));
	        controllers.put(GHOST.SUE, new POGhost(GHOST.SUE));
	        
	        executor.runGameTimed(badboy, new MASController(controllers));
	        System.out.println("Evaluation over");
	        
    	}
    }
}
