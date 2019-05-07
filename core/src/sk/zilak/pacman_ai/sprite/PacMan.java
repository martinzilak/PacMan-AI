package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static sk.zilak.pacman_ai.PacManGame.PIXELS_PER_METER;

public class PacMan extends Sprite {

    public static final int PLAYER_SIZE = 12;

    public World world;
    public Body body;

    public PacMan(World world, float x, float y) {
        this.world = world;
        definePacMan(x, y);
    }

    public void definePacMan(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x / PIXELS_PER_METER, y / PIXELS_PER_METER);
        bodyDef.type = BodyDef.BodyType.KinematicBody;

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
