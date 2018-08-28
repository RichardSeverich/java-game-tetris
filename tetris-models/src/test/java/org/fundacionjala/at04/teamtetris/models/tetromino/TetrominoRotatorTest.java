package org.fundacionjala.at04.teamtetris.models.tetromino;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.ITetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.OTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;
import org.junit.Test;

/**
 * Created by abelb on 7/18/2017.
 */
public class TetrominoRotatorTest {

  /**
   * Test the set and get of Current Shape.
   */
  @Test
  public void testGetCurrentTetrominoShape_CurrentZero() {
    Rotator tetrominoRotator = new TetrominoRotator();
    Integer currentShape = 0;

    tetrominoRotator.setCurrentShape(currentShape);

    assertEquals(currentShape, tetrominoRotator.getCurrentShape());
  }

  /**
   * Test the next Shape for rotation.
   */
  @Test
  public void testGetNextShape_NextShapeOne() {
    Rotator tetrominoRotator = new TetrominoRotator();
    TetrominoModel tetromino = new ITetromino();
    Integer currentShape = 0;

    tetrominoRotator.setTetromino(tetromino);
    tetrominoRotator.setCurrentShape(currentShape);
    Integer nextShape = 1;

    assertEquals(nextShape, tetrominoRotator.getNextShape());
  }

  /**
   * Test that return 0 if cannot rotate.
   */
  @Test
  public void testSetCurrentTetromino_NoRotation() {
    Rotator tetrominoRotator = new TetrominoRotator();
    TetrominoModel tetromino = new OTetromino();
    Integer currentShape = 0;

    tetrominoRotator.setCurrentShape(currentShape);
    tetrominoRotator.setTetromino(tetromino);

    assertEquals(1, tetromino.getTetrominoShape().size(), 0);
    assertEquals(currentShape, tetrominoRotator.getCurrentShape());
  }

  /**
   * Test set and get of the Tetromino Model.
   */
  @Test
  public void testGetNextShape_NoRotation() {
    Rotator tetrominoRotator = new TetrominoRotator();
    TetrominoModel tetromino = new OTetromino();
    Integer currentShape = 0;

    tetrominoRotator.setCurrentShape(currentShape);
    tetrominoRotator.setTetromino(tetromino);

    assertTrue(tetromino.equals(tetrominoRotator.getTetromino()));
  }
}
