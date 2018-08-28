package org.fundacionjala.at04.teamtetris.views;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by abelb on 6/26/2017.
 */
public class MainStageView implements StageView {

  private Stage mainStage;
  private HBox root;

  /**
   * Default Constructor.
   *
   * @param stage Stage.
   */
  public MainStageView(Stage stage) {
    this.mainStage = stage;
    root = new HBox();
    Image img = new Image("file:resources/main-menu.jpg");
    BackgroundImage bgImg = new BackgroundImage(img,
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
            false, false, true, true));
    root.setBackground(new Background(bgImg));
    stage.setScene(new Scene(root, 1030, 500));
    stage.setResizable(false);
    stage.show();

  }

  /**
   * Set Stage Title.
   *
   * @param title String.
   */
  public void setTitle(String title) {
    this.mainStage.setTitle(title);
  }

  /**
   * Override Interface StageView.
   *
   * @return Stage.
   */
  @Override
  public Stage getMainView() {
    return this.mainStage;
  }

  @Override
  public HBox getRootPane() {
    return this.root;
  }
}
