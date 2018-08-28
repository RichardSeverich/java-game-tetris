package org.fundacionjala.at04.teamtetris.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.ITetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.OTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.ZTetromino;
import org.junit.Test;


/**
 * Created by abelb on 7/11/2017.
 */
public class BoardModelTest {

  /**
   * Test Initial Board.
   */
  @Test
  public void testNewBoard_InitialBoard() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    int expectedResult = 200;

    assertEquals(expectedResult, boardModel.getBoard().size());
  }

  /**
   * Test Board Columns and Rows.
   */
  @Test
  public void testNewBoard_GetColsAndRows() {
    Integer rows = 20;
    Integer cols = 10;
    BoardModel boardModel = new BoardModelImpl(rows, cols);

    assertEquals(rows, boardModel.getRows());
    assertEquals(cols, boardModel.getColumns());
  }

  /**
   * Test Merge a Tetromino.
   */
  @Test
  public void testMerge_TetrominoToEmptyBoard() {
    BoardModel boardModel = new BoardModelImpl(4, 4);

    TetrominoModel tetromino = new OTetromino();
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(0));

    assertNotNull(boardModel.getBoard().get(0).getBrick());
    assertNotNull(boardModel.getBoard().get(1).getBrick());
    assertNotNull(boardModel.getBoard().get(4).getBrick());
    assertNotNull(boardModel.getBoard().get(5).getBrick());
  }

  /**
   * Test Collision with empty board.
   */
  @Test
  public void testCollision_WhenBoardIsNotEmpty() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetromino = new OTetromino();
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(0));

    TetrominoModel tetrominoOver = new ZTetromino();

    assertTrue(boardModel.checkCollision(tetrominoOver.getTetrominoShape().get(0), 0, 0));
  }

  /**
   * Test Collision with Board not empy.
   */
  @Test
  public void testCollision_WhenBoardIsEmpty() {
    BoardModel boardModel = new BoardModelImpl(4, 4);

    TetrominoModel tetromino = new OTetromino();

    assertFalse(boardModel.checkCollision(tetromino.getTetrominoShape().get(0), 3, 0));
  }

  /**
   * Test Tetromino is Out of Borders.
   */
  @Test
  public void testOutOfBorder_EmptyBoard() {
    BoardModel boardModel = new BoardModelImpl(4, 4);

    TetrominoModel tetromino = new OTetromino();

    assertTrue(boardModel.checkOutOfBorder(tetromino.getTetrominoShape().get(0), 0, 3));
  }

  /**
   * Test Tetromino is not Out of Borders.
   */
  @Test
  public void testOutOfBorder_EmptyBoardTwo() {
    BoardModel boardModel = new BoardModelImpl(4, 4);

    TetrominoModel tetromino = new OTetromino();

    assertFalse(boardModel.checkOutOfBorder(tetromino.getTetrominoShape().get(0), 0, 2));
  }

  /**
   * Test Can create a new Tetromino on Top Center.
   */
  @Test
  public void testCreateTetromino_EmptyBoard() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    assertTrue(boardModel.createCurrentTetrominoShape());
  }

  /**
   * Test Movement to Left.
   */
  @Test
  public void testMovement_MoveAPieceToLeft() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    boardModel.createCurrentTetrominoShape();

    assertTrue(boardModel.moveCurrentTetrominoShapeLeft());
  }

  /**
   * Test Movement to Left.
   */
  @Test
  public void testMovement_MoveAPieceToLeftWhenIsInBorder() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    boardModel.createCurrentTetrominoShape();
    boardModel.moveCurrentTetrominoShapeLeft();
    boardModel.moveCurrentTetrominoShapeLeft();
    boardModel.moveCurrentTetrominoShapeLeft();
    boardModel.moveCurrentTetrominoShapeLeft();

    assertFalse(boardModel.moveCurrentTetrominoShapeLeft());
  }


  /**
   * Test Movement to Right.
   */
  @Test
  public void testMovement_MoveAPieceToRight() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    boardModel.createCurrentTetrominoShape();

    assertTrue(boardModel.moveCurrentTetrominoShapeRight());
  }

  /**
   * Test Movement to Right.
   */
  @Test
  public void testMovement_MoveAPieceToRightWhenIsInBorder() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    boardModel.createCurrentTetrominoShape();
    boardModel.moveCurrentTetrominoShapeRight();
    boardModel.moveCurrentTetrominoShapeRight();
    boardModel.moveCurrentTetrominoShapeRight();
    boardModel.moveCurrentTetrominoShapeRight();
    boardModel.moveCurrentTetrominoShapeRight();

    assertFalse(boardModel.moveCurrentTetrominoShapeRight());
  }

  /**
   * Test Movement to Down.
   */
  @Test
  public void testMovement_MoveAPieceDown() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    boardModel.createCurrentTetrominoShape();

    assertTrue(boardModel.moveCurrentTetrominoShapeDown());
  }

  /**
   * Test Movement to Down.
   */
  @Test
  public void testMovement_MoveAPieceDownWhenIsInBorder() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    boardModel.createCurrentTetrominoShape();

    assertTrue(boardModel.moveCurrentTetrominoShapeDown());
  }

  /**
   * Test a row not complete.
   */
  @Test
  public void testRowComplete_ARowIsNotComplete() {
    BoardModel boardModel = new BoardModelImpl(20, 10);

    assertFalse(boardModel.rowIsComplete(3));
  }

  /**
   * Test a row complete.
   */
  @Test
  public void testRowComplete_ARowIsComplete() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetromino = new ITetromino();

    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));

    assertTrue(boardModel.rowIsComplete(0));
  }

  /**
   * Test that score increase when a row is complete.
   */
  @Test
  public void testRowCompleteAndScore_ARowIsComplete() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetromino = new ITetromino();
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    boardModel.calculateScore();
    Integer expectedCurrentScore = 40;
    Integer expectedTotalScore = 40;

    assertEquals(expectedCurrentScore, boardModel.getScore().getCurrentScore());
    assertEquals(expectedTotalScore, boardModel.getScore().getTotalScore());
  }

  /**
   * Test that score increase when two row is complete.
   */
  @Test
  public void testRowCompleteAndScore_TwoRowIsComplete() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetromino = new ITetromino();

    tetromino.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 3, t.getCol())));

    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    boardModel.calculateScore();
    tetromino.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() - 1, t.getCol())));
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    boardModel.calculateScore();
    Integer expectedTotalScore = 140;

    assertEquals(expectedTotalScore, boardModel.getScore().getTotalScore());
  }

  /**
   * Test to clean one row.
   */
  @Test
  public void testCleanRows_CleanOneRow() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetromino = new ITetromino();

    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    boardModel.calculateScore();
    boardModel.cleanCompleteRow(0);

    assertTrue(boardModel.getBoard().get(0).getBrick() instanceof EmptyBrick);
  }

  /**
   * Test maintain states bricks not complete.
   */
  @Test
  public void testMainteinState_InitialState() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetrominoOne = new ITetromino();
    TetrominoModel tetrominoTwo = new OTetromino();
    tetrominoOne.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 3, t.getCol())));
    boardModel.mergeToBoard(tetrominoOne.getTetrominoShape().get(1));
    tetrominoTwo.getTetrominoShape().get(0)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 1, t.getCol())));
    boardModel.mergeToBoard(tetrominoTwo.getTetrominoShape().get(0));
    boardModel.cleanCompleteRow(3);
    assertEquals(4, boardModel.getOverBricksState(3).size());
  }

  /**
   * Test down state.
   */
  @Test
  public void testDownState_FinalState() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetrominoOne = new ITetromino();
    TetrominoModel tetrominoTwo = new OTetromino();

    tetrominoOne.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 3, t.getCol())));
    boardModel.mergeToBoard(tetrominoOne.getTetrominoShape().get(1));

    tetrominoTwo.getTetrominoShape().get(0)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 1, t.getCol())));

    boardModel.mergeToBoard(tetrominoTwo.getTetrominoShape().get(0));
    boardModel.cleanCompleteRow(3);

    List<TileModel> tileModel = boardModel.getOverBricksState(3);
    boardModel.moveOverBricksDown(tileModel);
    assertFalse(boardModel.rowIsComplete(3));
  }

  /**
   * Test that new shape is drawn.
   */
  @Test
  public void testGetNextShape_NoRotation() {
    BoardModel boardModel = new BoardModelImpl(20, 10);
    boardModel.createCurrentTetrominoShape();

    assertTrue(boardModel.rotateCurrentTetrominoShapeToLeft());
  }

  /**
   * Test that punish a row.
   */
  @Test
  public void testGeneratePunishRow_LastRow() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    List<TileModel> list = boardModel.generatePunishRow();


    assertEquals(3, list.size());

  }

  /**
   * Test a player is punished.
   */
  @Test
  public void testPunishPlayer_AfterTetris() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    boardModel.generatePunishRow();
    TetrominoModel tetromino = new OTetromino();

    tetromino.getTetrominoShape().get(0)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 2, t.getCol())));

    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(0));

    boardModel.punishPlayer();

    assertEquals(7, boardModel.getOverBricksState(4).size());
  }

  /**
   * Test total Cleaned Rows.
   */
  @Test
  public void testTotalCleanedRows_After4Cleaned() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    TetrominoModel tetromino = new ITetromino();
    tetromino.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 1, t.getCol())));
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    tetromino.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 1, t.getCol())));
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    tetromino.getTetrominoShape().get(1)
        .forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 1, t.getCol())));
    boardModel.mergeToBoard(tetromino.getTetrominoShape().get(1));
    boardModel.cleanCompleteRow(1);
    boardModel.cleanCompleteRow(2);
    boardModel.cleanCompleteRow(3);
    Integer result = 3;
    assertEquals(result, boardModel.totalCleanedRows());
  }

  /**
   * Test get Current Level.
   */
  @Test
  public void testSetCurrentLevel_LevelOne() {
    BoardModel boardModel = new BoardModelImpl(4, 4);
    Level level = new Level();
    level.increaseLevel();
    boardModel.setCurrentLevel(level);
    Integer result = 1;

    assertEquals(result, boardModel.getCurrentLevel().currentLevel());
  }
}

