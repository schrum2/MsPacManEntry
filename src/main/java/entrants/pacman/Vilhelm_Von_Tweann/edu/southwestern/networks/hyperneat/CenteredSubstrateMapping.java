package entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.networks.hyperneat;

import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.util.CartesianGeometricUtilities;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.util.util2D.ILocated2D;
import entrants.pacman.Vilhelm_Von_Tweann.edu.southwestern.util.util2D.Tuple2D;

public class CenteredSubstrateMapping implements SubstrateCoordinateMapping {

	@Override
	public ILocated2D transformCoordinates(Tuple2D toScale, int width, int height) {
		return CartesianGeometricUtilities.centerAndScale(toScale, width, height);
	}

}
