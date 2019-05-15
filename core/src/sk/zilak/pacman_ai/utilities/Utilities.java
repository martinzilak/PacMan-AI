package sk.zilak.pacman_ai.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static sk.zilak.pacman_ai.PacManGame.TILE_HEIGHT;
import static sk.zilak.pacman_ai.PacManGame.TILE_WIDTH;

public class Utilities {

    public static TextureRegion getTextureRegion(Texture texture, int positionX, int positionY, int tileWidth, int tileHeight) {
        return new TextureRegion(texture, positionX * tileWidth, positionY * tileHeight, tileWidth, tileHeight);
    }

    public static TextureRegion getTextureRegion(Texture texture, int positionX, int positionY) {
        return getTextureRegion(texture, positionX, positionY, TILE_WIDTH, TILE_HEIGHT);
    }
}
