package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import sk.zilak.pacman_ai.scene.Hud;

import java.util.HashMap;
import java.util.Map;

import static sk.zilak.pacman_ai.PacManGame.BALL_BIT;
import static sk.zilak.pacman_ai.PacManGame.EATEN_BIT;

public class Ball extends InteractiveTileObject {

    private static final int LAYER_INDEX = 2;
    public static final int BALL_SCORE = 100;

    private static Map<World, Integer> worldBallCount = new HashMap();

    public Ball(World world, TiledMap map, Rectangle tileBounds) {
        super(world, map, tileBounds, BodyDef.BodyType.KinematicBody);

        fixture.setUserData(this);
        setCategoryFilter(BALL_BIT);

        ballCreated();
    }

    @Override
    public void onCollision() {
        setCategoryFilter(EATEN_BIT);
        TiledMapTileLayer.Cell cell = getCell();
        if(cell != null) {
            getCell().setTile(null);
        }

        ballEaten();

        Hud.addScore(BALL_SCORE);
    }

    public static int getBallCount(World world) {
        return worldBallCount.getOrDefault(world, 0);
    }

    private void ballCreated() {
        worldBallCount.put(this.world, worldBallCount.getOrDefault(this.world, 0) + 1);
    }

    private void ballEaten() {
        worldBallCount.put(this.world, worldBallCount.getOrDefault(this.world, 0) - 1);
    }

    @Override
    public int getLayerIndex() {
        return LAYER_INDEX;
    }
}
