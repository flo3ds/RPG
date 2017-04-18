package monde.athmosphere;

import core.Rand;

public class Atmosphere {

	private String str;
	private short oxygen;

	public Atmosphere() {
		short rand = (short) Rand.entier(0, atmo.values().length);
		this.str = atmo.values()[rand].getName();
		this.oxygen = atmo.values()[rand].getOxygen();
	}

	public String getDescription() {
		return this.str;
	}

	public short getOxygen() {
		return this.oxygen;
	}

	private enum atmo {

		normal("90% oxygene, 10% azote\n", 90), fivtyfivty("50% oxygene, 50% azote\n",
				50), zero("100% " + gaz.values()[Rand.entier(0, gaz.values().length)].toString() + "\n", 0);

		private String str;
		private short oxygen;

		atmo(String str, int oxygen) {
			this.str = str;
			this.oxygen = (short) oxygen;
		}

		public String getName() {
			return this.str;
		}

		public short getOxygen() {
			return this.oxygen;
		}
	}

	private enum gaz {
		azote, helium, metane;
	}
}
