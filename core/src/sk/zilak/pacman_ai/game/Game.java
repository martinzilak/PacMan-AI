package sk.zilak.pacman_ai.game;

import sk.zilak.pacman_ai.entity.Entity;
import sk.zilak.pacman_ai.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Game gameInstance = new Game();

    private Level level;
    private Entity player;
    private List<Entity> ghosts = new ArrayList<>();

    private Game() {

    }

    public static Game getGameInstance() {
        return gameInstance;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public List<Entity> getGhosts() {
        return ghosts;
    }

    public void addGhost(Entity ghost) {
        ghosts.add(ghost);
    }
}
