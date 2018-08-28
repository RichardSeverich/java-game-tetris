package org.fundacionjala.at04.teamtetris.models.tetromino.creators;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;

/**
 * Created by SergioNavarro on 7/17/2017.
 */
public class CreatorTTetromino implements TetrominoCreator {
  /**
   * Overrride Create Tetromino.
   *
   * @return TetrominoModel.
   */
  @Override
  public TetrominoModel createTetromino() {
    return new TTetromino();
  }
}

