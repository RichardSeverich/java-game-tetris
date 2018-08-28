package org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import org.fundacionjala.at04.teamtetris.models.BoardModel;
import org.fundacionjala.at04.teamtetris.models.TileModel;


/**
 * Created by AbelBarrientos on 6/27/2017.
 */
public class TetrisBoardView extends Pane implements TetrisBoard {

  private static final Color LINE_COLOR = Color.web("#44c3f4");
  public static final int ROWS = 20;
  public static final int COLUMNS = 10;
  private int squareSize;
  private int width;
  private int height;

  private List<Rectangle> view;

  /**
   * Default Constructor.
   *
   * @param squareSize Integer.
   */
  public TetrisBoardView(int squareSize) {
    this.squareSize = squareSize;
    this.width = squareSize * COLUMNS;
    this.height = squareSize * ROWS;
    setMinWidth(width);
    setMaxHeight(height);
    setPadding(new Insets(10, 10, 10, 10));
    setBorder(new Border(new BorderStroke(LINE_COLOR, BorderStrokeStyle.SOLID,
        CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    view = new LinkedList<>();
  }

  /**
   * Get the Tetris Board.
   *
   * @return TetrisBoardView.
   */
  @Override
  public TetrisBoardView getTetrisPane() {
    return this;
  }

  /**
   * Draw the Tetris Board.
   */
  @Override
  public void drawTetrisBoard(BoardModel boardView) {
    getChildren().clear();
    view.clear();
    for (TileModel tile : boardView.getBoard()) {
      Rectangle rect = new Rectangle(squareSize, squareSize);
      rect.setStroke(LINE_COLOR);
      rect.setTranslateX(width / COLUMNS * tile.getCol());
      rect.setTranslateY(height / ROWS * tile.getRow());
      rect.setFill(tile.getColor());
      getChildren().add(rect);
      view.add(rect);
    }
  }
}
