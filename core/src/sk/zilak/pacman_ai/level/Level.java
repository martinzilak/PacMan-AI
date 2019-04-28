package sk.zilak.pacman_ai.level;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int width;
    private int height;
    private List<LevelTile> levelTiles = new ArrayList<>(); // todo: implement this
    private LevelGraph levelGraph;

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

    public List<LevelTile> getLevelTiles() {
        return levelTiles;
    }

    public void setLevelTiles(List<LevelTile> levelTiles) {
        this.levelTiles = levelTiles;
    }

    public LevelGraph getLevelGraph() {
        return levelGraph;
    }

    public void setLevelGraph(LevelGraph levelGraph) {
        this.levelGraph = levelGraph;
    }

    public LevelTile getTileAt(int x, int y) {
        return levelTiles.get(indexFromCoordinates(x, y));
    }

    // todo: this needs fixing
    public void setTileAt(int x, int y, LevelTileType tileType) {
        levelTiles.set(indexFromCoordinates(x, y), new LevelTile());
    }

    public void setTileAt(int x, int y, char tileType) {
        setTileAt(x, y, LevelTileType.get(tileType));
    }

    private int indexFromCoordinates(int x, int y) {
        return (y * height) + x;
    }
}
