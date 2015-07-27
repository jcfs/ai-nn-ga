package pt.pxinxas.fcpp.input;

import pt.pxinxas.fcpp.entity.ControlableEntity;

/**
 * Interface for the input controllers. All classes that handle input, must
 * implement this interface.
 *
 * @author JSalavisa
 */
public interface InputController {

    public void handleInput(ControlableEntity actorEntity, Input input);

}
