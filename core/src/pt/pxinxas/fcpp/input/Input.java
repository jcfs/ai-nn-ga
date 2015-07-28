package pt.pxinxas.fcpp.input;

import java.io.Serializable;
import java.util.List;

import com.badlogic.gdx.Input.Buttons;

public class Input implements Serializable {
	private static final long serialVersionUID = 4618180701316379072L;

	private boolean leftKeyDown;
	private boolean rightKeyDown;
	private boolean upKeyDown;
	private boolean downKeyDown;
	private boolean jumpKeyDown;

	private double angle;

	private boolean isLeftButtonPressed;
	private boolean isRightButtonPressed;

	public Input() {

	}

	public Input(com.badlogic.gdx.Input input) {
		if (input != null) {
			leftKeyDown = input.isKeyPressed(com.badlogic.gdx.Input.Keys.A);
			rightKeyDown = input.isKeyPressed(com.badlogic.gdx.Input.Keys.D);
			upKeyDown = input.isKeyPressed(com.badlogic.gdx.Input.Keys.W);
			downKeyDown = input.isKeyPressed(com.badlogic.gdx.Input.Keys.S);
			jumpKeyDown = input.isKeyPressed(com.badlogic.gdx.Input.Keys.W);

			if (input.isButtonPressed(Buttons.LEFT)) {
				isLeftButtonPressed = true;
			}

			if (input.isButtonPressed(Buttons.RIGHT)) {
				isRightButtonPressed = true;
			}

			int x = -input.getX() + (pt.pxinxas.fcpp.util.Constants.SCREEN_WIDTH / 2);
			int y = -input.getY() + (pt.pxinxas.fcpp.util.Constants.SCREEN_HEIGHT / 2);

			this.angle = Math.toDegrees(Math.atan2(x, y));

		}
	}

	public Input(List<Float> inputs) {
		float up = inputs.get(3);
		float down = inputs.get(2);
		float left = inputs.get(1);
		float right = inputs.get(0);

		if (up > 0.5f) {
			upKeyDown = true;
		} else {
			upKeyDown = false;
		}

		if (down > 0.5f) {
			downKeyDown = true;
		} else {
			downKeyDown = false;
		}
		if (left > 0.5f) {
			leftKeyDown = true;
		} else {
			leftKeyDown = false;
		}
		if (right > 0.5f) {
			rightKeyDown = true;
		} else {
			rightKeyDown = false;
		}
		
		this.angle = scale(inputs.get(4), 0, 1, 0, 360);

	}
	
	public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }

	public boolean isLeftKeyDown() {
		return leftKeyDown;
	}

	public void setLeftKeyDown(boolean leftKeyDown) {
		this.leftKeyDown = leftKeyDown;
	}

	public boolean isRightKeyDown() {
		return rightKeyDown;
	}

	public void setRightKeyDown(boolean rightKeyDown) {
		this.rightKeyDown = rightKeyDown;
	}

	public boolean isUpKeyDown() {
		return upKeyDown;
	}

	public void setUpKeyDown(boolean upKeyDown) {
		this.upKeyDown = upKeyDown;
	}

	public boolean isDownKeyDown() {
		return downKeyDown;
	}

	public void setDownKeyDown(boolean downKeyDown) {
		this.downKeyDown = downKeyDown;
	}

	public boolean isJumpKeyDown() {
		return jumpKeyDown;
	}

	public void setJumpKeyDown(boolean jumpKeyDown) {
		this.jumpKeyDown = jumpKeyDown;
	}

	public boolean isLeftButtonPressed() {
		return isLeftButtonPressed;
	}

	public void setLeftButtonPressed(boolean isLeftButtonPressed) {
		this.isLeftButtonPressed = isLeftButtonPressed;
	}

	public double getAngle() {
		return angle;
	}

	public boolean isRightButtonPressed() {
		return isRightButtonPressed;
	}

	public void setRightButtonPressed(boolean isRightButtonPressed) {
		this.isRightButtonPressed = isRightButtonPressed;
	}

	@Override
	public String toString() {
		return "Input [leftKeyDown=" + leftKeyDown + ", rightKeyDown=" + rightKeyDown + ", upKeyDown=" + upKeyDown + ", downKeyDown=" + downKeyDown
				+ ", jumpKeyDown=" + jumpKeyDown + ", isLeftButtonPressed=" + isLeftButtonPressed + "]";
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (downKeyDown ? 1231 : 1237);
		result = prime * result + (isLeftButtonPressed ? 1231 : 1237);
		result = prime * result + (isRightButtonPressed ? 1231 : 1237);
		result = prime * result + (jumpKeyDown ? 1231 : 1237);
		result = prime * result + (leftKeyDown ? 1231 : 1237);
		result = prime * result + (rightKeyDown ? 1231 : 1237);
		result = prime * result + (upKeyDown ? 1231 : 1237);
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Input other = (Input) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		if (downKeyDown != other.downKeyDown)
			return false;
		if (isLeftButtonPressed != other.isLeftButtonPressed)
			return false;
		if (isRightButtonPressed != other.isRightButtonPressed)
			return false;
		if (jumpKeyDown != other.jumpKeyDown)
			return false;
		if (leftKeyDown != other.leftKeyDown)
			return false;
		if (rightKeyDown != other.rightKeyDown)
			return false;
		if (upKeyDown != other.upKeyDown)
			return false;
		return true;
	}

	/**
	 * @param angle
	 *            the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
}
