package pacman.main;

import java.util.EnumMap;

import pacman.Executor;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.entrants.ghosts.spooky.*;
import pacman.entrants.pacman.spooky.*;
import pacman.entries.pacman.MyPacMan;
import edu.southwestern.tasks.mspacman.sensors.mediators.po.*;
import edu.southwestern.tasks.popacman.controllers.OldToNewPacManIntermediaryController;
import pacman.examples.StarterISMCTS.InformationSetMCTSPacMan;
import pacman.game.Constants.GHOST;

/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {

    public static void main(String[] args) {

        Executor executor = new Executor.Builder()
                .setVisual(true)
                .setTickLimit(4000)
                .build();

        EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);

        OldToNewPacManIntermediaryController badboy = new OldToNewPacManIntermediaryController();
        
        controllers.put(GHOST.INKY, new George());
        controllers.put(GHOST.BLINKY, new Ringo());
        controllers.put(GHOST.PINKY, new Paul());
        controllers.put(GHOST.SUE, new John());
        
        

        executor.runGameTimed(badboy, new MASController(controllers));
    }
}
