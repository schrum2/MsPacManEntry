package entrants.pacman.Squillyprice01.edu.southwestern.tasks;

import entrants.pacman.Squillyprice01.edu.southwestern.MMNEAT.MMNEAT;
import entrants.pacman.Squillyprice01.edu.southwestern.evolution.genotypes.Genotype;
import entrants.pacman.Squillyprice01.edu.southwestern.evolution.genotypes.TWEANNGenotype;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.TWEANN;
import entrants.pacman.Squillyprice01.edu.southwestern.networks.hyperneat.HyperNEATTask;
import entrants.pacman.Squillyprice01.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Squillyprice01.edu.southwestern.parameters.Parameters;
import entrants.pacman.Squillyprice01.edu.southwestern.util.datastructures.Pair;
import entrants.pacman.Squillyprice01.edu.southwestern.util.graphics.DrawingPanel;
import entrants.pacman.Squillyprice01.edu.southwestern.util.graphics.Plot;

public class CommonTaskUtil {

	public static final int NETWORK_WINDOW_OFFSET = 0;

	public static Pair<DrawingPanel, DrawingPanel> getDrawingPanels(Genotype<?> genotype){

		// This is not a TWEANNGenotype because it generates a DL4J network,
		// but it contains a TWEANNGenotype that can be used to display the appropriate network.
//		if(genotype instanceof HyperNEATCPPNforDL4JGenotype) {
//			genotype = ((HyperNEATCPPNforDL4JGenotype) genotype).getCPPN();
//		}
		
		DrawingPanel panel = null;
		DrawingPanel cppnPanel = null;

		if (genotype instanceof TWEANNGenotype) {
			if (CommonConstants.showNetworks) {
				panel = new DrawingPanel(TWEANN.NETWORK_VIEW_DIM, TWEANN.NETWORK_VIEW_DIM, "Evolved Network "+genotype.getId());
				panel.setLocation(NETWORK_WINDOW_OFFSET, 0);
				TWEANN network = ((TWEANNGenotype) genotype).getPhenotype();
				//System.out.println("Draw network with " + network.numInputs() + " inputs");
				network.draw(panel);
//				if(genotype instanceof HyperNEATCPPNGenotype) {
//					HyperNEATCPPNGenotype hngt = (HyperNEATCPPNGenotype) genotype;
//					if( Parameters.parameters.booleanParameter("showCPPN")) {
//						cppnPanel = new DrawingPanel(500, 500, "Evolved CPPN");
//						cppnPanel.setLocation(TWEANN.NETWORK_VIEW_DIM + NETWORK_WINDOW_OFFSET, 0);
//						hngt.getCPPN().draw(cppnPanel);
//					}
//					if(Parameters.parameters.booleanParameter("showWeights")){
//						// Weight panels disposed of in HyperNEATUtil
////						HyperNEATTask task = (HyperNEATTask) MMNEAT.task;
////						HyperNEATUtil.drawWeight(hngt.getSubstrateGenotype(task),task,hngt.numModules()); 
//					}
//
//				}
			}
			if (CommonConstants.viewModePreference && TWEANN.preferenceNeuronPanel == null && TWEANN.preferenceNeuron()) {
				TWEANN.preferenceNeuronPanel = new DrawingPanel(Plot.BROWSE_DIM, Plot.BROWSE_DIM, "Preference Neuron Activation");
				TWEANN.preferenceNeuronPanel.setLocation(Plot.BROWSE_DIM + Plot.EDGE, Plot.BROWSE_DIM + Plot.TOP);
			}
			// this does not happen for TorusPredPreyTasks because the
			// "Individual Info" panel is unnecessary, as panels for each
			// evolved agents are already shown with monitorInputs with all
			// of their sensors and information
//			if (CommonConstants.monitorInputs && !(MMNEAT.task instanceof TorusPredPreyTask) && !(MMNEAT.task instanceof Breve2DTask)) {
//				Offspring.fillInputs((TWEANNGenotype) genotype);
//			}
		}
		return new Pair<DrawingPanel, DrawingPanel>(panel, cppnPanel);
	}

}