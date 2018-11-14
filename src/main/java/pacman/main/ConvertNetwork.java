package pacman.main;
import java.io.File;
import java.io.FileNotFoundException;
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
		// Convert all original MM-NEAT files into MsPacManEntry versions
		File originalDir = new File("OriginalMMNEATChampions");
		File[] files = originalDir.listFiles();
		for(File f : files) {
			String source = f.getPath();
			if(source.endsWith("xml")) { // Only convert xml files
				String target = source.replace("OriginalMMNEATChampions", "src\\main\\resources\\entrants\\pacman\\Squillyprice01");
				convertFromTo(source, target);
			}
		}
		
		// Converts one file
//		if(args.length != 2) {
//			System.out.println("Two command line parameters are required."); 
//			System.out.println("The first is the source xml file.");
//			System.out.println("The second is the target xml file.");
//			System.exit(0);
//		}
//		convertFromTo(args[0],args[1]);		
	}
	
	public static void convertFromTo(String originalPath, String newPath) throws FileNotFoundException {		
		System.out.println("Convert " + originalPath + " to " + newPath);
		File fds = new File(originalPath);
		
		Scanner s = new Scanner(fds);		
		String text = new String();
		while(s.hasNextLine()) {
			text += s.nextLine() + "\n";
		}
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
