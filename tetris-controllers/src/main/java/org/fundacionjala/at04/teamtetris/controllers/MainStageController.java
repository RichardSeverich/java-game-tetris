package org.fundacionjala.at04.teamtetris.controllers;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.fundacionjala.at04.teamtetris.controllers.eventhandlers.ExitEventHandler;
import org.fundacionjala.at04.teamtetris.controllers.eventhandlers.MovesEventHandler;
import org.fundacionjala.at04.teamtetris.controllers.gamesound.TetrisSound;
import org.fundacionjala.at04.teamtetris.controllers.observer.GameOverObservable;
import org.fundacionjala.at04.teamtetris.controllers.observer.GameOverObserver;
import org.fundacionjala.at04.teamtetris.controllers.observer.LevelObservable;
import org.fundacionjala.at04.teamtetris.controllers.observer.LevelObserver;
import org.fundacionjala.at04.teamtetris.controllers.observer.TetrisObservable;
import org.fundacionjala.at04.teamtetris.controllers.observer.TetrisObserver;
import org.fundacionjala.at04.teamtetris.models.BoardModel;
import org.fundacionjala.at04.teamtetris.models.BoardModelImpl;
import org.fundacionjala.at04.teamtetris.models.Level;
import org.fundacionjala.at04.teamtetris.models.StageModel;
import org.fundacionjala.at04.teamtetris.views.StageView;
import org.fundacionjala.at04.teamtetris.views.gamescreen.GameScreenMainView;
import org.fundacionjala.at04.teamtetris.views.gamescreen.GameScreenView;
import org.fundacionjala.at04.teamtetris.views.gamescreen.TimerLabel;

/**
 * Created by abelb on 6/26/2017.
 */
public class MainStageController {

  private static final int DEFAULT_TIME_MILLIS = 600;
  private GameScreenController firstGameScreenController;
  private GameScreenController secondGameScreenController;
  private StageView view;
  private StageModel model;
  private Timeline timelineForPlayerOne;
  private Timeline timelineForPlayerTwo;
  private TetrisObservable tetrisObservableOne;
  private TetrisObservable tetrisObservableTwo;
  private TetrisObserver tetrisObserverOne;
  private TetrisObserver tetrisObserverTwo;
  private GameOverObservable gameOverObservable;
  private GameOverObserver firstGameOverObserver;
  private GameOverObserver secondGameOverObserver;
  private TetrisSound tetrisSound;
  private Boolean gameOver;
  private Level level;
  private LevelObservable levelObservable;
  private LevelObserver firstLevelObserver;
  private LevelObserver secondLevelObserver;

  public Timeline getTimelineForTimer() {
    return timelineForTimer;
  }

  private Timeline timelineForTimer;
  private Integer seconds = 0;
  private Integer minutes = 0;
  private Integer hours = 0;
  private TimerLabel timerLabel;
  private String currentTime;


  private Map<String, KeyCode> playerOne = new HashMap<String, KeyCode>() {
    {
      put("DOWN", KeyCode.S);
      put("LEFT", KeyCode.A);
      put("RIGHT", KeyCode.D);
      put("UP", KeyCode.W);
    }
  };
  private Map<String, KeyCode> playerTwo = new HashMap<String, KeyCode>() {
    {
      put("DOWN", KeyCode.DOWN);
      put("LEFT", KeyCode.LEFT);
      put("RIGHT", KeyCode.RIGHT);
      put("UP", KeyCode.UP);
    }
  };

  /**
   * Default Construtor.
   *
   * @param view  StageView.
   * @param model StageModel.
   */
  public MainStageController(StageView view, StageModel model) {
    this.view = view;
    this.model = model;
  }

  /**
   * Set Stage Title.
   */
  private void setStageTitle() {
    view.getMainView().setTitle(model.getTitle());
  }

  /**
   * Set up the Event Handlers.
   */
  private void setupEventHandlers() {
    Stage stage = view.getMainView();
    stage.setOnCloseRequest(new ExitEventHandler());
    stage.getScene().setOnKeyPressed(new MovesEventHandler(this, firstGameScreenController,
        secondGameScreenController, playerOne, playerTwo));
    timelineForPlayerOne = initTimeLine(firstGameScreenController, DEFAULT_TIME_MILLIS);
    timelineForPlayerOne.play();
    timelineForPlayerTwo = initTimeLine(secondGameScreenController, DEFAULT_TIME_MILLIS);
    timelineForPlayerTwo.play();
  }

  /**
   * Initial timeline for players.
   *
   * @param playerGameScreenController GameScreenController.
   * @param millis                     millis.
   */
  private Timeline initTimeLine(GameScreenController playerGameScreenController, double millis) {
    Timeline timeLinePlayer;
    timeLinePlayer = new Timeline(new KeyFrame(
        Duration.millis(millis),
        moveDown -> {
          playerGameScreenController.moveDown();
        }));
    timeLinePlayer.setCycleCount(Timeline.INDEFINITE);
    return timeLinePlayer;
  }


  /**
   * Run the events.
   */
  public void run() {
    GameScreenMainView firstBoardView = new GameScreenView();
    BoardModel firstBoardModel = new BoardModelImpl(20, 10);
    firstGameScreenController = new GameScreenController(firstBoardModel,
        firstBoardView);
    GameScreenMainView secondBoardView = new GameScreenView();
    BoardModel secondBoardModel = new BoardModelImpl(20, 10);
    secondGameScreenController = new GameScreenController(secondBoardModel,
        secondBoardView);
    setUpPunishObservables();
    level = new Level();
    firstGameScreenController.setLevel(level);
    secondGameScreenController.setLevel(level);
    levelObservable = new LevelObservable(level);
    setupEventHandlers();
    firstLevelObserver = new LevelObserver(firstGameScreenController, level, timelineForPlayerOne);
    secondLevelObserver = new LevelObserver(secondGameScreenController, level,
        timelineForPlayerTwo);
    levelObservable.addObserver(firstLevelObserver);
    levelObservable.addObserver(secondLevelObserver);
    firstGameScreenController.setLevelObservable(levelObservable);
    secondGameScreenController.setLevelObservable(levelObservable);
    setStageTitle();
    setUpBackgroundSound();
    setUpGameOverObservable();
    firstGameScreenController.setTimeLine(timelineForPlayerOne);
    secondGameScreenController.setTimeLine(timelineForPlayerTwo);
    gameOver = false;
    view.getRootPane().getChildren().add(firstGameScreenController.drawGameScreen());
    timerLabel = new TimerLabel();
    currentTime = "00:00";
    view.getRootPane().getChildren().add(timerLabel.drawTimer(currentTime));
    view.getRootPane().getChildren().add(secondGameScreenController.drawGameScreen());

    timelineForTimer = new Timeline(new KeyFrame(
        Duration.seconds(1),
        ae -> showTimer()));
    timelineForTimer.setCycleCount(Timeline.INDEFINITE);
    timelineForTimer.play();

  }

  /**
   * Show Timer for View in the Game.
   */
  private void showTimer() {
    seconds++;
    if (seconds == 60) {
      seconds = 0;
      minutes += 1;
    }
    if (minutes == 60) {
      minutes = 0;
      hours++;
    }
    if (hours > 1) {
      currentTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    } else {
      currentTime = String.format("%02d:%02d", minutes, seconds);
    }
    view.getRootPane().getChildren().remove(1);
    view.getRootPane().getChildren().add(1, timerLabel.drawTimer(currentTime));
  }

  /**
   * Set Up the Observables and Observers for GameOver.
   */
  private void setUpGameOverObservable() {
    gameOverObservable = new GameOverObservable();
    firstGameScreenController.setGameOverObservable(gameOverObservable);
    secondGameScreenController.setGameOverObservable(gameOverObservable);
    firstGameOverObserver = new GameOverObserver(this, firstGameScreenController);
    secondGameOverObserver = new GameOverObserver(this, secondGameScreenController);
    gameOverObservable.addObserver(firstGameOverObserver);
    gameOverObservable.addObserver(secondGameOverObserver);
  }

  /**
   * Start the BackGround Sound.
   */
  private void setUpBackgroundSound() {
    tetrisSound = new TetrisSound();
    tetrisSound.startBackGroundSound();
  }

  /**
   * Set Up the Observables and Observers for Punish.
   */
  private void setUpPunishObservables() {
    tetrisObservableOne = new TetrisObservable();
    tetrisObservableTwo = new TetrisObservable();
    tetrisObserverOne = new TetrisObserver(secondGameScreenController);
    tetrisObserverTwo = new TetrisObserver(firstGameScreenController);
    tetrisObservableOne.addObserver(tetrisObserverOne);
    tetrisObservableTwo.addObserver(tetrisObserverTwo);
    firstGameScreenController.setTetrisObservable(tetrisObservableOne);
    secondGameScreenController.setTetrisObservable(tetrisObservableTwo);
  }

  /**
   * Restart games.
   */
  public void restart() {
    view.getRootPane().getChildren().clear();
    tetrisSound.stopBackGroundSound();
    seconds = 0;
    minutes = 0;
    hours = 0;
    run();
  }

  /**
   * Returns the Current Tetris Sound.
   *
   * @return TetrisSound.
   */
  public TetrisSound getTetrisSound() {
    return tetrisSound;
  }

  /**
   * Set GameOver Status.
   */
  public void setGameOver() {
    gameOver = true;
  }

  /**
   * Returns is Game Over.
   *
   * @return Boolean.
   */
  public Boolean getGameOver() {
    return gameOver;
  }
}
