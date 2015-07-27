package pt.pxinxas.fcpp.util;

import java.util.Random;

public class RandomUtil {
	
	private static final Random rnd = new Random();
	
	public static float nextFloat() {
		return rnd.nextFloat();
	}
	
	public static float nextClampedFloat() {
		return rnd.nextFloat() - rnd.nextFloat();
	}
	
	public static boolean nextBoolean() {
		return rnd.nextBoolean();
	}
	
	public static int nextInt() {
		return rnd.nextInt();
	}
	
	public static int nextInt(int n) {
		return rnd.nextInt(n);
	}
}
