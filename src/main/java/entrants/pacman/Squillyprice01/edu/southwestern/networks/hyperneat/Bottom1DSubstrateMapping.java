package entrants.pacman.Squillyprice01.edu.southwestern.networks.hyperneat;

import entrants.pacman.Squillyprice01.edu.southwestern.util.CartesianGeometricUtilities;
import entrants.pacman.Squillyprice01.edu.southwestern.util.util2D.ILocated2D;
import entrants.pacman.Squillyprice01.edu.southwestern.util.util2D.Tuple2D;

/**
 * maps substrate to 1D in Y-Direction with bottom coordinate mapping
 * @author Lauren Gillespie
 *
 */
public class Bottom1DSubstrateMapping implements SubstrateCoordinateMapping {
	@Override
	public ILocated2D transformCoordinates(Tuple2D toScale, int width, int height) {
		return CartesianGeometricUtilities.Bottom1DCenterAndScale(toScale, width, height);
	}

}
