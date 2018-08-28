package org.fundacionjala.at04.teamtetris.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.fundacionjala.at04.teamtetris.controllers.MainStageController;
import org.fundacionjala.at04.teamtetris.models.StageModel;
import org.fundacionjala.at04.teamtetris.models.StageModelImpl;
import org.fundacionjala.at04.teamtetris.views.MainStageView;
import org.fundacionjala.at04.teamtetris.views.StageView;

/**
 * Created by abelb on 6/26/2017.
 */
public class TetrisMain extends Application {

  /**
   * Main for application.
   * @param args String[].
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Override start method from Application.
   * @param primaryStage Stage.
   * @throws Exception Any.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    StageView mainStageView = new MainStageView(primaryStage);
    StageModel mainStageModel = new StageModelImpl("Tetris");
    MainStageController mainStageController = new MainStageController(mainStageView,
        mainStageModel);
    mainStageController.run();
  }
}
