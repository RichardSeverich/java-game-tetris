package org.fundacionjala.at04.teamtetris.controllers.observer;

import java.util.Observable;
import java.util.Observer;

import org.fundacionjala.at04.teamtetris.controllers.GameScreenController;
import org.fundacionjala.at04.teamtetris.controllers.MainStageController;

/**
 * Created by abelb on 7/23/2017.
 */
public class GameOverObserver implements Observer {

  private GameScreenController gameScreenController;
  private MainStageController mainStageController;

  /**
   * Constructor for the GameScreenController.
   *
   * @param gameScreenController game Screen Controller.
   */
  public GameOverObserver(MainStageController mainStageController,
                          GameScreenController gameScreenController) {
    super();
    this.gameScreenController = gameScreenController;
    this.mainStageController = mainStageController;
  }

  /**
   * Method to send tha punishment.
   *
   * @param o   Observable.
   * @param arg Object.
   */
  @Override
  public void update(Observable o, Object arg) {
    mainStageController.getTetrisSound().stopBackGroundSound();
    mainStageController.getTimelineForTimer().stop();
    mainStageController.setGameOver();
    gameScreenController.getTimeLine().stop();
    gameScreenController.setCanMove();
    gameScreenController.drawGameOver();
  }
}
