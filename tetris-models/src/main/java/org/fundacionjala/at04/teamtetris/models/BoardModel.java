package org.fundacionjala.at04.teamtetris.models;

import java.util.List;

/**
 * Created by AbelBarrientos on 6/27/2017.
 */
public interface BoardModel {

  List<TileModel> getBoard();

  Integer getRows();

  Integer getColumns();

  boolean checkCollision(List<TileModel> tileModels, Integer col, Integer row);

  boolean checkOutOfBorder(List<TileModel> tileModels, Integer col, Integer row);

  void mergeToBoard(List<TileModel> tileModels);

  boolean createCurrentTetrominoShape();

  List<TileModel> getCurrentTetrominoShape();

  boolean moveCurrentTetrominoShapeLeft();

  boolean moveCurrentTetrominoShapeRight();

  boolean moveCurrentTetrominoShapeDown();

  void moveCurrentTetrominoShapeToBottom();

  List<List<TileModel>> getNextFiveTetromino();

  boolean rowIsComplete(Integer row);

  void calculateScore();

  Score getScore();

  void cleanCompleteRow(Integer row);

  List<TileModel> getOverBricksState(Integer row);

  void moveOverBricksDown(List<TileModel> overBricks);

  boolean rotateCurrentTetrominoShapeToLeft();

  List<TileModel> generatePunishRow();

  void punishPlayer();

  Integer totalCleanedRows();

  void setCurrentLevel(Level level);

  Level getCurrentLevel();
}
