package pt.pxinxas.fcpp.manager;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MatchAssetManager extends AssetManager {

    private static final String SPLUX_PACK_ATLAS = "gfx/match/splux-match.atlas";

    private static final MatchAssetManager instance = new MatchAssetManager();

    private final Map<String, TextureRegion> assetMap = new HashMap<String, TextureRegion>();

    public void init() {

        this.load(SPLUX_PACK_ATLAS, TextureAtlas.class);
    }

    @Override
    public void dispose() {
        super.dispose();
        assetMap.clear();
    }

    public TextureRegion getRegion(String region) {
        if (assetMap.get(region) == null) {
            AtlasRegion findRegion = get(SPLUX_PACK_ATLAS, TextureAtlas.class).findRegion(region);
            TextureRegion textureRegion = new TextureRegion(findRegion, 0, 0, findRegion.getRegionWidth(), findRegion.getRegionHeight());

            assetMap.put(region, textureRegion);
        } else {
            return assetMap.get(region);
        }

        return assetMap.get(region);
    }

    /**
     * @return the instance
     */
    public static MatchAssetManager getInstance() {
        return instance;
    }
}
