package sk.zilak.pacman_ai.utilities;

import com.badlogic.gdx.physics.box2d.*;
import sk.zilak.pacman_ai.sprite.InteractiveTileObject;

import static sk.zilak.pacman_ai.sprite.PacMan.USER_DATA_NAME;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(USER_DATA_NAME.equals(fixtureA.getUserData()) || USER_DATA_NAME.equals(fixtureB.getUserData())) {
            Fixture pacman = USER_DATA_NAME.equals(fixtureA.getUserData()) ? fixtureA : fixtureB;
            Fixture other = pacman.equals(fixtureA) ? fixtureB : fixtureA;

            if(other.getUserData() != null && other.getUserData() instanceof InteractiveTileObject) {
                ((InteractiveTileObject) other.getUserData()).onCollision();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
