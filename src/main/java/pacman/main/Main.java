package pacman.main;

import java.util.EnumMap;

import entrants.ghosts.Squillyprice01.George;
import entrants.ghosts.Squillyprice01.John;
import entrants.ghosts.Squillyprice01.Paul;
import entrants.ghosts.Squillyprice01.Ringo;
import entrants.pacman.Squillyprice01.MyPacMan;
import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;
import pacman.Executor;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.game.Constants.GHOST;

/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {

    public static void main(String[] args) {

    	// Also initializes parameters
        MyPacMan badboy = new MyPacMan();

        Executor executor = new Executor.Builder()
                .setVisual(true)
                .setGhostPO(Parameters.parameters.booleanParameter("ghostPO"))
                .setPacmanPO(Parameters.parameters.booleanParameter("pacmanPO"))
                .setTickLimit(4000)
                .build();

        EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);
        
        controllers.put(GHOST.INKY, new George());
        controllers.put(GHOST.BLINKY, new Ringo());
        controllers.put(GHOST.PINKY, new Paul());
        controllers.put(GHOST.SUE, new John());

        executor.runGameTimed(badboy, new MASController(controllers));
    }
}
