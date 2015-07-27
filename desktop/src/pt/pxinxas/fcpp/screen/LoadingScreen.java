package pt.pxinxas.fcpp.screen;

import pt.pxinxas.fcpp.manager.MatchAssetManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {

	@Override
	public void show() {
		Gdx.input.setInputProcessor(null);

		MatchAssetManager.getInstance().init();

	}

	@Override
	public void render(float delta) {
		MatchAssetManager.getInstance().update();
		float progress = MatchAssetManager.getInstance().getProgress();

		if (progress == 1) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new AiScreen());
		}

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		// spriteBatch.dispose();
	}

	@Override
	public void dispose() {
		// spriteBatch.dispose();
	}

}
