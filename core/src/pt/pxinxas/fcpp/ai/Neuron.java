package pt.pxinxas.fcpp.ai;

import java.util.Arrays;

import pt.pxinxas.fcpp.util.RandomUtil;

/**
 * @author jcfs
 */
public class Neuron {
	private Float[] weights;
	private boolean isSigmoid = true;

	public Neuron(int numberOfInputs) {
		this.weights = new Float[numberOfInputs];
		for (int i = 0; i < weights.length; i++) {
			this.weights[i] = RandomUtil.nextClampedFloat();
		}
	}

	public Neuron(Neuron n) {
		this.weights = new Float[n.weights.length];
		for (int i = 0; i < weights.length; i++) {
			this.weights[i] = n.weights[i];
		}
	}

	public float evaluate(Float[] inputs) {
		float net = 0;

		if (inputs.length != weights.length) {
			// OMG
		}

		for (int i = 0; i < inputs.length; i++) {
			net += inputs[i] * weights[i];
		}

		net += weights[weights.length - 1] * -1f;// bias

		if (isSigmoid) {
			return sigmoid(net);
		} else {
			return net;
		}

	}

	private static float sigmoid(float x) {
		return (float) (1 / (1 + Math.exp(-x)));
	}

	/**
	 * @return the weights
	 */
	public Float[] getWeights() {
		return weights;
	}

	/**
	 * @param weights
	 *            the weights to set
	 */
	public void setWeights(Float[] weights) {
		this.weights = weights;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Neuron [weights=" + Arrays.toString(weights) + "]";
	}

	/**
	 * @return the isSigmoid
	 */
	public boolean isSigmoid() {
		return isSigmoid;
	}

	/**
	 * @param isSigmoid
	 *            the isSigmoid to set
	 */
	public void setSigmoid(boolean isSigmoid) {
		this.isSigmoid = isSigmoid;
	}
}
