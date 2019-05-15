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
    public static final int STARTING_LIFE = 3;

    public Hud(SpriteBatch spriteBatch) {
        Viewport viewport = new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label lifeTextLabel = generateHudLabel("LIVES");
        Label scoreTextLabel = generateHudLabel("SCORE");
        Label lifeValueLabel = generateHudLabel(String.format("%01d", STARTING_LIFE));
        Label scoreValueLabel = generateHudLabel(String.format("%06d", STARTING_SCORE));

        table.add(lifeTextLabel).expandX().padTop(10);
        table.add(scoreTextLabel).expandX().padTop(10);
        table.row();
        table.add(lifeValueLabel).expandX().padTop(10);
        table.add(scoreValueLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private Label generateHudLabel(String text) {
        return new Label(text, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }
}
