package sk.zilak.pacman_ai.behavior;

import sk.zilak.pacman_ai.entity.Entity;
import sk.zilak.pacman_ai.game.Game;
import sk.zilak.pacman_ai.utilities.Vector2;

public interface Behavior {

    Vector2 getNextStep(Entity self, Game game);
}
