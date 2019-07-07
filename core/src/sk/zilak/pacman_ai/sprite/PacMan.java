package sk.zilak.pacman_ai.sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import sk.zilak.pacman_ai.screen.PlayScreen;

import static sk.zilak.pacman_ai.PacManGame.*;
import static sk.zilak.pacman_ai.sprite.PacMan.AnimationState.MOVING;
import static sk.zilak.pacman_ai.sprite.PacMan.AnimationState.STANDING;
import static sk.zilak.pacman_ai.utilities.Utilities.getTextureRegion;

public class PacMan extends Sprite {

    public static final int PLAYER_SIZE = 13;
    public static final float SPEED_STEP = 0.1f;
    public static final float MAX_SPEED = 1.5f;
    public static final String USER_DATA_NAME = "pacman-collision";

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
            getTextureRegion(getTexture(), 0, 0),
            getTextureRegion(getTexture(), 1, 0),
            getTextureRegion(getTexture(), 2, 0)
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

        fixtureDef.filter.categoryBits = PACMAN_BIT;
        fixtureDef.filter.maskBits = DEFAULT_BIT | BALL_BIT | POWERUP_BIT | GHOST_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        CircleShape centerPoint = new CircleShape();
        centerPoint.setRadius(0.1f);
        fixtureDef.shape = centerPoint;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData(USER_DATA_NAME);
    }

    public void update(float delta){
        setRegion(getAnimationFrame(delta));
        setOriginCenter();
        setRotation(getAnimationRotation());
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
        float velocityX = body.getLinearVelocity().x;
        float velocityY = body.getLinearVelocity().y;

        if(velocityX == 0 && velocityY == 0) {
            return STANDING;
        }

        if(Math.abs(velocityX) > Math.abs(velocityY)) {
            movementDirection = velocityX > 0 ? MovementDirection.RIGHT : MovementDirection.LEFT;
        } else {
            movementDirection = velocityY > 0 ? MovementDirection.UP : MovementDirection.DOWN;
        }

        return MOVING;
    }

    public float getAnimationRotation() {
        switch (movementDirection) {
            case UP:
                return 90;
            case DOWN:
                return 270;
            case LEFT:
                return 180;
            case RIGHT:
            default:
                return 0;
        }
    }
}
