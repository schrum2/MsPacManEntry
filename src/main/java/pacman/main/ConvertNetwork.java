package pacman.main;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Takes an xml file generated by MM-NEAT to represent an evolved
 * Ms. Pac-Man controller, and changes the package names in the file
 * so that they fit the package conventions required for the Ms. Pac-Man
 * vs. Ghost Team competition.
 * 
 * @author Will Price and Jacob Schrum
 */
public class ConvertNetwork {
	
	private static String MMNEATVersion = "edu.southwestern.evolution.genotypes.TWEANNGenotype";
	private static String submissionUserName = "Squillyprice01";
	private static String submissionVersion = "entrants.pacman." + submissionUserName + "." + MMNEATVersion;

	public static void main(String args[]) throws IOException {
		
		if(args.length != 2) {
			System.out.println("Two command line parameters are required."); 
			System.out.println("The first is the source xml file.");
			System.out.println("The second is the target xml file.");
			System.exit(0);
		}
		
		String originalPath = args[0]; // Original xml file
		String newPath = args[1]; // New xml file
		
		File fds = new File(originalPath);
		
		Scanner s = new Scanner(fds);		
		String text = new String();
		text = s.nextLine();
		s.close();
		text = convert(text);
		
		PrintStream ps = new PrintStream(new File(newPath));
		ps.print(text);
		ps.close();
	}
	
	public static String convert(String text) {
		text = text.replaceAll(MMNEATVersion, submissionVersion);
		return text;
	}
	
}