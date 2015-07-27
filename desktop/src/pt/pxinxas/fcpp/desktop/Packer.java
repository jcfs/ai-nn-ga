package pt.pxinxas.fcpp.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Packer {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;
        settings.combineSubdirectories = true;
        settings.flattenPaths = true;
        TexturePacker.process(settings, "../gfx/match/", "../core/assets/gfx/match/", "splux-match.atlas");
        TexturePacker.process(settings, "../gfx/game/", "../core/assets/gfx/game/", "splux-game.atlas");
    }
}
