package org.fundacionjala.at04.teamtetris.models;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.scene.paint.Color;
import org.fundacionjala.at04.teamtetris.models.tetromino.RandomTetrominoGenerator;
import org.fundacionjala.at04.teamtetris.models.tetromino.Rotator;
import org.fundacionjala.at04.teamtetris.models.tetromino.TetrominoGenerator;
import org.fundacionjala.at04.teamtetris.models.tetromino.TetrominoRotator;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;



/**
 * Created by AbelBarrientos on 6/27/2017.
 */
public class BoardModelImpl implements BoardModel {

  private final int rows;
  private final int columns;
  private List<TileModel> board;
  private TetrominoGenerator tetrominoGenerator;
  private List<TileModel> currentTetrominoShape;
  private List<Rotator> rotatorList;
  private IPosition currentTetrominoPosition;
  private Score brickScore;
  private Integer currentLevel;
  private Level level;
  private Rotator rotator;
  private List<TileModel> punishRow;
  private Integer totalCleanedRows;


  /**
   * Default Constructor.
   *
   * @param rows    Integer.
   * @param columns Integer.
   */
  public BoardModelImpl(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    initBoardMatrix();
    brickScore = new BrickScore();
    currentLevel = 0;
    totalCleanedRows = 0;
    tetrominoGenerator = new RandomTetrominoGenerator();
    rotator = new TetrominoRotator();
    rotatorList = new LinkedList<>();
    IntStream.range(0, 20)
        .forEach(n -> addElementsToQueue());
    punishRow = new LinkedList<>();
  }

  /**
   * Initial Board Matrix.
   */
  private void initBoardMatrix() {
    board = new LinkedList<>();
    Brick emptyBrick = new EmptyBrick();
    IntStream.range(0, rows)
        .forEach(i -> IntStream.range(0, columns)
            .forEach(j -> {
              IPosition position = new BrickPosition(i, j);
              TileModel tile = new ConcreteTileModel(emptyBrick, position);
              board.add(tile);
            }));
  }

  /**
   * Return the Board matrix.
   *
   * @return TileModel List.
   */
  @Override
  public List<TileModel> getBoard() {
    return board;
  }

  /**
   * Get Rows.
   *
   * @return Integer.
   */
  @Override
  public Integer getRows() {
    return rows;
  }

  /**
   * Get Columns.
   *
   * @return Integer.
   */
  @Override
  public Integer getColumns() {
    return columns;
  }

  /**
   * Check if Tiles has any Collision.
   *
   * @param tileModels List.
   * @param col        Integer.
   * @param row        Integer.
   * @return Boolean.
   */
  @Override
  public boolean checkCollision(List<TileModel> tileModels, Integer col, Integer row) {
    for (TileModel tetromino : tileModels) {
      Integer index = columns * (tetromino.getRow() + row) + (tetromino.getCol() + col);
      if (index >= rows * columns || index < 0) {
        return true;
      }
      TileModel boardTile = board.get(index);
      if (boardTile.getBrick() instanceof ConcreteBrick) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if Tiles are Out of Border.
   *
   * @param tileModels List.
   * @param col        Integer.
   * @param row        Integer.
   * @return Boolean.
   */
  @Override
  public boolean checkOutOfBorder(List<TileModel> tileModels, Integer col, Integer row) {
    for (TileModel tetromino : tileModels) {
      Integer newColumn = tetromino.getCol() + col;
      Integer newRow = tetromino.getRow() + row;
      boolean checkColumns = (newColumn < 0 || newColumn > columns - 1);
      boolean checkRows = newRow > rows - 1;
      if (checkColumns || checkRows) {
        return true;
      }
    }
    return false;
  }

  /**
   * Merge a Tile into the Board.
   *
   * @param tileModels List.
   */
  @Override
  public void mergeToBoard(List<TileModel> tileModels) {
    tileModels.forEach(tetromino -> {
      TileModel boardTile = board.get(columns * tetromino.getRow() + tetromino.getCol());
      boardTile.setBrick(tetromino.getBrick());
      boardTile.setColor(tetromino.getBrick().getColor());
    });
  }

  /**
   * Create a random Tetromino.
   *
   * @return Boolean.
   */
  @Override
  public boolean createCurrentTetrominoShape() {
    List<TileModel> tetrominoClone = getTetrominoClone();
    if (!checkCollision(tetrominoClone, currentTetrominoPosition.getCol(),
        currentTetrominoPosition.getRow())) {
      currentTetrominoShape = tetrominoClone;
      currentTetrominoShape.forEach(t -> t.setPosition(new BrickPosition(t.getRow(),
          t.getCol() + 4)));
      displayCurrentTetromino(currentTetrominoShape);
      return true;
    }
    return false;
  }

  /**
   * Clone a Tetromino.
   *
   * @return List.
   */
  private List<TileModel> getTetrominoClone() {
    Rotator rotatorZero = rotatorList.get(0);
    List<TileModel> tetrominoShape = rotatorZero.getTetromino().getTetrominoShape()
        .get(rotatorZero.getCurrentShape());
    currentTetrominoPosition = new BrickPosition(0, 4);
    List<TileModel> tetrominoClone = new LinkedList<>();
    tetrominoShape.forEach(tile -> tetrominoClone.add(deepCopy(tile)));
    this.rotator.setCurrentShape(rotatorZero.getCurrentShape());
    this.rotator.setTetromino(rotatorZero.getTetromino());
    rotatorList.remove(0);
    addElementsToQueue();
    return tetrominoClone;
  }

  /**
   * Add Elements to the Rotator List,
   * Containing Tetromino Shape
   * and Index.
   */
  private void addElementsToQueue() {
    TetrominoModel allTetrominos = tetrominoGenerator.getTetromino();
    List<List<TileModel>> allTetrominosShape = allTetrominos.getTetrominoShape();
    Integer currentShape = ThreadLocalRandom.current().nextInt(allTetrominosShape.size());
    Rotator rotator = new TetrominoRotator();
    rotator.setCurrentShape(currentShape);
    rotator.setTetromino(allTetrominos);
    rotatorList.add(rotator);
  }

  /**
   * Copy Tile.
   *
   * @param tile TileModel.
   * @return TileModel.
   */
  private TileModel deepCopy(TileModel tile) {
    TileModel copy = new ConcreteTileModel();
    copy.setColor(tile.getColor());
    copy.setPosition(new BrickPosition(tile.getRow(), tile.getCol()));
    copy.setBrick(tile.getBrick());
    return copy;
  }

  /**
   * Return Current Tetromino.
   *
   * @return List of TileModels.
   */
  @Override
  public List<TileModel> getCurrentTetrominoShape() {
    return currentTetrominoShape;
  }

  /**
   * Move Current Tetromino to Left.
   *
   * @return Boolean.
   */
  @Override
  public boolean moveCurrentTetrominoShapeLeft() {
    Integer left = -Math.abs(1);
    return canMove(left, 0);
  }

  /**
   * Current Tetromino to Right.
   *
   * @return Boolean.
   */
  @Override
  public boolean moveCurrentTetrominoShapeRight() {
    Integer right = Math.abs(1);
    return canMove(right, 0);
  }

  /**
   * Move Current Tetromino Down.
   *
   * @return Boolean.
   */
  @Override
  public boolean moveCurrentTetrominoShapeDown() {
    Integer down = Math.abs(1);
    return canMove(0, down);
  }

  /**
   * Move Current Tetromino To The Bottom.
   */
  @Override
  public void moveCurrentTetrominoShapeToBottom() {
    Integer maxTetrominoRow = currentTetrominoShape.stream()
        .max(Comparator.comparingInt(TileModel::getRow))
        .get().getRow();

    IntStream.range(0, rows - maxTetrominoRow - 1)
        .forEach(row -> moveCurrentTetrominoShapeDown());
  }

  /**
   * Tetromino can Move in Horizontal.
   *
   * @param vertical Side quantity.
   * @return Boolean.
   */
  private boolean canMove(Integer vertical, Integer horizontal) {
    Integer currentRow = currentTetrominoPosition.getRow();
    Integer currentCol = currentTetrominoPosition.getCol();
    if (!checkCollision(currentTetrominoShape, vertical, horizontal)
        && !checkOutOfBorder(currentTetrominoShape, vertical, horizontal)) {
      currentTetrominoPosition = new BrickPosition(currentTetrominoPosition.getRow() + horizontal,
          currentTetrominoPosition.getCol() + vertical);
      currentTetrominoShape.forEach(tetromino -> {
        Integer tetrominoRow = tetromino.getRow();
        Integer tetrominoCol = tetromino.getCol();
        tetromino.setPosition(new BrickPosition(tetrominoRow + horizontal,
            tetrominoCol + vertical));
        board.get(columns * tetrominoRow + tetrominoCol).setColor(Color.TRANSPARENT);
      });
      currentTetrominoPosition = new BrickPosition(currentRow + horizontal, currentCol + vertical);
      displayCurrentTetromino(currentTetrominoShape);
      return true;
    }
    return false;
  }

  /**
   * Return a List with Five next pieces.
   *
   * @return List.
   */
  @Override
  public List<List<TileModel>> getNextFiveTetromino() {
    List<List<TileModel>> topFive = new LinkedList<>();
    Rotator newRotatorZero = rotatorList.get(0);
    topFive.add(newRotatorZero.getTetromino().getTetrominoShape()
        .get(newRotatorZero.getCurrentShape()));
    Rotator newRotatorOne = rotatorList.get(1);
    topFive.add(newRotatorOne.getTetromino().getTetrominoShape()
        .get(newRotatorOne.getCurrentShape()));
    Rotator newRotatorTwo = rotatorList.get(2);
    topFive.add(newRotatorTwo.getTetromino().getTetrominoShape()
        .get(newRotatorTwo.getCurrentShape()));
    Rotator newRotatorThree = rotatorList.get(3);
    topFive.add(newRotatorThree.getTetromino().getTetrominoShape()
        .get(newRotatorThree.getCurrentShape()));
    Rotator newRotatorFour = rotatorList.get(4);
    topFive.add(newRotatorFour.getTetromino().getTetrominoShape()
        .get(newRotatorFour.getCurrentShape()));
    return topFive;
  }

  /**
   * Display the Current Tetromino on Board.
   *
   * @param tileModels List of TileModels.
   */
  private void displayCurrentTetromino(List<TileModel> tileModels) {
    tileModels.forEach(tile -> {
      Integer currentRow = tile.getRow();
      Integer currentCol = tile.getCol();
      TileModel newTile = board.get(columns * currentRow + currentCol);
      newTile.setColor(tile.getColor());
    });
  }

  /**
   * Verify rows complete by index.
   *
   * @param row Integer.
   * @return Boolean.
   */
  @Override
  public boolean rowIsComplete(Integer row) {
    return board.stream()
        .filter(tile -> row == tile.getRow())
        .allMatch(tileModel -> tileModel.getBrick() instanceof ConcreteBrick);
  }

  /**
   * Calculate Scores.
   */
  @Override
  public void calculateScore() {
    Integer completedRows = (int) IntStream.range(0, rows)
        .filter(this::rowIsComplete)
        .count();
    if (completedRows > 0) {
      brickScore.calculateCurrentScore(currentLevel, completedRows);
    }
  }

  /**
   * Return Score.
   *
   * @return Score.
   */
  @Override
  public Score getScore() {
    return brickScore;
  }

  /**
   * Clean Complete Rows.
   *
   * @param row Integer.
   */
  @Override
  public void cleanCompleteRow(Integer row) {
    board.stream()
        .filter(t -> row == t.getRow())
        .forEach(t -> t.setBrick(new EmptyBrick()));
    totalCleanedRows++;
  }


  /**
   * Capture the states of over bricks.
   *
   * @param row Integer.
   * @return list titleModel.
   */
  @Override
  public List<TileModel> getOverBricksState(Integer row) {

    List<TileModel> overBricks = board.stream()
        .filter(t -> t.getRow() < row && t.getBrick() instanceof ConcreteBrick)
        .collect(Collectors.toList());
    List<TileModel> overBricksClone = new LinkedList<>();
    overBricks.forEach(t -> overBricksClone.add(deepCopy(t)));
    board.stream()
        .filter(t -> row >= t.getRow())
        .forEach(t -> {
          t.setBrick(new EmptyBrick());
          t.setColor(Color.TRANSPARENT);
        });
    return overBricksClone;
  }

  /**
   * Move Over Bricks.
   *
   * @param overBricks list of tileModel.
   */
  @Override
  public void moveOverBricksDown(List<TileModel> overBricks) {
    overBricks.forEach(t -> t.setPosition(new BrickPosition(t.getRow() + 1, t.getCol())));
  }

  /**
   * Move all bricks up for the punish.
   * @param overBricks List of TileModels.
   */
  private void moveOverBricksUp(List<TileModel> overBricks) {
    overBricks.forEach(t -> t.setPosition(new BrickPosition(t.getRow() - 1, t.getCol())));
  }

  /**
   * This method rotates the Current Tetromino to the Left.
   * Returns True if can or False if not.
   *
   * @return Boolean.
   */
  @Override
  public boolean rotateCurrentTetrominoShapeToLeft() {
    List<TileModel> tetrominoClone = new LinkedList<>();
    Integer currentShape = rotator.getCurrentShape();
    Integer indexNextShape = rotator.getNextShape();
    rotator.getTetromino().getTetrominoShape().get(indexNextShape).forEach(t ->
        tetrominoClone.add(deepCopy(t)));
    if (!checkCollision(tetrominoClone, currentTetrominoPosition.getCol(),
        currentTetrominoPosition.getRow()) && !checkOutOfBorder(tetrominoClone,
        currentTetrominoPosition.getCol(), currentTetrominoPosition.getRow())) {
      cleanCurrentTetromino(currentTetrominoShape);
      currentTetrominoShape = tetrominoClone;
      currentTetrominoShape.forEach(t -> t.setPosition(new BrickPosition(t.getRow()
          + currentTetrominoPosition.getRow(),
          t.getCol() + currentTetrominoPosition.getCol())));
      displayCurrentTetromino(currentTetrominoShape);
      return true;
    }
    rotator.setCurrentShape(currentShape);
    return false;
  }

  /**
   * Cleans the Current Tetromino from board.
   *
   * @param currentTetrominoShape List.
   */
  private void cleanCurrentTetromino(List<TileModel> currentTetrominoShape) {
    currentTetrominoShape.forEach(t -> {
      Integer currentRow = t.getRow();
      Integer currentCol = t.getCol();
      TileModel tile = board.get(columns * currentRow + currentCol);
      tile.setColor(Color.TRANSPARENT);
    });
  }

  /**
   * Generates a row to punish a player.
   * @return List of Tile Models.
   */
  @Override
  public List<TileModel> generatePunishRow() {
    punishRow.clear();
    Brick grayBrick = new ConcreteBrick(Color.GRAY);
    IntStream.range(0, columns)
        .forEach(n -> {
          IPosition position = new BrickPosition(rows - 1, n);
          TileModel tile = new ConcreteTileModel(grayBrick, position);
          punishRow.add(tile);
        });
    Random randomColumn = new Random();
    punishRow.remove(randomColumn.nextInt(columns - 1));
    return punishRow;
  }

  /**
   * Punish other player with a row and push up other rows.
   */
  @Override
  public void punishPlayer() {
    generatePunishRow();
    List<TileModel> overBricks = getOverBricksState(rows);
    mergeToBoard(punishRow);
    moveOverBricksUp(overBricks);
    mergeToBoard(overBricks);
  }

  /**
   * Returns Total Cleaned Rows.
   * @return Integer.
   */
  @Override
  public Integer totalCleanedRows() {
    return totalCleanedRows;
  }

  /**
   * Set the current Level.
   * @param level Level.
   */
  @Override
  public void setCurrentLevel(Level level) {
    this.level = level;
    this.currentLevel = level.currentLevel();
  }

  /**
   * Returns the current level.
   * @return Level.
   */
  @Override
  public Level getCurrentLevel() {
    return level;
  }
}
