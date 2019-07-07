package sk.zilak.pacman_ai;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.zilak.pacman_ai.screen.PlayScreen;

public class PacManGame extends Game {

    public static final float FPS = 60;

    public static final int WINDOW_WIDTH = 608;
    public static final int WINDOW_HEIGHT = 775;
    public static final int GAME_WIDTH = 608;
    public static final int GAME_HEIGHT = 704;
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final float PIXELS_PER_METER = 100;

    public static final short DEFAULT_BIT = 1;
    public static final short PACMAN_BIT = 2;
    public static final short BALL_BIT = 4;
    public static final short POWERUP_BIT = 8;
    public static final short EATEN_BIT = 16;
    public static final short GHOST_BIT = 32;

    public SpriteBatch batch;
//    private Texture img;

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
//        img.dispose();
    }
}
