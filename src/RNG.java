import java.util.Random;

@SuppressWarnings("serial")
public class RNG extends Random {

	public int getBound(int range) {

		switch (range) {
		case 1:
			range *= 10;
			range--;
			return range;
		case 2:
			range /= range;
			range *= 100;
			range--;
			return range;
		case 3:
			range /= range;
			range *= 1000;
			range--;
			return range;
		case 4:
			range /= range;
			range *= 10000;
			range--;
			return range;
		case 5:
			range /= range;
			range *= 100000;
			range--;
			return range;
		}

		return range;

	}

	@Override
	public int nextInt() {
		return super.nextInt(getBound(NumField.getMaxRange()));
	}

}
