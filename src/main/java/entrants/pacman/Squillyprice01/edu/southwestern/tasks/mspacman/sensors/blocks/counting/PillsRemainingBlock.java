package entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.sensors.blocks.counting;

import entrants.pacman.Squillyprice01.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Squillyprice01.edu.southwestern.util.MiscUtil;

/**
 * Support PO pacman
 * @author Jacob Schrum
 */
public class PillsRemainingBlock extends TargetPortionRemainingBlock {

	public PillsRemainingBlock(boolean portion, boolean inverse) {
		super(portion, inverse);
	}

	@Override
	public int getTargetMax(GameFacade gf) {
		return gf.getNumberOfPills();
	}

	@Override
	public int getTargetCurrent(GameFacade gf) {
		return gf.getActivePillsIndices().length;
	}

	@Override
	public String getTargetType() {
		return "Pill";
	}
}
