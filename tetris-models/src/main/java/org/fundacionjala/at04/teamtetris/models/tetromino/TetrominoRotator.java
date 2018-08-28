package org.fundacionjala.at04.teamtetris.models.tetromino;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;

/**
 * Created by abelb on 7/18/2017.
 */
public class TetrominoRotator implements Rotator {

  private Integer currentShape;
  private TetrominoModel tetrominoModel;

  @Override
  public void setCurrentShape(Integer currentShape) {
    this.currentShape = currentShape;
  }

  @Override
  public Integer getCurrentShape() {
    return currentShape;
  }

  @Override
  public Integer getNextShape() {
    return ++currentShape % tetrominoModel.getTetrominoShape().size();
  }

  @Override
  public void setTetromino(TetrominoModel tetrominoModel) {
    this.tetrominoModel = tetrominoModel;
  }

  @Override
  public TetrominoModel getTetromino() {
    return tetrominoModel;
  }
}
