package org.fundacionjala.at04.teamtetris.controllers.eventhandlers;

import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.fundacionjala.at04.teamtetris.controllers.GameScreenController;
import org.fundacionjala.at04.teamtetris.controllers.MainStageController;

/**
 * Created by Abel on 16/07/2017.
 */
public class MovesEventHandler implements EventHandler<KeyEvent> {

  private Map<String, KeyCode> keyValuesOne;
  private Map<String, KeyCode> keyValuesTwo;
  private GameScreenController controllerOne;
  private GameScreenController controllerTwo;
  private Boolean isPaused;
  private MainStageController mainStageController;

  /**
   * Default Constructor.
   *
   * @param controllerOne GameScreenController.
   * @param controllerTwo GameScreenController.
   * @param keyValuesOne  Map.
   * @param keyValuesTwo  Map.
   */
  public MovesEventHandler(MainStageController mainStageController,
                           GameScreenController controllerOne,
                           GameScreenController controllerTwo, Map<String,
      KeyCode> keyValuesOne, Map<String, KeyCode> keyValuesTwo) {
    this.mainStageController = mainStageController;
    this.controllerOne = controllerOne;
    this.controllerTwo = controllerTwo;
    this.keyValuesOne = keyValuesOne;
    this.keyValuesTwo = keyValuesTwo;
    isPaused = false;
  }

  /**
   * Handle Events.
   *
   * @param event KeyEvent.
   */
  @Override
  public void handle(KeyEvent event) {
    if (event.getCode() == KeyCode.N) {
      mainStageController.restart();
    }
    if (event.getCode() == KeyCode.P) {
      isPaused = !isPaused;
      controllerOne.getTimeLine().pause();
      controllerTwo.getTimeLine().pause();
      mainStageController.getTetrisSound().pauseBackGroundSound();
      mainStageController.getTimelineForTimer().pause();
    }
    if (!isPaused) {
      if (!mainStageController.getGameOver()) {
        mainStageController.getTetrisSound().playBackGroundSound();
        mainStageController.getTimelineForTimer().play();
      }
      controllerOne.getTimeLine().play();
      controllerTwo.getTimeLine().play();
      if (event.getCode() == keyValuesOne.get("LEFT")) {
        controllerOne.moveLeft();
      }
      if (event.getCode() == keyValuesOne.get("RIGHT")) {
        controllerOne.moveRight();
      }
      if (event.getCode() == keyValuesOne.get("DOWN")) {
        controllerOne.moveBottom();
      }
      if (event.getCode() == keyValuesOne.get("UP")) {
        controllerOne.rotate();
      }
      if (event.getCode() == keyValuesTwo.get("LEFT")) {
        controllerTwo.moveLeft();
      }
      if (event.getCode() == keyValuesTwo.get("RIGHT")) {
        controllerTwo.moveRight();
      }
      if (event.getCode() == keyValuesTwo.get("DOWN")) {
        controllerTwo.moveBottom();
      }
      if (event.getCode() == keyValuesTwo.get("UP")) {
        controllerTwo.rotate();
      }
    }
  }
}
