package pt.pxinxas.fcpp.util;

import java.util.Random;

public class IdGenerator {
	private static final IdGenerator instance = new IdGenerator();

	Random random = new Random();
	private int currentIndex = 1000;

	private IdGenerator() {

	}

	public int nextId() {
		synchronized (instance) {
			return currentIndex++;
		}
	}

	/**
	 * @return the instance
	 */
	public static IdGenerator getInstance() {
		return instance;
	}

}
