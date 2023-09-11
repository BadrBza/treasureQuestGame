package treasurequest.domains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnumResultTests {

	@Test
	public void testGetTitle() {
		EnumProfile gain = EnumProfile.GAIN;
		EnumProfile loss = EnumProfile.LOSS;
		EnumProfile duration = EnumProfile.DURATION;
		EnumProfile lumberjack = EnumProfile.LUMBERJACK;
		EnumProfile miner = EnumProfile.MINER;
		EnumProfile farmer = EnumProfile.FARMER;
		EnumProfile tourist = EnumProfile.TOURIST;
		EnumProfile none = EnumProfile.NONE;

		Assertions.assertEquals("Gains", gain.getTitle());
		Assertions.assertEquals("Pertes", loss.getTitle());
		Assertions.assertEquals("Bilan", duration.getTitle());
		Assertions.assertEquals("Bucheron", lumberjack.getTitle());
		Assertions.assertEquals("Mineur", miner.getTitle());
		Assertions.assertEquals("Fermier", farmer.getTitle());
		Assertions.assertEquals("Touriste", tourist.getTitle());
		Assertions.assertEquals("Inconnu", none.getTitle());
	}

}
