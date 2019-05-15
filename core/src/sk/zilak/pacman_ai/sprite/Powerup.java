package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import static sk.zilak.pacman_ai.utilities.B2WorldCreator.POWERUP_INDEX;

public class Powerup extends InteractiveTileObject {

    public Powerup(World world, TiledMap map, Rectangle tileBounds) {
        super(world, map, tileBounds, BodyDef.BodyType.KinematicBody);
    }
}
