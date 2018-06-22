package entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.veryclose;

import entrants.pacman.Spooky.edu.southwestern.tasks.mspacman.facades.GameFacade;
import entrants.pacman.Spooky.edu.southwestern.util.MiscUtil;

/**
 * should support popacman (TODO: test)
 * @author Jacob Schrum
 */
public class IsCloseToPowerPill extends IsCloseBlock {

	private static final int CLOSE_POWER_PILL_DISTANCE = 10;

	public IsCloseToPowerPill() {
		super(CLOSE_POWER_PILL_DISTANCE);
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getActivePowerPillsIndices();
	}

	@Override
	public String getType() {
		return "Power Pill";
	}
}
