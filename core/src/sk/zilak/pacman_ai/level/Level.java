package sk.zilak.pacman_ai.level;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int width;
    private int height;
    private List<LevelTileType> levelTiles = new ArrayList<>();

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<LevelTileType> getLevelTiles() {
        return levelTiles;
    }

    public void setLevelTiles(List<LevelTileType> levelTiles) {
        this.levelTiles = levelTiles;
    }

    public LevelTileType getTileAt(int x, int y) {
        return levelTiles.get(indexFromCoordinates(x, y));
    }

    public void setTileAt(int x, int y, LevelTileType tileType) {
        levelTiles.set(indexFromCoordinates(x, y), tileType);
    }

    public void setTileAt(int x, int y, char tileType) {
        setTileAt(x, y, LevelTileType.get(tileType));
    }

    private int indexFromCoordinates(int x, int y) {
        return (y * height) + x;
    }
}
