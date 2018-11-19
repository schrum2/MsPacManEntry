package pacman.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.EnumMap;

import entrants.pacman.Squillyprice01.MyPacMan;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.examples.StarterGhost.POGhost;
import pacman.game.Constants.GHOST;
import pacman.game.util.Stats;

public class AllPostEval {
	public static void main(String[] args) throws FileNotFoundException {
		CustomExecutor executor = new CustomExecutor.Builder()
				//.setVisual(true)
				.setTickLimit(4000) // 4000 is the default value: Used in competition?
				//.setTickLimit(200000) // So high it will never be reached
				.build();		

		StatsRun.doStats = true;
		
		PrintStream ps = new PrintStream(new File("ChampionPostEvals.txt"));
		ps.printf("%10s, %15s, %4s, %10s, %10s, %10s, %10s, %10s, %10s, %14s, %10s\n", "Ghosts", "Modules", "Run", "AVG", "StdDev", "Min", "Max", "StdErr", "Sum", "SumSq", "SD");
		System.out.printf("%10s, %15s, %4s, %10s, %10s, %10s, %10s, %10s, %10s, %14s, %10s\n", "Ghosts", "Modules", "Run", "AVG", "StdDev", "Min", "Max", "StdErr", "Sum", "SumSq", "SD");
		
		boolean[] ghostsArePO = new boolean[] {true, false};
		for(boolean poGhosts : ghostsArePO) {
			for(MyPacMan.MODULE_TYPE modules: MyPacMan.MODULE_TYPE.values()) {        	
				for(int run = 0; run < 20; run++) {
					EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);
					
					MyPacMan badboy = new MyPacMan(poGhosts, modules, run);

					controllers.put(GHOST.INKY, new POGhost(GHOST.INKY));
					controllers.put(GHOST.BLINKY, new POGhost(GHOST.BLINKY));
					controllers.put(GHOST.PINKY, new POGhost(GHOST.PINKY));
					controllers.put(GHOST.SUE, new POGhost(GHOST.SUE));

					Stats[] stats = executor.runExperiment(badboy, new MASController(controllers), 100, "post evals");
					System.out.printf("%10s, %15s, %4d, %10.4f, %10.4f, %10.1f, %10.1f, %10.1f, %10.1f, %14.1f, %10.4f\n", (poGhosts ? "POGhosts" : "COGhosts"), modules.name(), run, stats[0].getAverage(), stats[0].getStandardDeviation(), stats[0].getMin(), stats[0].getMax(), stats[0].getStandardError(), stats[0].getSum(), stats[0].getSumsq(), stats[0].getSd());
					ps.printf("%10s, %15s, %4d, %10.4f, %10.4f, %10.1f, %10.1f, %10.1f, %10.1f, %14.1f, %10.4f\n", (poGhosts ? "POGhosts" : "COGhosts"), modules.name(), run, stats[0].getAverage(), stats[0].getStandardDeviation(), stats[0].getMin(), stats[0].getMax(), stats[0].getStandardError(), stats[0].getSum(), stats[0].getSumsq(), stats[0].getSd());
				}
			}
		}
		
		ps.close();
	}
}
