package sk.zilak.pacman_ai.utilities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import sk.zilak.pacman_ai.sprite.Ball;
import sk.zilak.pacman_ai.sprite.Powerup;

import java.util.Arrays;
import java.util.List;

import static sk.zilak.pacman_ai.PacManGame.PIXELS_PER_METER;

public class B2WorldCreator {

    public static final int WALL_INDEX = 4;
    public static final int BALL_INDEX = 5;
    public static final int POWERUP_INDEX = 6;
    public static final int PLAYER_SPAWN_INDEX = 10;

    private World world;
    private TiledMap map;

    public B2WorldCreator(World world, TiledMap map) {
        this.world = world;
        this.map = map;

        for(MapObject mapObject : map.getLayers().get(WALL_INDEX).getObjects().getByType(RectangleMapObject.class)) {
            createBody(world, mapObject, BodyDef.BodyType.StaticBody);
        }

        for(MapObject mapObject : map.getLayers().get(BALL_INDEX).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

            new Ball(world, map, rectangle);
        }

        for(MapObject mapObject : map.getLayers().get(POWERUP_INDEX).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

            new Powerup(world, map, rectangle);
        }
    }

    public static void createBody(World world, MapObject mapObject, BodyDef.BodyType bodyType) {
        Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(
                (rectangle.getX() + rectangle.getWidth() / 2) / PIXELS_PER_METER,
                (rectangle.getY() + rectangle.getHeight() / 2) / PIXELS_PER_METER
        );

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(
                (rectangle.getWidth() / 2) / PIXELS_PER_METER,
                (rectangle.getHeight() / 2) / PIXELS_PER_METER
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }
}
