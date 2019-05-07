package sk.zilak.pacman_ai.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static sk.zilak.pacman_ai.PacManGame.WINDOW_HEIGHT;
import static sk.zilak.pacman_ai.PacManGame.WINDOW_WIDTH;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private int score = 0;
    private int life = 3;

    private Label lifeTextLabel;
    private Label scoreTextLabel;
    private Label lifeValueLabel;
    private Label scoreValueLabel;

    public Hud(SpriteBatch spriteBatch) {
        viewport = new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        lifeTextLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreTextLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lifeValueLabel = new Label(String.format("%01d", life), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreValueLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(lifeTextLabel).expandX().padTop(10);
        table.add(scoreTextLabel).expandX().padTop(10);
        table.row();
        table.add(lifeValueLabel).expandX().padTop(10);
        table.add(scoreValueLabel).expandX().padTop(10);

        stage.addActor(table);
    }
}
