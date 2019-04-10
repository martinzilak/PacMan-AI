package sk.zilak.pacman_ai.level;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LevelTileType {
    WALL("W"),
    BALL("."),
    POWERUP("O"),
    EMPTY(" "),
    PLAYER_SPAWN("P"),
    GHOST_SPAWN("G"),
    GHOST_DOOR("D"),
    TELEPORT("T");

    private String tileChar;

    private static final Map<String, LevelTileType> ENUM_MAP;

    LevelTileType(String tileChar) {
        this.tileChar = tileChar;
    }

    public String getTileChar() {
        return this.tileChar;
    }

    static {
        Map<String, LevelTileType> map = Stream
                .of(LevelTileType.values())
                .collect(Collectors.toMap(Enum::name, Function.identity()));
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static LevelTileType get(String tileChar) {
        return ENUM_MAP.get(tileChar);
    }
    
    public static LevelTileType get(char tileChar) {
        return ENUM_MAP.get(String.valueOf(tileChar));
    }
}
