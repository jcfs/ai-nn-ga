package pt.pxinxas.fcpp.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import pt.pxinxas.fcpp.entity.Entity;
import pt.pxinxas.fcpp.entity.WorldObject;
import pt.pxinxas.fcpp.util.IdGenerator;
import pt.pxinxas.fcpp.util.SpatialHashMap;

public class WorldManager extends Manager {
	private static final WorldManager instance = new WorldManager();

	private final Set<WorldObject> renderOnlyObjects = new HashSet<WorldObject>();
	private final Set<WorldObject> worldObjects = new HashSet<WorldObject>();
	private final Set<WorldObject> addOnNextUpdate = new HashSet<WorldObject>();
	private final Map<Integer, WorldObject> worldObjectsMap = new ConcurrentHashMap<Integer, WorldObject>();
	private final Map<Class<? extends WorldObject>, Set<WorldObject>> worldObjectByClass = new ConcurrentHashMap<Class<? extends WorldObject>, Set<WorldObject>>();
	private final SpatialHashMap spatialMap = new SpatialHashMap(64);

	private WorldManager() {
		// private constructor
	}

	@Override
	public void init() {

	}

	/**
	 * Renders all the objects that should be rendered
	 */
	public void render(float delta) {
		List<WorldObject> renderList = new ArrayList<WorldObject>();
		renderList.addAll(this.worldObjects);
		renderList.addAll(this.renderOnlyObjects);
		Collections.sort(renderList);
		for (WorldObject object : renderList) {
			if (object.shouldRender()) {
				object.render();
			}
		}
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see pt.pxinxas.fcpp.manager.Manager#update(float)
	 */
	@Override
	public void update(float delta) {
		// Add all the objects that should be added on this update
		if (addOnNextUpdate.size() > 0) {
			this.worldObjects.addAll(this.addOnNextUpdate);
			this.addOnNextUpdate.clear();
		}

		// Build spatial hashmap with all the world objects and save previous
		// state
		spatialMap.clear();
		for (WorldObject worldObject : worldObjects) {
			worldObject.savePreviousState();

			if (worldObject instanceof Entity) {
				Entity entity = (Entity) worldObject;
				this.spatialMap.add(entity);
			}
		}

		for (Iterator<WorldObject> iterator = worldObjects.iterator(); iterator.hasNext();) {
			WorldObject worldObject = iterator.next();

			// If we should not update this object, we update it one last time
			// and then remove it
			if (!worldObject.shouldUpdate(delta)) {
				worldObject.update(delta);
				this.removeWorldObject(worldObject);
				iterator.remove();
			} else {
				worldObject.update(delta);
			}
		}

	}

	@Override
	public void destroy() {
		this.worldObjects.clear();
		this.renderOnlyObjects.clear();
		this.addOnNextUpdate.clear();
		this.worldObjectByClass.clear();
		this.worldObjectsMap.clear();
	}

	/**
	 * Adds a new world object to the data structure
	 *
	 * @param object
	 */
	public void addWorldObject(WorldObject object) {
		if (object.getId() == null) {
			do {
				object.setId(IdGenerator.getInstance().nextId());
			} while (worldObjectsMap.get(object.getId()) != null);
		}
		this.addOnNextUpdate.add(object);

		Set<WorldObject> list = this.worldObjectByClass.get(object.getClass());
		if (list == null) {
			list = new HashSet<WorldObject>();
		}
		list.add(object);
		this.worldObjectByClass.put(object.getClass(), list);
		this.worldObjectsMap.put(object.getId(), object);
	}

	public void addRenderOnlyObject(WorldObject object) {
		this.renderOnlyObjects.add(object);
	}

	@SuppressWarnings("unchecked")
	public <T extends WorldObject> List<T> getWorldObjects(Class<T> type) {
		List<T> result = new ArrayList<T>();
		for (Class<? extends WorldObject> clazz : worldObjectByClass.keySet()) {
			if (type.isAssignableFrom(clazz)) {
				result.addAll((Collection<? extends T>) worldObjectByClass.get(clazz));
			}
		}
		return result;
	}

	public Set<WorldObject> getWorldObjects() {
		return this.worldObjects;
	}

	public Map<Integer, WorldObject> getWorldObjectsMap() {
		return worldObjectsMap;
	}

	public static WorldManager getInstance() {
		return instance;
	}

	public SpatialHashMap getSpatialMap() {
		return spatialMap;
	}

	private void removeWorldObject(WorldObject object) {
		this.worldObjectsMap.remove(object.getId());
		this.worldObjectByClass.get(object.getClass()).remove(object);
	}

}
