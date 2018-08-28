package org.fundacionjala.at04.teamtetris.controllers.observer;

import java.util.Observable;
import java.util.Observer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import org.fundacionjala.at04.teamtetris.controllers.GameScreenController;
import org.fundacionjala.at04.teamtetris.controllers.gamesound.TetrisSound;
import org.fundacionjala.at04.teamtetris.models.Level;

/**
 * Created by abelb on 7/23/2017.
 */
public class LevelObserver implements Observer {

  private GameScreenController gameScreenController;
  private Level level;
  private Timeline timeline;
  private Double millis;
  private TetrisSound tetrisSound;

  /**
   * Default Constructor.
   *
   * @param gameScreenController GameScreenController.
   * @param level                Level.
   * @param timeline             TimeLine.
   */
  public LevelObserver(GameScreenController gameScreenController, Level level, Timeline timeline) {
    this.gameScreenController = gameScreenController;
    this.level = level;
    this.timeline = timeline;
    millis = 600.0;
    tetrisSound = new TetrisSound();
  }

  /**
   * Changes to made.
   *
   * @param o   Observable.
   * @param arg Object.
   */
  @Override
  public void update(Observable o, Object arg) {
    gameScreenController.setLevel(level);
    timeline.stop();
    timeline = initTimeLine();
    timeline.play();
    tetrisSound.playLevelUpSound();
  }

  /**
   * Reset Timeline.
   *
   * @return Timeline.
   */
  private Timeline initTimeLine() {
    this.millis = millis - (this.millis * level.currentSpeed());
    timeline = new Timeline(new KeyFrame(
        Duration.millis(millis),
        moveDown -> {
          gameScreenController.moveDown();
        }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    return timeline;
  }
}
