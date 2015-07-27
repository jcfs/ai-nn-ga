package pt.pxinxas.fcpp.camera;

import pt.pxinxas.fcpp.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class SpectatorCamera implements GameCamera {

    private final OrthographicCamera camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    private final Vector2 position = new Vector2(496, 2768);

    @Override
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Keys.D)) {
            this.position.x += 2 * delta;
        }

        if (Gdx.input.isKeyPressed(Keys.A)) {
            this.position.x -= 2 * delta;
        }

        if (Gdx.input.isKeyPressed(Keys.S)) {
            this.position.y -= 2 * delta;
        }

        if (Gdx.input.isKeyPressed(Keys.W)) {
            this.position.y += 2 * delta;
        }

        this.camera.position.set(this.position.x, this.position.y, 0);
        this.camera.update(true);
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

}
