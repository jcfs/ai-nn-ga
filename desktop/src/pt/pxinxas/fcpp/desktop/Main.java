package pt.pxinxas.fcpp.desktop;

import pt.pxinxas.fcpp.game.ClientGame;
import pt.pxinxas.fcpp.game.ClientGameStaticContext;
import pt.pxinxas.fcpp.game.GameContextProvider;
import pt.pxinxas.fcpp.manager.RunningMode;
import pt.pxinxas.fcpp.manager.RunningModeType;
import pt.pxinxas.fcpp.util.Constants;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.SCREEN_WIDTH;
        config.height = Constants.SCREEN_HEIGHT;
        config.resizable = false;
        config.title = "Main - dev version";
        config.allowSoftwareMode = true;
        config.vSyncEnabled = false;
        config.foregroundFPS = 0;
        RunningMode.getInstance().setType(RunningModeType.CLIENT);
        GameContextProvider.getInstance().setContext(ClientGameStaticContext.getInstance());
        new LwjglApplication(new ClientGame(), config);
    }
}
