package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.sensors;

import java.util.ArrayList;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.tasks.mspacman.agentcontroller.pacman.actions.MsPacManAction;

/**
 *
 * @author Jacob Schrum
 */
public class ActionBlockLoadedInputOutputMediator extends BlockLoadedInputOutputMediator {

	public ArrayList<MsPacManAction> actions;

	public ActionBlockLoadedInputOutputMediator() {
		super();
		actions = new ArrayList<MsPacManAction>();
	}

	@Override
	public String[] outputLabels() {
		String[] labels = new String[actions.size()];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = actions.get(i).getClass().getSimpleName();
		}
		return labels;
	}

	/**
	 * One output corresponding to the selection of each action
	 *
	 * @return
	 */
	@Override
	public int numOut() {
		return actions.size();
	}
}
