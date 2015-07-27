package pt.pxinxas.fcpp.system;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import pt.pxinxas.fcpp.entity.Entity;
import pt.pxinxas.fcpp.entity.Status;

public class SystemManager {

	private List<EntitySystem> systems = new CopyOnWriteArrayList<EntitySystem>();

	/**
	 * @param entity
	 * @param delta
	 */
	public void update(Entity entity, float delta) {
		if (entity.getStatus() == Status.ACTIVE) {
			for (EntitySystem system : systems) {
				system.update(entity, delta);
			}
		}
	}

	public void add(EntitySystem newSystem) {
		systems.add(newSystem);
	}

}
