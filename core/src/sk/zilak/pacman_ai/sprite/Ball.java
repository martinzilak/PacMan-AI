package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static sk.zilak.pacman_ai.utilities.B2WorldCreator.BALL_INDEX;

public class Ball extends InteractiveTileObject {

    public Ball(World world, TiledMap map, Rectangle tileBounds) {
        super(world, map, tileBounds, BodyDef.BodyType.KinematicBody);
    }
}
