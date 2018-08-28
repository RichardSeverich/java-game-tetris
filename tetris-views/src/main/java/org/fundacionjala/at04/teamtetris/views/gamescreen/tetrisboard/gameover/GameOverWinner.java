package org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.gameover;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by abelb on 7/23/2017.
 */
public class GameOverWinner implements IGameOver {
  private Label gameOver;
  private String title;

  /**
   * Default Constructor.
   *
   * @param title String.
   */
  public GameOverWinner(String title) {
    this.title = title;
    gameOver = new Label(this.title);
  }

  /**
   * Set up the Label.
   *
   * @return Label.
   */
  @Override
  public Label showGameOverLabel() {
    gameOver.setTextFill(Color.YELLOW);
    gameOver.setMinHeight(70);
    gameOver.setMinWidth(250);
    gameOver.setFont(Font.font("Verdana", 32));
    gameOver.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    gameOver.setBackground(new Background(new BackgroundFill(Color.BLUE,
        CornerRadii.EMPTY, Insets.EMPTY)));
    return gameOver;
  }
}
