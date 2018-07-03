package entrants.pacman.Squillyprice01.edu.southwestern.MMNEAT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entrants.pacman.Squillyprice01.edu.southwestern.evolution.EvolutionaryHistory;
import entrants.pacman.Squillyprice01.edu.southwestern.evolution.genotypes.CombinedGenotype;
import entrants.pacman.Squillyprice01.edu.southwestern.evolution.genotypes.Genotype;
import entrants.pacman.Squillyprice01.edu.southwestern.evolution.genotypes.TWEANNGenotype;
import entrants.pacman.Squillyprice01.edu.southwestern.experiment.Experiment;
import entrants.pacman.Squillyprice01.edu.southwestern.log.EvalLog;
import entrants.pacman.Squillyprice01.edu.southwestern.log.MMNEATLog;
import entrants.pacman.Squillyprice01.edu.southwestern.log.PerformanceLog;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.ActivationFunctions;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.TWEANN;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.hyperneat.Bottom1DSubstrateMapping;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.hyperneat.SubstrateArchitectureDefinition;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.hyperneat.SubstrateCoordinateMapping;
import entrants.pacman.Squillyprice01.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;
import entrants.pacman.Squillyprice01.edu.southwestern.scores.Score;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.ensemble.MsPacManEnsembleArbitrator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.init.MsPacManInitialization;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.multitask.MsPacManModeSelector;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.MsPacManControllerInputOutputMediator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.VariableDirectionBlockLoadedInputOutputMediator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.directional.VariableDirectionBlock;
import entrants.pacman.Squillyprice01.edu.southwestern.util.ClassCreation;
import entrants.pacman.Squillyprice01.edu.southwestern.util.file.FileUtilities;
import entrants.pacman.Squillyprice01.edu.southwestern.util.graphics.DrawingPanel;
import entrants.pacman.Squillyprice01.edu.southwestern.util.random.RandomGenerator;
import entrants.pacman.Squillyprice01.edu.southwestern.util.random.RandomNumbers;
import entrants.pacman.Squillyprice01.edu.southwestern.util.stats.Statistic;
import entrants.pacman.Squillyprice01.oldpacman.Executor;
import entrants.pacman.Squillyprice01.wox.serial.Easy;

/**
 * Modular Multiobjective Neuro-Evolution of Augmenting Topologies.
 * 
 * Main class that launches experiments.
 * Running "mvn -U install" will create a SNAPSHOT jar recognized by all
 * batch files.
 * 
 * @author Jacob Schrum
 */
public class MMNEAT {

	public static boolean seedExample = false;
	public static int networkInputs = 0;
	public static int networkOutputs = 0;
	public static int modesToTrack = 0;
	public static double[] lowerInputBounds;
	public static double[] upperInputBounds;
	public static int[] discreteCeilings;
	public static Experiment experiment;
//	public static Task task;
//	public static EA ea;
	@SuppressWarnings("rawtypes") // could hold any type, depending on command line
	public static Genotype genotype;
	@SuppressWarnings("rawtypes") // could hold any type, depending on command line
	public static ArrayList<Genotype> genotypeExamples;
//	@SuppressWarnings("rawtypes") // can crossover any type, depending on command line
//	public static Crossover crossoverOperator;
//	public static FunctionOptimizationSet fos;
//	public static RLGlueEnvironment rlGlueEnvironment;
//	@SuppressWarnings("rawtypes") // depends on genotypes
//	public static ArrayList<Metaheuristic> metaheuristics;
	public static ArrayList<ArrayList<String>> fitnessFunctions;
	public static ArrayList<Statistic> aggregationOverrides;
//	public static TaskSpec tso;
//	public static FeatureExtractor rlGlueExtractor;
	public static boolean blueprints = false;
	@SuppressWarnings("rawtypes") // applies to any population type
	public static PerformanceLog performanceLog;
	public static MsPacManControllerInputOutputMediator pacmanInputOutputMediator;
//	public static GhostControllerInputOutputMediator ghostsInputOutputMediator;
	public static MsPacManControllerInputOutputMediator[] coevolutionMediators = null;
	public static MsPacManEnsembleArbitrator ensembleArbitrator = null;
	private static ArrayList<Integer> actualFitnessFunctions;
	public static MsPacManModeSelector pacmanMultitaskScheme = null;
	public static VariableDirectionBlock directionalSafetyFunction;
	public static TWEANNGenotype sharedMultitaskNetwork = null;
	public static TWEANNGenotype sharedPreferenceNetwork = null;
	public static EvalLog evalReport = null;
	public static RandomGenerator weightPerturber = null;
	public static MMNEATLog ghostLocationsOnPowerPillEaten = null;
	public static boolean browseLineage = false;
	public static SubstrateCoordinateMapping substrateMapping = null;
//	@SuppressWarnings("rawtypes")
//	public static HallOfFame hallOfFame;
//	@SuppressWarnings("rawtypes")
//	public static BoardGame boardGame;
//	@SuppressWarnings("rawtypes")
//	public static TwoDimensionalBoardGameViewer boardGameViewer;
	public static SubstrateArchitectureDefinition substrateArchitectureDefinition;
	
	public static MMNEAT mmneat;

	@SuppressWarnings("rawtypes")
	public static ArrayList<String> fitnessPlusMetaheuristics(int pop) {
		@SuppressWarnings("unchecked")
		ArrayList<String> result = (ArrayList<String>) fitnessFunctions.get(pop).clone();
		if(pop == 0) {
			ArrayList<String> meta = new ArrayList<String>();
			//for (Metaheuristic m : metaheuristics) {
			//	meta.add(m.getClass().getSimpleName());
			//}
			result.addAll(actualFitnessFunctions.get(pop), meta);
		}
		return result;
	}

//	private static void setupSaveDirectory() {
//		String saveTo = Parameters.parameters.stringParameter("saveTo");
//		if (Parameters.parameters.booleanParameter("io") && !saveTo.isEmpty()) {
//			String directory = FileUtilities.getSaveDirectory();
//			File dir = new File(directory);
//			if (!dir.exists()) {
//				dir.mkdir();
//			}
//		}
//	}

	@SuppressWarnings("rawtypes") // type of genotypes being crossed could be anything
	private static void setupCrossover() throws NoSuchMethodException {
		// Crossover operator
		//if (Parameters.parameters.booleanParameter("mating")) {
		//	crossoverOperator = (Crossover) ClassCreation.createObject("crossover");
		//}
	}

	private static void setupFunctionOptimization() throws NoSuchMethodException {
		// Function minimization benchmarks, if they are used
		//fos = (FunctionOptimizationSet) ClassCreation.createObject("fos");
		//if (Parameters.parameters.booleanParameter("lengthDependentMutationRate") && fos != null) {
			//Parameters.parameters.setDouble("realMutateRate", 1.0 / fos.getLowerBounds().length);
		//}
	}

	@SuppressWarnings("rawtypes") // Metaheuristic can be applied to any type of population
	private static void setupMetaHeuristics() {
		// Metaheuristics are objectives that are not associated with the
		// domain/task
		System.out.println("Use meta-heuristics?");
		//metaheuristics = new ArrayList<Metaheuristic>();
		if (Parameters.parameters.booleanParameter("antiMaxModeUsage")) {
			System.out.println("Penalize Max Mode Usage");
		//	metaheuristics.add(new AntiMaxModuleUsageFitness());
		}
		if (Parameters.parameters.integerParameter("numModesToPrefer") > 0) {
			int target = Parameters.parameters.integerParameter("numModesToPrefer");
			System.out.println("Prefer even usage of " + target + " modes");
		//	metaheuristics.add(new FavorXModulesFitness(target));
		}
		if (Parameters.parameters.booleanParameter("penalizeLinks")) {
			System.out.println("Penalize Links");
		//	metaheuristics.add(new LinkPenalty());
		}
		if (Parameters.parameters.booleanParameter("maximizeModes")) {
			System.out.println("Maximize Modes");
		//	metaheuristics.add(new MaxModulesFitness());
		}
		if (Parameters.parameters.booleanParameter("penalizeSubstrateLinks")) {
			System.out.println("Penalize Substrate Links");
		//	metaheuristics.add(new SubstrateLinkPenalty());
		}
	}

	private static void setupTWEANNGenotypeDataTracking(boolean coevolution) {
		if (genotype instanceof TWEANNGenotype || 
			genotype instanceof CombinedGenotype) { // || // Assume first member of pair is TWEANNGenotype
			//genotype instanceof HyperNEATCPPNforDL4JGenotype) { // Contains CPPN that is TWEANNGenotype
			if (Parameters.parameters.booleanParameter("io")
					&& Parameters.parameters.booleanParameter("logTWEANNData")) {
				System.out.println("Init TWEANN Log");
				EvolutionaryHistory.initTWEANNLog();
			}
			if (!coevolution) {
				EvolutionaryHistory.initArchetype(0);
			}

//			@SuppressWarnings("rawtypes")
//			long biggestInnovation = genotype instanceof CombinedGenotype ? 
//					((TWEANNGenotype) ((CombinedGenotype) genotype).t1).biggestInnovation() :
//						(genotype instanceof HyperNEATCPPNforDL4JGenotype ?
//						((HyperNEATCPPNforDL4JGenotype) genotype).getCPPN().biggestInnovation()	:
//					((TWEANNGenotype) genotype).biggestInnovation());
//			if (biggestInnovation > EvolutionaryHistory.largestUnusedInnovationNumber) {
//				EvolutionaryHistory.setInnovation(biggestInnovation + 1);
//			}
		}
	}

	public static void prepareCoevolutionArchetypes() {
		for (int i = 0; i < genotypeExamples.size(); i++) {
			String archetypeFile = Parameters.parameters.stringParameter("seedArchetype" + (i + 1));
			if (!EvolutionaryHistory.archetypeFileExists(i) && archetypeFile != null && !archetypeFile.isEmpty()) {
				System.out.println("Using seed archetype: " + archetypeFile);
				EvolutionaryHistory.initArchetype(i, archetypeFile);
			} else {
				System.out.println("New or resumed archetype");
				EvolutionaryHistory.initArchetype(i);
			}
			System.out.println("---------------------------------------------");
		}
	}

	/**
	 * Constructor takes the command line parameters
	 * to initialize the systems parameter values.
	 * @param args directly from command line
	 */
	public MMNEAT(String[] args) {
		Parameters.initializeParameterCollections(args);
	}

	/**
	 * Constructor that takes a parameter file
	 * string to initialize the systems
	 * parameter values.
	 * @param parameterFile
	 */
	public MMNEAT(String parameterFile) {
		Parameters.initializeParameterCollections(parameterFile);
	}

	public static void registerFitnessFunction(String name) {
		registerFitnessFunction(name, 0);
	}

	/**
	 * For plotting purposes. Let simulation know that a given fitness function
	 * will be tracked.
	 * @param name Name of fitness function in plot files
	 * @param pop population index (for coevolution)
	 */
	public static void registerFitnessFunction(String name, int pop) {
		registerFitnessFunction(name, null, true, pop);
	}

	public static void registerFitnessFunction(String name, boolean affectsSelection) {
		registerFitnessFunction(name, affectsSelection, 0);
	}


	/**
	 * Like above, but indicating that the "fitness" function does not affect 
	 * selection means that it is simply an other score that is being tracked
	 * in the logs.
	 * @param name Name of score
	 * @param affectsSelection Whether or not score is actually used for selection
	 * @param pop population index (for coevolution)
	 */
	public static void registerFitnessFunction(String name, boolean affectsSelection, int pop) {
		registerFitnessFunction(name, null, affectsSelection, pop);
	}

	public static void registerFitnessFunction(String name, Statistic override, boolean affectsSelection) {
		registerFitnessFunction(name, override, affectsSelection, 0);
	}

	/**
	 * As above, but it is now possible to indicate how the score is statistically
	 * summarized when noisy evaluations occur. The default is to average scores
	 * across evaluations, but if an overriding statistic is used, then this will
	 * also be mentioned in the log.
	 * @param name Name of score column
	 * @param override Statistic applied across evaluations (null is default/average)
	 * @param affectsSelection whether it affects selection
	 * @param pop population index (for coevolution)
	 */
	public static void registerFitnessFunction(String name, Statistic override, boolean affectsSelection, int pop) {
		if(actualFitnessFunctions == null){
			actualFitnessFunctions = new ArrayList<Integer>();
		}
		while(actualFitnessFunctions.size() <= pop){
			actualFitnessFunctions.add(0);
		}
		if (affectsSelection) {
			int num = actualFitnessFunctions.get(pop) + 1;
			actualFitnessFunctions.set(pop, num);
		}
		// For coevolution.
		// Create enough objective arrays to accomadate each population
		while(fitnessFunctions.size() <= pop) {
			fitnessFunctions.add(new ArrayList<String>());
		}
		fitnessFunctions.get(pop).add(name);
		aggregationOverrides.add(override);
	}

	/**
	 * Load important classes from class parameters.
	 * Other important experiment setup also occurs.
	 * Perhaps the most important classes that always
	 * need to be loaded at the task, the experiment, 
	 * and the ea. These get stored in public static 
	 * variables of this class so they are easily accessible
	 * from all parts of the code.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void loadClasses() {
//		try {
			ActivationFunctions.resetFunctionSet();

			fitnessFunctions = new ArrayList<ArrayList<String>>();
			fitnessFunctions.add(new ArrayList<String>());
			aggregationOverrides = new ArrayList<Statistic>();

			System.out.println("Init Genotype Ids");
			EvolutionaryHistory.initGenotypeIds();

			int multitaskModes = CommonConstants.multitaskModules;
			if (!CommonConstants.hierarchicalMultitask && multitaskModes > 1) {
				modesToTrack = multitaskModes;
			}
			
			if (Parameters.parameters.booleanParameter("scalePillsByGen")
					&& Parameters.parameters.stringParameter("lastSavedDirectory").equals("")
					&& Parameters.parameters.integerParameter("lastSavedGeneration") == 0) {
				System.out.println("Set pre-eaten pills high, since we are scaling pills with generation");
				Parameters.parameters.setDouble("preEatenPillPercentage", 0.999);
			}

			System.out.println("Setup Ms. Pac-Man Task");
			try {
				pacmanInputOutputMediator = (MsPacManControllerInputOutputMediator) ClassCreation.createObject("pacmanInputOutputMediator");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			if (MMNEAT.pacmanInputOutputMediator instanceof VariableDirectionBlockLoadedInputOutputMediator) {
				try {
					directionalSafetyFunction = (VariableDirectionBlock) ClassCreation.createObject("directionalSafetyFunction");
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}

			// Regular Check-Each-Direction networks
			setNNInputParameters(pacmanInputOutputMediator.numIn(), pacmanInputOutputMediator.numOut());

			MsPacManInitialization.setupMsPacmanParameters();
			if (CommonConstants.multitaskModules > 1) {
				try {
					pacmanMultitaskScheme = (MsPacManModeSelector) ClassCreation.createObject("pacmanMultitaskScheme");
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}

	/**
	 * Using HyperNEAT means certain parameters values need to be overridden
	 * @throws NoSuchMethodException 
	 */
	public static void hyperNEATOverrides() throws NoSuchMethodException {
		// Already set to 1 as default value
		//HyperNEATCPPNGenotype.numCPPNOutputsPerLayerPair = 1;

		// Cannot monitor inputs with HyperNEAT because the NetworkTask
		// interface no longer applies
		CommonConstants.monitorInputs = false;
		// Setting the common constant should be sufficient, but keeping the parameter means
		// that hybrID can turn it back on if it needs to
		//Parameters.parameters.setBoolean("monitorInputs", false);

		substrateMapping = (SubstrateCoordinateMapping) ClassCreation.createObject("substrateMapping");

		// This substrate mapping does not require all CPPN inputs
		if(substrateMapping instanceof Bottom1DSubstrateMapping) {
			// Other tasks may also use this mapping in the future.
			//HyperNEATTetrisTask.reduce2DTo1D = true;
		}		
	}

	/**
	 * Resets the classes used in MMNEAT and and sets them to null.
	 */
	public static void clearClasses() {
//		rlGlueEnvironment = null;
//		task = null;
//		fos = null;
//		metaheuristics = null;
		fitnessFunctions = null;
		aggregationOverrides = null;
//		ea = null;
		genotype = null;
		experiment = null;
		performanceLog = null;
		EvolutionaryHistory.archetypes = null;
		Executor.close();
	}

	/**
	 * Initializes and runs the experiment given the loaded classes.
	 */
	public void run() {
		System.out.println("Run:");
		clearClasses();
		loadClasses();

		if (Parameters.parameters.booleanParameter("io")) {
			Parameters.parameters.saveParameters();
		}
		System.out.println("Run");
		experiment.run();
		System.out.println("Experiment finished");
	}

	/**
	 * Processes the task experiment for a given number of runs.
	 * @param runs
	 * @throws FileNotFoundException
	 * @throws NoSuchMethodException
	 */
	public static void process(int runs) throws FileNotFoundException, NoSuchMethodException {
	}

	/**
	 * Processes the hypervolume(HV) for a given number of runs.
	 * @param runs
	 * @throws FileNotFoundException
	 */
	public static void calculateHVs(int runs) throws FileNotFoundException {
	}

	public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException {
		if (args.length == 0) {
			System.out.println("First command line parameter must be one of the following:");
			System.out.println("\tmultiple:n\twhere n is the number of experiments to run in sequence");
			System.out.println("\trunNumber:n\twhere n is the specific experiment number to assign");
			System.out.println("\tprocess:n\twhere n is the number of experiments to do data processing on");
			System.out.println("\tlineage:n\twhere n is the experiment number to do lineage browsing on");
			System.exit(0);
		}
		long start = System.currentTimeMillis();
		// Executor.main(args);
		StringTokenizer st = new StringTokenizer(args[0], ":");
		if (args[0].startsWith("multiple:")) {
			st.nextToken(); // "multiple"
			String value = st.nextToken();

			int runs = Integer.parseInt(value);
			for (int i = 0; i < runs; i++) {
				args[0] = "runNumber:" + i;
				evolutionaryRun(args);
			}
			process(runs);
		} else if (args[0].startsWith("hv:")) {
			st.nextToken(); // "hv"
			String value = st.nextToken();

			int runs = Integer.parseInt(value);
			args[0] = "runNumber:0";
			Parameters.initializeParameterCollections(args); // file should exist
			loadClasses();
			calculateHVs(runs);
		} else if (args[0].startsWith("lineage:")) {
			System.out.println("Lineage browser");
			browseLineage = true;
			st.nextToken(); // "lineage"
			String value = st.nextToken();

			int run = Integer.parseInt(value);
			args[0] = "runNumber:" + run;
			Parameters.initializeParameterCollections(args); // file should exist			
			System.out.println("Params loaded");
			String saveTo = Parameters.parameters.stringParameter("saveTo");
			String loadFrom = Parameters.parameters.stringParameter("loadFrom");
			boolean includeChildren = false;
			if (loadFrom == null || loadFrom.equals("")) {
				loadFrom = saveTo;
				includeChildren = true;
			}
	//		Offspring.fillInLineage(Parameters.parameters.stringParameter("base"), saveTo, run,
	//				Parameters.parameters.stringParameter("log"), loadFrom, includeChildren);
	//		Offspring.browse();
		} else if (args[0].startsWith("process:")) {
			st.nextToken(); // "process"
			String value = st.nextToken();

			int runs = Integer.parseInt(value);
			args[0] = "runNumber:0";
			Parameters.initializeParameterCollections(args); // file should exist
			loadClasses();
			process(runs);
		} else {
			evolutionaryRun(args);
		}
		System.out.println("done: " + (((System.currentTimeMillis() - start) / 1000.0) / 60.0) + " minutes");
	//	if (!(task instanceof FunctionOptimization)) {
	//		System.exit(0);
	//	}
	}

	/**
	 * Runs a single evolution experiment with a given run number.
	 * @param args Command line parameters
	 */
	private static void evolutionaryRun(String[] args) {
		// Commandline
		mmneat = new MMNEAT(args);
		if (CommonConstants.replayPacman) {
			if (CommonConstants.showNetworks) {
				String replayNetwork = Parameters.parameters.stringParameter("replayNetwork");
				FileUtilities.drawTWEANN(replayNetwork);
			}
			//ExecutorFacade ef = new ExecutorFacade(new Executor());
			//ef.replayGame(Parameters.parameters.stringParameter("pacmanSaveFile"), CommonConstants.watch);
			System.exit(1);
		}
		String branchRoot = Parameters.parameters.stringParameter("branchRoot");
		String lastSavedDirectory = Parameters.parameters.stringParameter("lastSavedDirectory");
		if (branchRoot != null && !branchRoot.isEmpty()
				&& (lastSavedDirectory == null || lastSavedDirectory.isEmpty())) {
			// This run is a branch off of another run.
			Parameters rootParameters = new Parameters(new String[0]);
			System.out.println("Loading root parameters from " + branchRoot);
			rootParameters.loadParameters(branchRoot);
			// Copy the needed parameters
			Parameters.parameters.setString("lastSavedDirectory", rootParameters.stringParameter("lastSavedDirectory"));
			Parameters.parameters.setString("archetype", rootParameters.stringParameter("archetype"));
			Parameters.parameters.setLong("lastInnovation", rootParameters.longParameter("lastInnovation"));
			Parameters.parameters.setLong("lastGenotypeId", rootParameters.longParameter("lastGenotypeId"));
		}
		RandomNumbers.reset();

		mmneat.run();

		closeLogs();
	}

	/**
	 * Checks for logs that aren't null, closes them and sets them to null.
	 */
	public static void closeLogs() {
		if (performanceLog != null) {
			performanceLog.close();
		}
		//if (EvolutionaryHistory.tweannLog != null) {
		////	EvolutionaryHistory.tweannLog.close();
		///	EvolutionaryHistory.tweannLog = null;
		//}
		if (EvolutionaryHistory.mutationLog != null) {
			EvolutionaryHistory.mutationLog.close();
			EvolutionaryHistory.mutationLog = null;
		}
		if (EvolutionaryHistory.lineageLog != null) {
			EvolutionaryHistory.lineageLog.close();
			EvolutionaryHistory.lineageLog = null;
		}
	}

	/**
	 * Signals that neural networks will be used, and sets them up
	 * 
	 * @param numIn
	 *            Number of task inputs to network
	 * @param numOut
	 *            Number of task outputs to network (not counting extra modes)
	 */
	public static void setNNInputParameters(int numIn, int numOut) {
		networkInputs = numIn;
		networkOutputs = numOut;
		int multitaskModes = CommonConstants.multitaskModules;
		if (CommonConstants.hierarchicalMultitask) {
			multitaskModes = 1; // Initialize the network like a preference
			// neuron net instead
		}
		networkOutputs *= multitaskModes;
		System.out.println("Networks will have " + networkInputs + " inputs and " + networkOutputs + " outputs.");

		lowerInputBounds = new double[networkInputs];
		upperInputBounds = new double[networkInputs];
		for (int i = 0; i < networkInputs; i++) {
			lowerInputBounds[i] = -1.0;
			upperInputBounds[i] = 1.0;
		}
	}

	/**
	 * Write information to the performance log, if it is being used
	 * 
	 * @param <T> phenotype
	 * @param combined
	 *            Combined population of scores/genotypes
	 * @param generation
	 *            Current generation information is being logged for
	 */
	@SuppressWarnings("unchecked")
	public static <T> void logPerformanceInformation(ArrayList<Score<T>> combined, int generation) {
		if (performanceLog != null)
			performanceLog.log(combined, generation);
	}
}
