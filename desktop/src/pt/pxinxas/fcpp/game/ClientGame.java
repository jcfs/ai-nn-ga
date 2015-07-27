package pt.pxinxas.fcpp.game;

import pt.pxinxas.fcpp.screen.LoadingScreen;

import com.badlogic.gdx.Game;

public class ClientGame extends Game {

	@Override
	public void create() {
		this.setScreen(new LoadingScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
