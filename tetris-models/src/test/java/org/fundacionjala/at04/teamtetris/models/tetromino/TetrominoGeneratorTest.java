package org.fundacionjala.at04.teamtetris.models.tetromino;

import static org.junit.Assert.assertEquals;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;
import org.junit.Test;

/**
 * Created by abelb on 7/11/2017.
 */
public class TetrominoGeneratorTest {

  /**
   * Test Tetromino Model Size.
   */
  @Test
  public void testTetrominoShape_GetSize() {
    TetrominoGenerator generator = new RandomTetrominoGenerator();

    TetrominoModel tetromino = generator.getTetromino();

    assertEquals(4, tetromino.getTetrominoShape().get(0).size());
  }
}
