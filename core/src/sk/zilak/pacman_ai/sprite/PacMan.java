package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import sk.zilak.pacman_ai.screen.PlayScreen;

import static sk.zilak.pacman_ai.PacManGame.*;
import static sk.zilak.pacman_ai.sprite.PacMan.AnimationState.*;
import static sk.zilak.pacman_ai.utilities.Utilities.getTextureRegion;

public class PacMan extends Sprite {

    public static final int PLAYER_SIZE = 13;

    public enum AnimationState { STANDING, MOVING };
    public AnimationState currentState;
    private float stateTimer;

    public enum MovementDirection { UP, DOWN, LEFT, RIGHT };
    public MovementDirection movementDirection;

    public World world;
    public Body body;

    private TextureRegion pacmanStanding;
    private Animation pacmanMoving;

    public PacMan(World world, PlayScreen playScreen, Rectangle playerSpawn) {
        super(playScreen.getTextureAtlas().findRegion("pacman"));
        this.world = world;

        currentState = AnimationState.STANDING;
        stateTimer = 0;
        movementDirection = MovementDirection.RIGHT;

        definePacMan(playerSpawn.x + playerSpawn.width / 2, playerSpawn.y + playerSpawn.height / 2);

        Array<TextureRegion> movementFrames = Array.with(
            getTextureRegion(getTexture(), 1, 0),
            getTextureRegion(getTexture(), 0, 0)
        );

        pacmanMoving = new Animation(0.1f, movementFrames);

        pacmanStanding = getTextureRegion(getTexture(), 2, 0);
        setBounds(0, 0, TILE_WIDTH / PIXELS_PER_METER, TILE_HEIGHT / PIXELS_PER_METER);
        setRegion(pacmanStanding);
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
        setRegion(getAnimationFrame(delta));
        setAnimationRotation();
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }

    public TextureRegion getAnimationFrame(float delta) {
        currentState = getAnimationState();

        TextureRegion region;
        switch (currentState) {
            case MOVING:
                region = (TextureRegion) pacmanMoving.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = pacmanStanding;
                break;
        }

        if (currentState.equals(MOVING)) {
            stateTimer += delta;
        } else {
            stateTimer = 0;
        }

        return region;
    }

    public AnimationState getAnimationState() {
        if(body.getLinearVelocity().y > 0) {
            movementDirection = MovementDirection.UP;
            return MOVING;
        }
        else if(body.getLinearVelocity().y < 0) {
            movementDirection = MovementDirection.DOWN;
            return MOVING;
        }
        else if(body.getLinearVelocity().x < 0) {
            movementDirection = MovementDirection.LEFT;
            return MOVING;
        }
        else if(body.getLinearVelocity().x > 0) {
            movementDirection = MovementDirection.RIGHT;
            return MOVING;
        } else {
            return STANDING;
        }
    }

    //TODO: there has to be a better way...
    public void setAnimationRotation() {
        switch (movementDirection) {
            case UP:
                rotate90(false);
                break;
            case DOWN:
                rotate90(true);
                break;
            case LEFT:
                rotate90(true);
                rotate90(true);
                break;
            case RIGHT:
            default:
                break;
        }
    }
}
