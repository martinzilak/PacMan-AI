package sk.zilak.pacman_ai.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sk.zilak.pacman_ai.PacManGame;
import sk.zilak.pacman_ai.scene.Hud;
import sk.zilak.pacman_ai.sprite.Ball;
import sk.zilak.pacman_ai.sprite.PacMan;
import sk.zilak.pacman_ai.utilities.B2WorldCreator;
import sk.zilak.pacman_ai.utilities.WorldContactListener;

import static sk.zilak.pacman_ai.PacManGame.*;
import static sk.zilak.pacman_ai.sprite.PacMan.MAX_SPEED;
import static sk.zilak.pacman_ai.sprite.PacMan.SPEED_STEP;
import static sk.zilak.pacman_ai.utilities.B2WorldCreator.PLAYER_SPAWN_INDEX;

public class PlayScreen implements Screen {

    private PacManGame game;
    private TextureAtlas textureAtlas;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private PacMan player;

    public PlayScreen(PacManGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(WINDOW_WIDTH / PIXELS_PER_METER, WINDOW_HEIGHT / PIXELS_PER_METER, camera);
        hud = new Hud(game.batch);

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        textureAtlas = new TextureAtlas("sprites/entities.atlas");

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/level-1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PIXELS_PER_METER);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        Rectangle playerSpawn = map.getLayers().get(PLAYER_SPAWN_INDEX).getObjects().getByType(RectangleMapObject.class).get(0).getRectangle();
        player = new PacMan(world, this, playerSpawn);
        player.body.setFixedRotation(true);

        new B2WorldCreator(world, map);

        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getTextureAtlas() {
        return this.textureAtlas;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

//        b2dr.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void update(float delta) {
        processInput(delta);
        limitVelocity();

        world.step(1/FPS, 6, 2);

        player.update(delta);

        checkWinCondition();

        camera.update();
        mapRenderer.setView(camera);
    }

    public void processInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.body.applyLinearImpulse(new Vector2(0, SPEED_STEP), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.body.applyLinearImpulse(new Vector2(0, -SPEED_STEP), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.body.applyLinearImpulse(new Vector2(-SPEED_STEP, 0), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.body.applyLinearImpulse(new Vector2(SPEED_STEP, 0), player.body.getWorldCenter(), true);
        }

        if(!(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S))) {
            player.body.setLinearVelocity(player.body.getLinearVelocity().x, 0);
        }
        if(!(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D))) {
            player.body.setLinearVelocity(0, player.body.getLinearVelocity().y);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    public void limitVelocity() {
        Vector2 velocity = player.body.getLinearVelocity();
        float velocityX = velocity.x;
        float velocityY = velocity.y;

        if(Math.abs(velocityX) > Math.abs(velocityY)) {
            player.body.setLinearVelocity(Math.min(Math.abs(velocityX), MAX_SPEED) * Math.signum(velocityX), 0);
        } else {
            player.body.setLinearVelocity(0, Math.min(Math.abs(velocityY), MAX_SPEED) * Math.signum(velocityY));
        }
    }

    private void checkWinCondition() {
        if(Ball.getBallCount(this.world) <= 0) {
            this.game.pause();
        }
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
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
