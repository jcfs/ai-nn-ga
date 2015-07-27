package pt.pxinxas.fcpp.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface GameCamera {

	OrthographicCamera getCamera();
	
	void update(float delta);
}
