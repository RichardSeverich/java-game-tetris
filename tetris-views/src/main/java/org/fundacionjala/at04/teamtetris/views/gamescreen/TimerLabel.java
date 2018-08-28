package org.fundacionjala.at04.teamtetris.views.gamescreen;

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
 * Created by OvidioMiranda on 7/24/2017.
 */
public class TimerLabel {
  private Label label;

  /**
   * Default Constructor.
   */
  public TimerLabel() {
    label = new Label();
  }

  /**
   * Draw Timer in the View.
   *
   * @param timer Timer.
   * @return label.
   */
  public Label drawTimer(String timer) {
    label.setTextFill(Color.WHITE);
    label.setMinHeight(40);
    label.setMinWidth(50);
    label.setFont(Font.font("Verdana", 12));
    label.setBorder(new Border(new BorderStroke(Color.WHITE,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    label.setBackground(new Background(new BackgroundFill(Color.web("#2e86ff"),
        CornerRadii.EMPTY, Insets.EMPTY)));
    label.setText(timer);
    return label;
  }
}
