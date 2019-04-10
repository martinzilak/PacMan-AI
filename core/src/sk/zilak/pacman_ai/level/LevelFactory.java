package sk.zilak.pacman_ai.level;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.IOException;

public class LevelFactory {

    private static final int BUFFER_SIZE = 8 * 1024;
    private static final String LEVEL_FILE_PATH = "levels/";
    private String fileName;

    private LevelFactory(String filePath) {
        this.fileName = filePath;
    }

    public static LevelFactory load(String fileName) {
        return new LevelFactory(fileName);
    }

    public Level createLevel() throws LevelFileException {
        Level level = new Level();
        BufferedReader levelReader = Gdx.files.internal(LEVEL_FILE_PATH + fileName).reader(BUFFER_SIZE);

        try {
            String[] dimensionsLine = levelReader.readLine().split(" ");

            int width = Integer.parseInt(dimensionsLine[0]);
            level.setWidth(width);

            int height = Integer.parseInt(dimensionsLine[1]);
            level.setHeight(height);

            for(int y = 0; y < height; y++) {
                String levelLine = levelReader.readLine();

                for(int x = 0; x < width; x++) {
                    char levelChar = levelLine.charAt(x);

                    level.setTileAt(x, y, levelChar);
                }
            }
        } catch (IOException e) {
            throw new LevelFileException("Unable to parse the given level file", e);
        }

        return level;
    }
}
