package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import sk.zilak.pacman_ai.screen.PlayScreen;

import java.awt.*;

import static sk.zilak.pacman_ai.PacManGame.*;
import static sk.zilak.pacman_ai.utilities.Utilities.getTextureRegion;

public class PacMan extends Sprite {

    public static final int PLAYER_SIZE = 12;

    public World world;
    public Body body;

    private TextureRegion pacmanStand;

    public PacMan(World world, PlayScreen playScreen, Rectangle playerSpawn) {
        super(playScreen.getTextureAtlas().findRegion("pacman"));
        this.world = world;
        definePacMan(playerSpawn.x + playerSpawn.width / 2, playerSpawn.y + playerSpawn.height / 2);

        pacmanStand = getTextureRegion(getTexture(), 2, 0);
        setBounds(0, 0, TILE_WIDTH / PIXELS_PER_METER, TILE_HEIGHT / PIXELS_PER_METER);
        setRegion(pacmanStand);
    }

    public void definePacMan(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x / PIXELS_PER_METER, y / PIXELS_PER_METER);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(PLAYER_SIZE / PIXELS_PER_METER);

        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }
}
