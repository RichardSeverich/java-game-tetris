package org.fundacionjala.at04.teamtetris.views.gamescreen;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Created by Abel on 16/07/2017.
 */
public interface IPanelView {

  BorderPane getPane();

  void setPanelContent(Node node);
}
