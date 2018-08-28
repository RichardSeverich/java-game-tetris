package org.fundacionjala.at04.teamtetris.views.gamescreen;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by AbelBarrientos on 6/27/2017.
 */
public class PanelView implements IPanelView {

  private Label labelTitle;
  private BorderPane panelView;

  /**
   * Default Constructor.
   *
   * @param title String.
   */
  public PanelView(String title) {
    panelView = new BorderPane();
    setLabel(title);
    panelView.setTop(this.labelTitle);
    panelView.setMaxWidth(125);
    panelView.setMinHeight(125);
  }

  /**
   * Set Label Title.
   * @param title String.
   */
  private void setLabel(String title) {
    this.labelTitle = new Label(title);
    this.labelTitle.setTextFill(Color.WHITE);
    this.labelTitle.setMinHeight(30);
    this.labelTitle.setMinWidth(100);
    this.labelTitle.setFont(Font.font("Verdana", 18));
    this.labelTitle.setBorder(new Border(new BorderStroke(Color.WHITE,
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    this.labelTitle.setBackground(new Background(new BackgroundFill(Color.web("#2e86ff"),
        CornerRadii.EMPTY, Insets.EMPTY)));
  }

  /**
   * Return Pane.
   * @return BorderPane.
   */
  @Override
  public BorderPane getPane() {
    return this.panelView;
  }

  /**
   * Return Pane Content.
   * @param node Node.
   */
  @Override
  public void setPanelContent(Node node) {
    panelView.setCenter(node);
  }
}
