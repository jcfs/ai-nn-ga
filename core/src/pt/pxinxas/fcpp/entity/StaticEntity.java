package pt.pxinxas.fcpp.entity;

import java.util.List;

import pt.pxinxas.fcpp.manager.WorldManager;
import pt.pxinxas.fcpp.system.EntitySystem;

public abstract class StaticEntity extends Entity {
	public StaticEntity() {
		this.setType(EntityType.STATIC);
		this.hasFriction = false;
		this.id = -31;
		WorldManager.getInstance().addWorldObject(this);
	}

	@Override
	public boolean collidesWith(Entity entity) {
		return false;
	}

	@Override
	protected List<? extends EntitySystem> getEntitySystems() {
		return null;
	}

	@Override
	public boolean blocksLineOfSight() {
		return true;
	}
}
