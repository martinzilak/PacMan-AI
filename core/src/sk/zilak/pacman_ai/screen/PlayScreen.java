package sk.zilak.pacman_ai.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sk.zilak.pacman_ai.PacManGame;
import sk.zilak.pacman_ai.scene.Hud;
import sk.zilak.pacman_ai.sprite.PacMan;

import java.util.Arrays;
import java.util.List;

import static sk.zilak.pacman_ai.PacManGame.*;

public class PlayScreen implements Screen {

    private PacManGame game;
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

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/level-1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PIXELS_PER_METER);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        MapObject playerSpawn = map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class).get(0);
        Rectangle spawnRectangle = ((RectangleMapObject) playerSpawn).getRectangle();
        player = new PacMan(world, spawnRectangle.x + spawnRectangle.width / 2, spawnRectangle.y + spawnRectangle.height / 2);

        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

//        List<String> objectTypes = Arrays.asList("wall", "ball", "powerup");
        List<Integer> objectTypes = Arrays.asList(4, 5, 6);

        for(Integer objectType : objectTypes) {
            for(MapObject mapObject : map.getLayers().get(objectType).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(
                        (rectangle.getX() + rectangle.getWidth() / 2) / PIXELS_PER_METER,
                        (rectangle.getY() + rectangle.getHeight() / 2) / PIXELS_PER_METER
                );

                body = world.createBody(bodyDef);

                polygonShape.setAsBox(
                        (rectangle.getWidth() / 2) / PIXELS_PER_METER,
                        (rectangle.getHeight() / 2) / PIXELS_PER_METER
                );
                fixtureDef.shape = polygonShape;
                body.createFixture(fixtureDef);
            }
        }
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

        b2dr.render(world, camera.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void update(float delta) {
        processInput(delta);

        world.step(1/60f, 6, 2);

        player.update(delta);

        camera.update();
        mapRenderer.setView(camera);
    }

    public void processInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.body.applyLinearImpulse(new Vector2(0, -4f), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.body.applyLinearImpulse(new Vector2(-4f, 0), player.body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.body.applyLinearImpulse(new Vector2(4f, 0), player.body.getWorldCenter(), true);
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

    }
}
