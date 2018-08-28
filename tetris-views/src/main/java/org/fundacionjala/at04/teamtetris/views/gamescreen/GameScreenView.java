package org.fundacionjala.at04.teamtetris.views.gamescreen;

import java.util.Comparator;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;
import org.fundacionjala.at04.teamtetris.models.BoardModel;
import org.fundacionjala.at04.teamtetris.models.TileModel;
import org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.TetrisBoard;
import org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.TetrisBoardView;


/**
 * Created by AbelBarrientos on 6/27/2017.
 */
public class GameScreenView extends BorderPane implements GameScreenMainView {

  private VBox leftSidePane;
  private VBox rightSidePane;
  private TetrisBoard tetrisBoard;
  private List<List<TileModel>> nextTopFive;
  private VBox nextContent = new VBox(5);
  private Pane scorePane = new Pane();
  private Pane levelPane = new Pane();

  /**
   * Default Constructor.
   */
  public GameScreenView() {
    setPrefSize(500, 500);
    tetrisBoard = new TetrisBoardView(25);
    leftSidePane = new VBox(50);
    rightSidePane = new VBox(200);
    setLeft(leftSidePane);
    setRight(rightSidePane);
    setCenter(tetrisBoard.getTetrisPane());
  }

  /**
   * Draw the Tetris Board.
   */
  @Override
  public void drawCenterBoard(BoardModel board) {
    setMargin(tetrisBoard.getTetrisPane(), new Insets(5, 5, 5, 5));
    tetrisBoard.drawTetrisBoard(board);
  }

  /**
   * Draw Next Panel.
   * @param nextFiveTetrominos List.
   */
  @Override
  public void drawNextPanel(List<List<TileModel>> nextFiveTetrominos) {
    nextTopFive = nextFiveTetrominos;
    nextContent.getChildren().clear();
    getNextPane();
  }

  /**
   * VBox of Next Pane.
   * @return VBox.
   */
  private void getNextPane() {
    Integer rectSize = 20;
    nextContent.setMinHeight(100);
    for (List<TileModel> tileList : nextTopFive) {
      Pane pane = new Pane();
      Integer tetrominoHeight = tileList.stream()
          .max(Comparator.comparingInt(TileModel::getRow))
          .get().getRow() + 1;
      pane.setMinHeight(tetrominoHeight * rectSize);
      for (TileModel tile : tileList) {
        Rectangle rect = new Rectangle(rectSize, rectSize);
        rect.setStroke(Color.web("#44c3f4"));
        rect.setTranslateX(rectSize * tile.getCol());
        rect.setTranslateY(rectSize * tile.getRow());
        rect.setFill(tile.getColor());
        pane.getChildren().add(rect);
      }
      nextContent.getChildren().add(pane);
    }
  }

  /**
   * Draw Right Side Panel.
   */
  public void drawRightBoard() {
    rightSidePane.setMinWidth(120);
    rightSidePane.setMinHeight(500);
    IPanelView panelNext = new PanelView("Next");
    panelNext.setPanelContent(nextContent);
    rightSidePane.getChildren().add(panelNext.getPane());
    IPanelView panelLinesSent = new PanelView("Lines Sent");
    Pane contentLines = new Pane();
    contentLines.getChildren().add(new Rectangle(100, 100, Color.WHITE));
    panelLinesSent.setPanelContent(contentLines);
    rightSidePane.getChildren().add(panelLinesSent.getPane());
  }

  /**
   * Draw Left Side Panel.
   */
  public void drawLeftBoard() {
    leftSidePane.setMinWidth(120);
    leftSidePane.setMinHeight(500);
    IPanelView panelHold = new PanelView("Hold");
    Pane contentHold = new Pane();
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        Rectangle rect = new Rectangle(25, 25);
        rect.setStroke(Color.web("#44c3f4"));
        rect.setTranslateX(25 * j);
        rect.setTranslateY(25 * i);
        rect.setFill(Color.WHITE);
        contentHold.getChildren().add(rect);
      }
    }
    panelHold.setPanelContent(contentHold);
    leftSidePane.getChildren().add(panelHold.getPane());
    IPanelView panelScore = new PanelView("Score");
    panelScore.setPanelContent(scorePane);
    leftSidePane.getChildren().add(panelScore.getPane());
    IPanelView panelLevel = new PanelView("Level");
    panelLevel.setPanelContent(levelPane);
    leftSidePane.getChildren().add(panelLevel.getPane());
  }

  /**
   * Return the Game Screen View.
   *
   * @return BorderPane.
   */
  @Override
  public GameScreenView drawGameScreenView() {
    drawRightBoard();
    drawLeftBoard();
    return this;
  }

  /**
   * Draw Score in the panel.
   * @param score Score.
   */
  @Override
  public void drawScorePanel(String score) {
    scorePane.getChildren().clear();
    getScorePane(score);
  }

  /**
   * Draw Level in the panel.
   * @param level Level.
   */
  @Override
  public void drawLevelPanel(String level) {
    levelPane.getChildren().clear();
    getLevelPane(level);
  }

  /**
   * Get Score for Panel
   * @param score Score.
   */
  private void getScorePane(String score) {
    Label labelScore = new Label(score);
    labelScore.setMinHeight(50);
    labelScore.setMinWidth(100);
    labelScore.setTextFill(Color.WHITE);
    labelScore.setFont(Font.font("Verdana", 24));
    labelScore.setAlignment(Pos.CENTER);
    scorePane.getChildren().add(labelScore);
  }

  /**
   * Get Level for Panel
   * @param level Level.
   */
  private void getLevelPane(String level) {
    Label labelScore = new Label(level);
    labelScore.setMinHeight(50);
    labelScore.setMinWidth(100);
    labelScore.setTextFill(Color.WHITE);
    labelScore.setFont(Font.font("Verdana", 24));
    labelScore.setAlignment(Pos.CENTER);
    levelPane.getChildren().add(labelScore);
  }

  /**
   * Return Tetris Board.
   * @return TetrisBoard.
   */
  @Override
  public TetrisBoard getTetrisBoardView() {
    return tetrisBoard;
  }
}
