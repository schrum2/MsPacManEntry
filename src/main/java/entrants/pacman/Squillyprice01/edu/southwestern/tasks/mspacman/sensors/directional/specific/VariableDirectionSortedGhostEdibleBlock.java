/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.directional.specific;

import java.util.ArrayList;
import java.util.Collections;

import entrants.pacman.Squillyprice01.edu.southwestern.parameters.CommonConstants;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.ghosts.DirectionalGhostComparator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.ghosts.GhostComparator;
import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.directional.VariableDirectionBlock;

/**
 * Supports popacman (TODO: test handling of PO conditions)
 * @author Jacob
 */
public class VariableDirectionSortedGhostEdibleBlock extends VariableDirectionBlock {

	private final int order;

	public VariableDirectionSortedGhostEdibleBlock(int order) {
		super(-1);
		this.order = order;
	}

	@Override
	public double wallValue() {
		return 0;
	}

	@Override
	/**
	 * Supports popacman (TODO: test)
	 */
	public double getValue(GameFacade gf) {
		ArrayList<Integer> ghosts = new ArrayList<Integer>(CommonConstants.numActiveGhosts);
		for (int i = 0; i < CommonConstants.numActiveGhosts; i++) {
			if (!gf.ghostInLair(i)) {
				ghosts.add(i);
			}
		}
		if (order >= ghosts.size()) {
			return 0; // Not incoming if in lair
		}
		Collections.sort(ghosts, CommonConstants.checkEachAbsoluteDistanceGhostSort
				? new GhostComparator(gf, true, true) : new DirectionalGhostComparator(gf, true, true, dir));
		return gf.isGhostEdible(ghosts.get(order)) ? 1 : 0;
	}

	@Override
	public String getLabel() {
		return order + " Closest Ghost Edible";
	}
}
