package pt.pxinxas.fcpp.manager;

import pt.pxinxas.fcpp.camera.GameCamera;
import pt.pxinxas.fcpp.camera.SpectatorCamera;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * @author JSalavisa
 */
public class CameraManager extends Manager {

    private static final CameraManager instance = new CameraManager();
    private GameCamera camera;

    /**
     * Singleton constructor
     */
    private CameraManager() {
    }

    @Override
    public void init() {
        
    }

    @Override
    public void update(float millisecondsDelta) {
        if (camera == null) {
            camera = new SpectatorCamera();
        }

        this.camera.update(millisecondsDelta);
        this.camera.getCamera().update(true);
    }
    
	@Override
	public void render(float delta) {
		
		
	}

    @Override
    public void destroy() {
        this.camera = null;
    }

    public OrthographicCamera getActiveCamera() {
        if (camera == null) {
            camera = new SpectatorCamera();
        }
        return camera.getCamera();
    }

    public void setActiveCamera(GameCamera camera) {
        this.camera = camera;
    }

    /**
     * @return the instance
     */
    public static CameraManager getInstance() {
        return instance;
    }


}
