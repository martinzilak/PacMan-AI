package sk.zilak.pacman_ai.entity;

import sk.zilak.pacman_ai.game.Game;
import sk.zilak.pacman_ai.utilities.Vector2;
import sk.zilak.pacman_ai.behavior.Behavior;

public abstract class Entity {

    private Vector2 position;
    private Behavior behavior;
    private EntityState entityState;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public EntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

    public Vector2 getNextStep() {
        return behavior.getNextStep(this, Game.getGameInstance());
    }
}
