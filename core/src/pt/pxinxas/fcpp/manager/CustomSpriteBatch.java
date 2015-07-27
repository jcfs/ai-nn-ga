package pt.pxinxas.fcpp.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * @author jsalavisa
 *
 */
public class CustomSpriteBatch extends SpriteBatch {

    private static final float DEFAULT_SCALE = 1f;

    public CustomSpriteBatch(int size, ShaderProgram defaultShader) {
        super(size, defaultShader);
    }

    public void draw(TextureRegion region, float x, float y, float width, float height, float alpha, float rotation) {
        Color oldColor = this.getColor();
        float oldAlpha = oldColor.a;
        oldColor.a = alpha;
        this.setColor(oldColor);
        this.draw(region, x, y, width/2, height/2, width, height, DEFAULT_SCALE, DEFAULT_SCALE, rotation);
        oldColor.a = oldAlpha;
        this.setColor(oldColor);
    }
    
    public void draw(TextureRegion region, float x, float y, float originX, float originY, float width, float height, float alpha, float rotation) {
        Color oldColor = this.getColor();
        float oldAlpha = oldColor.a;
        oldColor.a = alpha;
        this.setColor(oldColor);
        this.draw(region, x, y, originX, originY, width, height, DEFAULT_SCALE, DEFAULT_SCALE, rotation);
        oldColor.a = oldAlpha;
        this.setColor(oldColor);
    }
    
}
