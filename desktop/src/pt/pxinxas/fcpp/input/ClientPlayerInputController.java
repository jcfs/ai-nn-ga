package pt.pxinxas.fcpp.input;

import java.util.ArrayList;
import java.util.List;

import pt.pxinxas.fcpp.entity.ControlableEntity;
import pt.pxinxas.fcpp.entity.Direction;

/**
 * Input Controller for the level screen
 *
 * @author JSalavisa
 *
 */
public class ClientPlayerInputController implements InputController {

    @Override
    public void handleInput(ControlableEntity entity, Input input) {
        List<Direction> directions = new ArrayList<Direction>();
        if (input.isLeftKeyDown()) {
            directions.add(Direction.LEFT);
        }

        if (input.isRightKeyDown()) {
            directions.add(Direction.RIGHT);
        }

        if (input.isUpKeyDown()) {
            directions.add(Direction.UP);
        }

        if (input.isDownKeyDown()) {
            directions.add(Direction.DOWN);
        }

        entity.setRotation((float) input.getAngle());

        entity.setDirections(directions);

    }

}
