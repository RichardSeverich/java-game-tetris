package org.fundacionjala.at04.teamtetris.models.tetromino.creators;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.ZTetromino;

/**
 * Created by SergioNavarro on 7/17/2017.
 */
public class CreatorZTetromino implements TetrominoCreator {
  /**
   * Overrride Create Tetromino.
   *
   * @return TetrominoModel.
   */
  @Override
  public TetrominoModel createTetromino() {
    return new ZTetromino();
  }
}

