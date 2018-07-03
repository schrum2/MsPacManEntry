package pacman.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entrants.pacman.Squillyprice01.wox.serial.Easy;

public class ConvertNetwork {
	
	private static String MMNEATVersion = "edu.southwestern.evolution.genotypes.TWEANNGenotype";
	private static String submissionUserName = "Squillyprice01";
	private static String submissionVersion = "entrants.pacman." + submissionUserName + "." + MMNEATVersion;

	public static void main(String args[]) throws IOException {
		
		String originalPath = args[0];
		String newPath = args[1];
		String fileName = args[2];
		
		//TODO: access is denied?
		File fds = new File(originalPath);
		
		//System.load(originalPath);
		
		//Easy.load(originalPath);
		
		Scanner s = new Scanner(fds);
		
		String text = new String();
		text = s.nextLine();
		s.close();
		text = convert(text);
		
		FileWriter f = new FileWriter(newPath);
	}
	
	public static String convert(String text) {
		text = text.replaceAll(MMNEATVersion, submissionVersion);
		return text;
	}
	
}
