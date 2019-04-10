package sk.zilak.pacman_ai.level;

public class LevelFileException extends Exception {

    public LevelFileException(String message) {
        super(message);
    }

    public LevelFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public LevelFileException(Throwable cause) {
        super(cause);
    }
}
