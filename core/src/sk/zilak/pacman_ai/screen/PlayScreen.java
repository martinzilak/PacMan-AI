package sk.zilak.pacman_ai.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sk.zilak.pacman_ai.PacMan;
import sk.zilak.pacman_ai.scene.Hud;

import static sk.zilak.pacman_ai.PacMan.*;

public class PlayScreen implements Screen {

    private PacMan game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;

    public PlayScreen(PacMan game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, camera);
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
