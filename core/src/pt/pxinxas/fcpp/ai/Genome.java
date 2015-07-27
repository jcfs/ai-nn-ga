package pt.pxinxas.fcpp.ai;

import java.util.Arrays;

import pt.pxinxas.fcpp.util.RandomUtil;

/**
 * @author jcfs
 */
public class Genome implements Comparable<Genome> {
	private static final Float MUTATION_RATE = 0.20f;

	private Float[] genes;
	private float fitness;

	public Genome(int n) {
		this.genes = new Float[n];
		for (int i = 0; i < genes.length; i++) {
			this.genes[i] = RandomUtil.nextClampedFloat();
		}
	}

	public Genome(float fitness, Float[] genes, boolean mutate) {
		this.fitness = fitness;

		this.genes = new Float[genes.length];

		for (int i = 0; i < genes.length; i++) {
			this.genes[i] = genes[i];
		}

		if (mutate) {
			mutate();
		}
	}
	
	public Genome(float fitness, Float[] genes) {
		this(fitness, genes, false);
	}

	public Genome cloneGenome(boolean mutate) {
		return new Genome(this.fitness, this.genes, mutate);
	}

	/**
	 * Mutates the genome according to the given rate
	 * 
	 * @param rate
	 */
	public void mutate(float rate) {
		for (int i = 0; i < genes.length; i++) {
			if (RandomUtil.nextFloat() < rate) {
				genes[i] *= RandomUtil.nextClampedFloat();
			}
		}
	}

	public void mutate() {
		mutate(MUTATION_RATE);
	}

	/**
	 * @return the cromossomes
	 */
	public Float[] getGenes() {
		return genes;
	}

	/**
	 * @param cromossomes
	 *            the cromossomes to set
	 */
	public void setGenes(Float[] genes) {
		this.genes = genes;
	}

	/**
	 * @return the fitness
	 */
	public float getFitness() {
		return fitness;
	}

	/**
	 * @param fitness
	 *            the fitness to set
	 */
	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Genome o) {
		return this.fitness < o.fitness ? 1 : -1;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genome [cromossomes=" + Arrays.toString(genes) + ", fitness=" + fitness + "]";
	}
}
