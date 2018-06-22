package entrants.pacman.Vilhelm_Von_Tweann.pacman.prediction;

import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.internal.Maze;

import java.util.ArrayList;
import java.util.List;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Vilhelm_Von_Tweann.pacman.prediction.fast.GhostPredictionsFast;

/**
 *
 * Ghost Predictor
 *
 * Predicts ghosts locations in the future.
 * Created by Piers on 14/07/2016.
 */
public class GhostPredictor {

    private List<GhostPredictionsFast> ghostPredictions = new ArrayList<>();
    private boolean observationMade = false;

    /**
     * Clears the current predictions and starts afresh including a possible new maze
     * or new life on the same maze.
     */
    public void clear(Maze maze){
        ghostPredictions.clear();
        ghostPredictions.add(new GhostPredictionsFast(maze));
    }

    /**
     * This didn't used to take a GameFacade, but it is necessary now.
     * @param ghost
     * @param ghostIndex
     * @param lastMoveMade
     * @param game
     */
    public void addObservation(GHOST ghost, int ghostIndex, MOVE lastMoveMade, GameFacade game){
        if(ghostPredictions.isEmpty()) return;
        ghostPredictions.get(0).observe(ghost, ghostIndex, lastMoveMade, game);
        observationMade = true;
    }

    public void observeNotPresent(GHOST ghost, int ghostIndex, GameFacade game){
        if(ghostPredictions.isEmpty()) return;
        ghostPredictions.get(0).observeNotPresent(ghost, ghostIndex, game);
        observationMade = true;
    }

    public void observationsFinished(){
        if(observationMade){
            // Save the first one - it contains the observations that are new
            GhostPredictionsFast first = ghostPredictions.get(0);
            ghostPredictions.clear();
            ghostPredictions.add(first);
        }
        observationMade = false;
    }

    public List<GhostLocation> getCurrentLocations(GHOST ghost){
        if(ghostPredictions.isEmpty()) return new ArrayList<>();
        return ghostPredictions.get(0).getGhostLocations(ghost);
    }

    /**
     * Discard the current first prediction
     */
    public void discardCurrent(){
        if(ghostPredictions.isEmpty()) return;
        ghostPredictions.remove(0);
    }

    /**
     * Just in Time population of the list
     * @param depth The depth we are interested in
     * @param index The index in the map we are interested in
     * @return The value at that location
     */
    public double getPredictions(int depth, int index){
        if(ghostPredictions.isEmpty()) return 0.0d;
        if(ghostPredictions.size() <= depth){
            for(int i = ghostPredictions.size(); i <= depth; i++){
                GhostPredictionsFast temp = ghostPredictions.get(i - 1).copy();
                temp.update();
                ghostPredictions.add(temp);
            }
        }

        return ghostPredictions.get(depth).calculate(index);
    }
    
    public double getEdiblePredictions(int depth, int index){
        if(ghostPredictions.isEmpty()) return 0.0d;
        if(ghostPredictions.size() <= depth){
            for(int i = ghostPredictions.size(); i <= depth; i++){
                GhostPredictionsFast temp = ghostPredictions.get(i - 1).copy();
                temp.update();
                ghostPredictions.add(temp);
            }
        }

        return ghostPredictions.get(depth).calculateEdible(index);
    }
}
