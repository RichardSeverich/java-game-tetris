package org.fundacionjala.at04.teamtetris.models.tetromino;

import org.fundacionjala.at04.teamtetris.models.tetromino.creators.TetrominoCreator;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;


/**
 * Created by SergioNavarro on 7/18/2017.
 */

/**
 * Tetrominos Manager.
 */
public class TetrominosManager {
  
  private TetrominoCreator creator;
  
  public void setCreator(TetrominoCreator creator) {
    this.creator = creator;
  }
  
  public TetrominoModel createTetrominoModel() {
    return creator.createTetromino();
  }
}
