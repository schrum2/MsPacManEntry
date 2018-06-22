package entrants.pacman.Spooky.edu.southwestern.networks.hyperneat;

import entrants.pacman.Spooky.edu.southwestern.util.util2D.ILocated2D;
import entrants.pacman.Spooky.edu.southwestern.util.util2D.Tuple2D;

public interface SubstrateCoordinateMapping {
	public ILocated2D transformCoordinates(Tuple2D toScale, int width, int height);
}
