package pt.pxinxas.fcpp.entity;

import pt.pxinxas.fcpp.manager.CustomSpriteBatch;
import pt.pxinxas.fcpp.manager.MatchAssetManager;
import pt.pxinxas.fcpp.manager.SpriteBatchManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Animated entity. Entity that has an animated texture and renders it
 * accordingly
 *
 * @author JSalavisa
 */
public abstract class AnimatedEntity extends Entity {
    private Animation animation;
    private TextureRegion[] frames;
    private float stateTime;

    protected AnimatedEntity() {
        loadTextures();
    }

    protected AnimatedEntity(String textureFile) {
        loadTexture(new TextureRegion(new Texture(textureFile)));
    }

    /**
     * @return whether this entity should be animated
     */
    public abstract boolean shouldAnimate();

    /**
     * @return the animation texture column count
     */
    public abstract int getAnimationColumnCount();

    /**
     * @return the animation texture row count
     */
    public abstract int getAnimationRowCount();

    /**
     * @return the animation speed
     */
    public abstract float getAnimationSpeed();

    protected void loadTextures() {
        if (getTextureFile() != null) {
            loadTexture(MatchAssetManager.getInstance().getRegion(getTextureFile()));
            this.getCurrentState().width = frames[0].getRegionWidth();
            this.getCurrentState().height = frames[0].getRegionHeight();
        }
    }

    protected void loadTexture(TextureRegion textureRegion) {
        loadFrames(textureRegion);
        animation = new Animation(getAnimationSpeed(), frames);
        this.stateTime = 0f;
    }

    @Override
    public void render() {
        if (animation == null) {
            loadTextures();
        } else {
            if (shouldRender()) {
                CustomSpriteBatch batch = SpriteBatchManager.getInstance().getBatch();

                TextureRegion currentFrame;
                if (shouldAnimate()) {
                    stateTime += Gdx.graphics.getDeltaTime();
                    currentFrame = animation.getKeyFrame(stateTime, true);
                } else {
                    currentFrame = animation.getKeyFrame(0, true);
                }

                double xPosition = (this.getCurrentState().position.x - (currentFrame.getRegionWidth() / 2));
                double yPosition = (this.getCurrentState().position.y - (currentFrame.getRegionHeight() / 2));

                batch.draw(
                    currentFrame,
                    (float) xPosition,
                    (float) yPosition,
                    currentFrame.getRegionWidth() / 2,
                    currentFrame.getRegionHeight() / 2,
                    currentFrame.getRegionWidth(),
                    currentFrame.getRegionHeight(),
                    this.alpha,
                    (float) this.getCurrentState().rotation);
            }
        }
    }

    /**
     * {@inheritDoc}
     * @see pt.pxinxas.fcpp.entity.Entity#shouldRender()
     */
    @Override
    public boolean shouldRender() {
        return true;
    }

    private void loadFrames(TextureRegion texture) {
        int tileWidth = texture.getRegionWidth() / getAnimationColumnCount();
        int tileHeight = texture.getRegionHeight() / getAnimationRowCount();

        TextureRegion[][] tmp = texture.split(tileWidth, tileHeight);
        frames = new TextureRegion[getAnimationColumnCount() * getAnimationRowCount()];

        for (int i = 0, index = 0; i < getAnimationRowCount(); i++) {
            for (int j = 0; j < getAnimationColumnCount(); j++, index++) {
                frames[index] = tmp[i][j];
            }
        }
    }
}
