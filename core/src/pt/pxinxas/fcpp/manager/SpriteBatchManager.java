package pt.pxinxas.fcpp.manager;

import com.badlogic.gdx.graphics.Color;

public class SpriteBatchManager {

    private static final SpriteBatchManager instance = new SpriteBatchManager();
    private final CustomSpriteBatch batch;

    SpriteBatchManager() {
        batch = new CustomSpriteBatch(1000, null);

    }

    public void setBatchAlpha(float alpha) {
        Color color = batch.getColor();
        color.a = alpha;
        batch.setColor(color);
    }

    public static SpriteBatchManager getInstance() {
        return instance;
    }

    /**
     * @return the batch
     */
    public CustomSpriteBatch getBatch() {
        return batch;
    }
}
