package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static sk.zilak.pacman_ai.PacManGame.PIXELS_PER_METER;
import static sk.zilak.pacman_ai.utilities.B2WorldCreator.createBody;

public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle tileBounds;
    protected Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle tileBounds, BodyDef.BodyType bodyType) {
        this.world = world;
        this.map = map;
        this.tileBounds = tileBounds;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(
                (tileBounds.getX() + tileBounds.getWidth() / 2) / PIXELS_PER_METER,
                (tileBounds.getY() + tileBounds.getHeight() / 2) / PIXELS_PER_METER
        );

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(
                (tileBounds.getWidth() / 2) / PIXELS_PER_METER,
                (tileBounds.getHeight() / 2) / PIXELS_PER_METER
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }
}
