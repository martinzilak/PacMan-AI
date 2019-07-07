package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import static sk.zilak.pacman_ai.PacManGame.BALL_BIT;
import static sk.zilak.pacman_ai.PacManGame.EATEN_BIT;

public class Ball extends InteractiveTileObject {

    private static final int LAYER_INDEX = 2;

    public Ball(World world, TiledMap map, Rectangle tileBounds) {
        super(world, map, tileBounds, BodyDef.BodyType.KinematicBody);
        fixture.setUserData(this);
        setCategoryFilter(BALL_BIT);
    }

    @Override
    public void onCollision() {
        setCategoryFilter(EATEN_BIT);
        TiledMapTileLayer.Cell cell = getCell();
        if(cell != null) {
            getCell().setTile(null);
        }
    }

    @Override
    public int getLayerIndex() {
        return LAYER_INDEX;
    }
}
