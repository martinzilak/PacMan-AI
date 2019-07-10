package sk.zilak.pacman_ai.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static sk.zilak.pacman_ai.PacManGame.WINDOW_HEIGHT;
import static sk.zilak.pacman_ai.PacManGame.WINDOW_WIDTH;

public class Hud implements Disposable {
    public Stage stage;

    public static final int STARTING_SCORE = 0;
    public static final int STARTING_LIVES = 3;

    private static int score = STARTING_SCORE;
    private static int lives = STARTING_LIVES;
    private static Label lifeValueLabel;
    private static Label scoreValueLabel;

    public Hud(SpriteBatch spriteBatch) {
        Viewport viewport = new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label lifeTextLabel = generateHudLabel("LIVES");
        Label scoreTextLabel = generateHudLabel("SCORE");
        scoreValueLabel = generateHudLabel(String.format("%06d", score));
        lifeValueLabel = generateHudLabel(String.format("%01d", lives));

        table.add(lifeTextLabel).expandX().padTop(10);
        table.add(scoreTextLabel).expandX().padTop(10);
        table.row();
        table.add(lifeValueLabel).expandX().padTop(10);
        table.add(scoreValueLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public static void addScore(int score) {
        Hud.score += score;
        updateScoreValueLabel(Hud.score);
    }

    public static void loseLife() {
        lives--;
        updateLifeValueLabel(lives);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private static Label generateHudLabel(String formattedText) {
        return new Label(formattedText, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }

    private static void updateScoreValueLabel(int score) {
        scoreValueLabel.setText(String.format("%06d", score));
    }

    private static void updateLifeValueLabel(int lives) {
        lifeValueLabel.setText(String.format("%01d", lives));
    }
}