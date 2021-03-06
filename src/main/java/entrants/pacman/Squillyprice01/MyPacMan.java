package entrants.pacman.Squillyprice01;

import static pacman.game.Constants.MAG;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entrants.pacman.Squillyprice01.edu.southwestern.MMNEAT.MMNEAT;
import entrants.pacman.Squillyprice01.edu.southwestern.evolution.genotypes.*;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.TWEANN;
import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.agentcontroller.pacman.NNMsPacMan;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.data.ScentPath;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Squillyprice01.oldpacman.controllers.*;
import entrants.pacman.Squillyprice01.pacman.prediction.GhostLocation;
import entrants.pacman.Squillyprice01.pacman.prediction.PillModel;
import entrants.pacman.Squillyprice01.pacman.prediction.fast.GhostPredictionsFast;
import entrants.pacman.Squillyprice01.wox.serial.Easy;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Drawable;
import pacman.game.internal.Maze;
import pacman.main.CustomExecutor;
import pacman.main.StatsRun;
import pacman.game.Game;
import pacman.controllers.PacmanController;
/**
 * a class that converts entrants.pacman.Squillyprice01.oldpacman controller information into popacman controller information
 * @author pricew
 *
 */
public class MyPacMan extends PacmanController implements Drawable{

	protected final entrants.pacman.Squillyprice01.oldpacman.controllers.NewPacManController oldpacman;

	public PillModel pillModel = null;
	public Maze currentMaze;
	public GhostPredictionsFast ghostPredictions = null;
	ArrayList<Integer> eatenPills;
	ArrayList<Integer> eatenPowerPills;
	public int lastPowerPillEatenTime = -1;
	public int lastPillEatenTime = -1;
	public int[] ghostEdibleTime;
	private Color[] redAlphas;
	private Game mostRecentGame;
	public boolean usePillModel = Parameters.parameters.booleanParameter("usePillModel");
	public boolean useGhostModel = Parameters.parameters.booleanParameter("useGhostModel");
	public final double GHOST_THRESHOLD = Parameters.parameters.doubleParameter("probabilityThreshold");

	public enum MODULE_TYPE {ONE_MODULE, TWO_MODULES, THREE_MODULES, THREE_MULTITASK, MMD};

	/**
	 * An easy way to load all of the possible saved network files
	 * @param poGhosts Whether evolved using PO Ghosts (alternative is complete observability)
	 * @param modules Module type
	 * @param num Evolution experiment number
	 * @return File name to load from the resources subdir
	 */
	public static final String getChampionNetworkFile(boolean poGhosts, MODULE_TYPE modules, int num) {
		String result = (poGhosts ? "PO" : "CO") + "-";
		switch(modules) {
		case ONE_MODULE:
			result += "1M";
			break;
		case TWO_MODULES:
			result += "2MPref";
			break;
		case THREE_MODULES:
			result += "3MPref";
			break;
		case THREE_MULTITASK:
			result += "3MMultitask";
			break;
		case MMD:
			result += "MMD";
			break;
		}
		result += num + ".xml";
		return result;
	}

	private static final entrants.pacman.Squillyprice01.oldpacman.controllers.NewPacManController getController(boolean poGhosts, MODULE_TYPE modules, int num) {
		String file = getChampionNetworkFile(poGhosts, modules, num);
		System.out.println("Load " + file);
		//string of parameters for the agent
		String params = "drawGhostPredictions:true io:false netio:false highLevel:true infiniteEdibleTime:false imprisonedWhileEdible:false pacManLevelTimeLimit:2147483647 pacmanInputOutputMediator:entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.mediators.po.POCheckEachDirectionMediator edibleTime:200 trapped:true specificGhostEdibleThreatSplit:true specificGhostProximityOrder:true specific:false "+
				"partiallyObservablePacman:true pacmanPO:true useGhostModel:true usePillModel:true probabilityThreshold:0.125 rawScorePacMan:true pacmanMaxLevel:20"+ // Max 4 levels during evolution
				//(poGhosts ? "ghostPO:true" : "ghostPO:false")+ // This is not relevant. It is set directly in the Executor/CustomExecutor
				(modules.equals(MODULE_TYPE.ONE_MODULE) ? "" : " modePheremone:true")+ // Don't color modules if there is only one
				(StatsRun.doStats ? "" : " watch:true") +
				(modules.equals(MODULE_TYPE.THREE_MULTITASK) ? 
						" multitaskModes:3 pacmanMultitaskScheme:entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.multitask.po.POProbableGhostStateModeSelector3Mod" : 
							(modules.equals(MODULE_TYPE.ONE_MODULE) ? "" : " mmdRate:0.1")); // This will not actually mutate anything, but it will cause preference neurons to be detected
		System.out.println(params);
		Parameters.initializeParameterCollections(params.split(" "));
		MMNEAT.loadClasses();
		TWEANNGenotype genotype = (TWEANNGenotype) Easy.load(file);
		System.out.println(genotype);
		return (NewPacManController) (new NNMsPacMan<TWEANN>(genotype).controller);
	}

	public MyPacMan() {
		this(true, MODULE_TYPE.THREE_MULTITASK, 2); // Type used in the CEC 2018 competition
		//this(true, MODULE_TYPE.THREE_MODULES, 0);
		//this(true, MODULE_TYPE.TWO_MODULES, 0);
		//this(true, MODULE_TYPE.MMD, 0);
		//this(true, MODULE_TYPE.ONE_MODULE, 0);

		//this(false, MODULE_TYPE.THREE_MULTITASK, 0);
		//this(false, MODULE_TYPE.THREE_MODULES, 0);
		//this(false, MODULE_TYPE.TWO_MODULES, 0);
		//this(false, MODULE_TYPE.MMD, 0);
		//this(false, MODULE_TYPE.ONE_MODULE, 0);
	}

	public MyPacMan(boolean poGhosts, MODULE_TYPE modules, int num) {
		this(getController(poGhosts, modules, num));
	}

	public MyPacMan(entrants.pacman.Squillyprice01.oldpacman.controllers.NewPacManController oldpacman) {
		this.oldpacman = oldpacman;

		redAlphas = new Color[256];
		for (int i = 0; i < 256; i++) {
			redAlphas[i] = new Color(255, 0, 0, i);
		}

		ghostEdibleTime = new int[GHOST.values().length];
	}


	@Override
	/**
	 * This method returns a popacman move. 
	 * Used for popacman
	 */
	public MOVE getMove(Game game, long timeDue) {
		GameFacade informedGameFacade = updateModels(game, timeDue);
		//get the action to be made
		int action = oldpacman.getAction(informedGameFacade, timeDue);
		// This is triggered if Pac-Man beats the 4th level. However, if the
		// Parameter pacmanMaxLevel is set to a higher value, then the levels will
		// repeat in a loop.
		if(action == NewPacManController.END_GAME_CODE) {
			if(CustomExecutor.usingCustom) {
				System.out.println("Final Score: " + game.getScore());
				return null; // The CustomExecutor can handle this and end the eval
			}
			// The standard Executor doesn't allow this level of control
			if(!StatsRun.doStats) {
				System.out.println("Final Score: " + game.getScore());
				System.exit(0);
			} else {
				return MOVE.NEUTRAL; // Do neutral action if running stats so other trials continue
			}
		}		
		//converts an action to an entrants.pacman.Squillyprice01.oldpacman move to a popacman move to be returned
		return moveConverterOldToPO(GameFacade.indexToMove(action));

	}

	//This method clears all stored information about a game
	public void clearStoredInformation() {
		synchronized(pillModel) {
			pillModel = null;
		}
		synchronized(ghostPredictions) {
			ghostPredictions = null;
		}
	}

	/**
	 * Takes an entrants.pacman.Squillyprice01.oldpacman move and returns the equivalent popacman move
	 * @param move
	 * @return
	 * @throws NoSuchFieldException
	 * @author pricew
	 */
	public static pacman.game.Constants.MOVE moveConverterOldToPO(entrants.pacman.Squillyprice01.oldpacman.game.Constants.MOVE move){
		switch(move) {
		case NEUTRAL:
			return pacman.game.Constants.MOVE.NEUTRAL;
		case UP:
			return pacman.game.Constants.MOVE.UP;
		case LEFT:
			return pacman.game.Constants.MOVE.LEFT;
		case DOWN:
			return pacman.game.Constants.MOVE.DOWN;
		case RIGHT:
			return pacman.game.Constants.MOVE.RIGHT;
		default:
			System.out.println("ERROR in moveConverterOldPO, GAmeFacade.java");
			return null;
		}
	}

	//credit to piers InformationSetMCTSPacMan.java
	//draws the red blocks representing a predicted ghost location
	public void draw(Graphics2D graphics) {
		//Draw Pill Model based on parameter
		if(Parameters.parameters.booleanParameter("drawPillModel") && mostRecentGame != null && pillModel != null) {

			//DRAW PILLS KNOWN TO THE MODEL
			for (int i = 0; i < mostRecentGame.getNumberOfNodes(); i++) {
				boolean isPillAvailable = false;
				synchronized(pillModel) {
					isPillAvailable = pillModel.getPills().get(mostRecentGame.getCurrentMaze().graph[i].nodeIndex);
				}
				if(isPillAvailable) {
					if(!eatenPills.contains(mostRecentGame.getCurrentMaze().graph[i].nodeIndex)) {
						graphics.setColor(new Color(255, 0, 0, 255));
						graphics.fillRect(
								mostRecentGame.getNodeXCood(i) * MAG + 5,
								mostRecentGame.getNodeYCood(i) * MAG + 8,
								2, 2
								);	
					}
				}
			}

			//DRAW POWER PILLS
			for (int i = 0; i < mostRecentGame.getNumberOfNodes(); i++) {
				boolean isPillAvailable = false;
				synchronized(pillModel) {
					isPillAvailable = pillModel.getPowerPills().get(mostRecentGame.getCurrentMaze().graph[i].nodeIndex);
				}
				if(isPillAvailable) {
					if(!eatenPowerPills.contains(mostRecentGame.getCurrentMaze().graph[i].nodeIndex)) {
						graphics.setColor(new Color(255, 0, 0, 255));
						graphics.fillRect(
								mostRecentGame.getNodeXCood(i) * MAG + 5,
								mostRecentGame.getNodeYCood(i) * MAG + 8,
								4, 4
								);	
					}
				}
			}

			//DRAWS PILLS WE CAN SEE
			for(int pill : mostRecentGame.getActivePillsIndices()) {
				assert pillModel != null : "why is this null";
				assert pillModel.getPills() != null : "why is this null";
				boolean isPillAvailable = pillModel.getPills().get(pill);
				if(isPillAvailable) {
					graphics.setColor(new Color(0, 0, 255, 255));
					graphics.fillRect(
							mostRecentGame.getNodeXCood(pill) * MAG + 4,
							mostRecentGame.getNodeYCood(pill) * MAG + 12,
							2, 2
							);
				}

			}
		}


		//Draw predicted Ghost Locations based on parameter
		if(Parameters.parameters.booleanParameter("drawGhostPredictions")) {
			if(ghostPredictions != null) {
				synchronized(ghostPredictions) {
					for (int i = 0; i < mostRecentGame.getNumberOfNodes(); i++) {      	
						double probability = ghostPredictions.calculate(i);
						if (probability >= GHOST_THRESHOLD) {
							double edibleProbability = ghostPredictions.calculateEdible(i);
							if(edibleProbability > 0.0) {
								graphics.setColor(new Color(0,0, 255, 255));
								graphics.fillRect(
										mostRecentGame.getNodeXCood(i) * MAG - 1,
										mostRecentGame.getNodeYCood(i) * MAG + 3,
										14, 14
										);
							} else {
								graphics.setColor(redAlphas[(int) Math.min(255 * probability, 255)]);
								graphics.fillRect(
										mostRecentGame.getNodeXCood(i) * MAG - 1,
										mostRecentGame.getNodeYCood(i) * MAG + 3,
										14, 14
										);	
							}
						}
					}
				}
			}
		}
	}

	//determines whether or not to use this classes draw method
	public boolean enabled() {
		if(Parameters.parameters.booleanParameter("drawGhostPredictions") || Parameters.parameters.booleanParameter("drawPillModel")) {
			return true;
		}
		return false;
	}


	public GameFacade updateModels(Game game, long timeDue) {
		//If we switched mazes, we need new models
		if(currentMaze != game.getCurrentMaze()){
			ScentPath.resetAll(); // Clear scent paths when level changes
			currentMaze = game.getCurrentMaze();
			if(ghostPredictions != null) {
				synchronized(ghostPredictions) {
					ghostPredictions = null;
				}
			}
			if(pillModel != null) {
				synchronized(pillModel) {
					pillModel = null;
				}
			}
			lastPillEatenTime = -1;
			lastPowerPillEatenTime = -1;
			Arrays.fill(ghostEdibleTime, -1);
		}

		if(game.wasPacManEaten()) {//if we were eaten, reset times
			lastPillEatenTime = -1;
			lastPowerPillEatenTime = -1;
		}

		//We need to pass the model of the game to the new gameFacade
		GameFacade informedGameFacade = new GameFacade(game);
		//we need to keep track of the mostRecenGame to pass to the draw method
		mostRecentGame = game;



		//PILL MODEL CODE
		if(usePillModel) {
			if(pillModel == null) {//create the pill model

				//Piers' Code
				pillModel = new PillModel(game.getNumberOfPills());
				int[] pillIndices = informedGameFacade.poG.getCurrentMaze().pillIndices;
				synchronized(pillModel) {
					for (int index : pillIndices) {
						pillModel.observePill(index, true);
					}
					//My code
					int[] powerPillIndices = informedGameFacade.poG.getCurrentMaze().powerPillIndices;
					for (int index : powerPillIndices){
						pillModel.observePowerPill(index, true);
					}
				}

				eatenPills = new ArrayList<Integer>();
				eatenPowerPills = new ArrayList<Integer>();

				informedGameFacade.setPillModel(pillModel);
				informedGameFacade.setTimeOfLastPowerPillEaten(lastPowerPillEatenTime);
				informedGameFacade.setTimeOfLastPillEaten(lastPillEatenTime);

			} 

			assert pillModel != null : "there is an if that checks it above. A null pillModel would break this code";

			//tell the game what the pill model looks like
			int pillIndex = informedGameFacade.poG.getPillIndex(informedGameFacade.poG.getPacmanCurrentNodeIndex());				        
			if (pillIndex != -1) {
				Boolean pillState = informedGameFacade.poG.isPillStillAvailable(pillIndex);
				if (pillState != null && !pillState) {//we can see the pill spot and it has been eaten
					pillModel.update(informedGameFacade.poG.getPacmanCurrentNodeIndex());
					eatenPills.add(informedGameFacade.poG.getPacmanCurrentNodeIndex());
					lastPillEatenTime = informedGameFacade.getCurrentLevelTime();
					informedGameFacade.setTimeOfLastPillEaten(informedGameFacade.getCurrentLevelTime());
				}
			}

			int powerPillIndex = informedGameFacade.poG.getPowerPillIndex(informedGameFacade.poG.getPacmanCurrentNodeIndex());				        
			if (powerPillIndex != -1) {
				Boolean powerPillState = informedGameFacade.poG.isPowerPillStillAvailable(powerPillIndex);
				if (powerPillState != null && !powerPillState) {
					pillModel.update(informedGameFacade.poG.getPacmanCurrentNodeIndex());
					eatenPowerPills.add(informedGameFacade.poG.getPacmanCurrentNodeIndex());
					lastPowerPillEatenTime = informedGameFacade.getCurrentLevelTime();
					informedGameFacade.setTimeOfLastPowerPillEaten(informedGameFacade.getCurrentLevelTime());
				}
			}

			informedGameFacade.setTimeOfLastPillEaten(lastPillEatenTime);
			informedGameFacade.setTimeOfLastPowerPillEaten(lastPowerPillEatenTime);
			informedGameFacade.setPillModel(pillModel);
		}


		//GHOST MODEL CODE
		if(useGhostModel) {
			//if pacman was eaten, ghosts are in the lair
			if (informedGameFacade.poG.wasPacManEaten() && ghostPredictions != null) {
				synchronized(ghostPredictions) {
					ghostPredictions = null;
				}
			}

			//init the ghost predictions
			if(ghostPredictions == null){				
				//create a new ghostPredictionFast object, initialize it, and pass it to informedGameFacade
				ghostPredictions = new GhostPredictionsFast(informedGameFacade.poG.getCurrentMaze(), ghostEdibleTime);
				ghostPredictions.preallocate();
				informedGameFacade.setGhostPredictions(this.ghostPredictions);
			} 

			assert ghostPredictions != null : "it should be set by now";

			// Get observations of ghosts and pass them in to the predictor
			//Credit to piers InformationSetMCTSPacmMan.java, cited 6/4/18
			for (GHOST ghost : GHOST.values()) {
				if (ghostEdibleTime[ghost.ordinal()] != -1) { //ghost is edible
					ghostEdibleTime[ghost.ordinal()]--; //tick the edible time down
				}

				int ghostIndex = game.getGhostCurrentNodeIndex(ghost);
				if (ghostIndex != -1) { //we have a line of sight on the ghost
					try {
						ghostPredictions.observe(ghost, ghostIndex, informedGameFacade.poG.getGhostLastMoveMade(ghost), informedGameFacade);
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
						break;
					}
					ghostEdibleTime[ghost.ordinal()] = game.getGhostEdibleTime(ghost);
				} else {
					List<GhostLocation> locations = ghostPredictions.getGhostLocations(ghost); //get our predicted ghost location
					locations.stream().filter(location -> informedGameFacade.poG.isNodeObservable(location.getIndex())).forEach(location -> {
						//observe the ghost as not present where it isn't to update probabilities
						ghostPredictions.observeNotPresent(ghost, location.getIndex(), informedGameFacade);
					});
				}
			}

			ghostPredictions.update(informedGameFacade);
			informedGameFacade.setGhostPredictions(ghostPredictions);
		}

		return informedGameFacade;
	}


}
