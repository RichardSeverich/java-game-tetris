package org.fundacionjala.at04.teamtetris.models.tetromino.creators;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;

/**
 * Created by SergioNavarro on 7/17/2017.
 */
public interface TetrominoCreator {
  /**
   * Create Tetromino of interface.
   *
   * @return TetrominoModel.
   */
  TetrominoModel createTetromino();
}
