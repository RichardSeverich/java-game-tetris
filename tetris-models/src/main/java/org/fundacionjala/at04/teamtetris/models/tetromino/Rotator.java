package org.fundacionjala.at04.teamtetris.models.tetromino;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;

/**
 * Created by abelb on 7/18/2017.
 */
public interface Rotator {

  void setCurrentShape(Integer currentShape);

  Integer getCurrentShape();

  Integer getNextShape();

  void setTetromino(TetrominoModel tetrominoModel);

  TetrominoModel getTetromino();
}
