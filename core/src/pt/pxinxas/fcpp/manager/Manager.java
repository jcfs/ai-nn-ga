package pt.pxinxas.fcpp.manager;

/**
 * Manager abstract class
 * 
 * @author jcfs
 */
public abstract class Manager {

	public abstract void init();

	public abstract void update(float delta);

	public abstract void render(float delta);

	public abstract void destroy();
}
