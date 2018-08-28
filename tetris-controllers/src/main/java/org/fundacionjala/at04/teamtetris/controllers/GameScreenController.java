package org.fundacionjala.at04.teamtetris.controllers;

import java.util.List;
import java.util.stream.IntStream;

import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;

import org.fundacionjala.at04.teamtetris.controllers.gamesound.TetrisSound;
import org.fundacionjala.at04.teamtetris.controllers.observer.GameOverObservable;
import org.fundacionjala.at04.teamtetris.controllers.observer.LevelObservable;
import org.fundacionjala.at04.teamtetris.controllers.observer.TetrisObservable;
import org.fundacionjala.at04.teamtetris.models.BoardModel;
import org.fundacionjala.at04.teamtetris.models.Level;
import org.fundacionjala.at04.teamtetris.models.TileModel;
import org.fundacionjala.at04.teamtetris.views.gamescreen.GameScreenMainView;
import org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.gameover.GameOverLoser;
import org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.gameover.GameOverWinner;
import org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.gameover.IGameOver;


/**
 * Created by AbelBarrientos on 6/27/2017.
 */
public class GameScreenController {

  private static final int LEVEL_LINES = 20;
  private BoardModel model;
  private GameScreenMainView view;
  private Integer rowsCounter;
  private TetrisObservable tetrisObservable;
  private GameOverObservable gameOverObservable;
  private Boolean isGameOver;
  private Boolean canMove;
  private Timeline timeLine;
  private LevelObservable levelObservable;

  /**
   * Default Constructor
   *
   * @param model BoardModel.
   * @param view  BoardView.
   */
  public GameScreenController(BoardModel model, GameScreenMainView view) {
    this.model = model;
    this.view = view;
    rowsCounter = 0;
    isGameOver = false;
    canMove = true;
  }

  /**
   * Draw GameScreen.
   *
   * @return BorderPane.
   */
  public BorderPane drawGameScreen() {
    newCurrentTetromino();
    return view.drawGameScreenView();
  }

  /**
   * Create a new Tetromino.
   */
  private void newCurrentTetromino() {
    if (model.createCurrentTetrominoShape()) {
      reDraw();
    } else {
      isGameOver = true;
      gameOverObservable.isGameOver();
    }
  }

  /**
   * Can't move after GameOver.
   */
  public void setCanMove() {
    canMove = false;
  }

  /**
   * Set the Tetris Observable for Punish.
   *
   * @param tetrisObservable TetrisObservable.
   */
  public void setTetrisObservable(TetrisObservable tetrisObservable) {
    this.tetrisObservable = tetrisObservable;
  }

  /**
   * Set the Tetris Observable for Game Over.
   *
   * @param gameOverObservable GameOverObservable.
   */
  public void setGameOverObservable(GameOverObservable gameOverObservable) {
    this.gameOverObservable = gameOverObservable;
  }

  /**
   * Set the timeline for movements.
   *
   * @param timeLine TimeLine.
   */
  public void setTimeLine(Timeline timeLine) {
    this.timeLine = timeLine;
  }

  /**
   * Returns the current TimeLine.
   *
   * @return Timeline.
   */
  public Timeline getTimeLine() {
    return timeLine;
  }

  /**
   * Move Current Tetromino To Left.
   */
  public void moveLeft() {
    if (model.moveCurrentTetrominoShapeLeft() && canMove) {
      reDraw();
    }
  }

  /**
   * Move Current Tetromino To Right.
   */
  public void moveRight() {
    if (model.moveCurrentTetrominoShapeRight() && canMove) {
      reDraw();
    }
  }

  /**
   * Move Current Tetromino To Down.
   */
  public void moveDown() {
    if (canMove) {
      if (model.moveCurrentTetrominoShapeDown()) {
        reDraw();
      } else {
        model.mergeToBoard(model.getCurrentTetrominoShape());
        checkCompleteLines();
        newCurrentTetromino();
      }
    }
  }

  /**
   * Move Current Tetromino To Bottom.
   */
  public void moveBottom() {
    if (canMove) {
      model.moveCurrentTetrominoShapeToBottom();
      model.mergeToBoard(model.getCurrentTetrominoShape());
      checkCompleteLines();
      newCurrentTetromino();
    }
  }

  /**
   * Verify whenever a line is complete.
   */
  private void checkCompleteLines() {
    model.calculateScore();
    rowsCounter = 0;
    IntStream.range(0, 20)
        .forEach(n -> {
          if (model.rowIsComplete(n)) {
            model.cleanCompleteRow(n);
            rowsCounter++;
            List<TileModel> demo = model.getOverBricksState(n);
            model.moveOverBricksDown(demo);
            model.mergeToBoard(demo);
          }
        });
    checkTetris();
    if (model.totalCleanedRows() >= LEVEL_LINES * (model.getCurrentLevel().currentLevel() + 1)) {
      levelObservable.increaseLevel();
    }
  }

  /**
   * Punish with a row to the other player and plays a sound.
   */
  private void checkTetris() {
    if (rowsCounter == 4) {
      tetrisObservable.punish();
      TetrisSound tetrisSound = new TetrisSound();
      tetrisSound.playPunishSound();
    }
  }

  /**
   * Redraw current Board.
   */
  public void reDraw() {
    view.drawNextPanel(model.getNextFiveTetromino());
    view.drawCenterBoard(model);
    view.drawScorePanel(model.getScore().getTotalScore().toString());
    view.drawLevelPanel(model.getCurrentLevel().currentLevel().toString());
  }

  /**
   * Rotates to the left a tetromino.
   */
  public void rotate() {
    if (canMove) {
      model.rotateCurrentTetrominoShapeToLeft();
      reDraw();
    }
  }

  /**
   * Draw the punish.
   */
  public void drawPunish() {
    model.punishPlayer();
  }

  /**
   * Draw Game Over Status Winner or Loser.
   */
  public void drawGameOver() {
    if (isGameOver) {
      IGameOver loser = new GameOverLoser("Loser");
      view.getTetrisBoardView().getTetrisPane().getChildren().add(loser.showGameOverLabel());
    } else {
      IGameOver winner = new GameOverWinner("Winner");
      view.getTetrisBoardView().getTetrisPane().getChildren().add(winner.showGameOverLabel());
    }
  }

  /**
   * Set level for the game.
   *
   * @param level Level.
   */
  public void setLevel(Level level) {
    model.setCurrentLevel(level);
  }

  /**
   * Set the Level Observable.
   *
   * @param levelObservable LevelObservable.
   */
  public void setLevelObservable(LevelObservable levelObservable) {
    this.levelObservable = levelObservable;
  }
}
