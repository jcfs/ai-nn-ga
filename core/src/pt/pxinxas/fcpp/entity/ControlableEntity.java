package pt.pxinxas.fcpp.entity;

import pt.pxinxas.fcpp.input.Input;
import pt.pxinxas.fcpp.input.InputController;

public abstract class ControlableEntity extends ActorEntity {
    protected final InputController inputController;
    private ControlableEntity controledEntity;

    public ControlableEntity() {
        this.inputController = getInputController();
    }

    /**
     * @return
     */
    protected abstract InputController getInputController();

    protected abstract Input getCurrentInput();

    @Override
    public void update(float delta) {
        Input currentInput = getCurrentInput();
        if (this.inputController != null && currentInput != null) {
            inputController.handleInput(findControllee(), currentInput);
        }
        super.update(delta);
    }

    /**
     * @return
     */
    private ControlableEntity findControllee() {
        ControlableEntity controlledEntity;
        if (this.controledEntity != null) {
            if (this.controledEntity.getStatus() == Status.INACTIVE) {
                this.controledEntity = null;
                controlledEntity = this;
            } else {
                controlledEntity = this.controledEntity;
            }
        } else {
            controlledEntity = this;
        }
        return controlledEntity;
    }

    public ControlableEntity getControlableEntity() {
        return this.controledEntity;
    }

    public void setControlableEntity(ControlableEntity controlableEntity) {
        this.controledEntity = controlableEntity;
    }

}
