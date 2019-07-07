package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static sk.zilak.pacman_ai.PacManGame.*;

public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle tileBounds;
    protected Body body;
    protected Fixture fixture;

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
        fixtureDef.isSensor = true;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void onCollision();

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell() {
        int layerIndex = getLayerIndex();
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerIndex);
        return layer.getCell(
            (int) (body.getPosition().x * PIXELS_PER_METER / TILE_WIDTH),
            (int) (body.getPosition().y * PIXELS_PER_METER / TILE_HEIGHT)
        );
    }

    public abstract int getLayerIndex();
}
