package org.fundacionjala.at04.teamtetris.models.tetromino.products;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Color;

import org.fundacionjala.at04.teamtetris.models.Brick;
import org.fundacionjala.at04.teamtetris.models.BrickPosition;
import org.fundacionjala.at04.teamtetris.models.ConcreteBrick;
import org.fundacionjala.at04.teamtetris.models.ConcreteTileModel;
import org.fundacionjala.at04.teamtetris.models.TileModel;

/**
 * Created by abelb on 6/25/2017.
 */
public class ITetromino implements TetrominoModel {

  private static Brick brick = new ConcreteBrick(Color.CYAN);
  private final List<List<TileModel>> tetrominoShape = new LinkedList<>();

  /**
   * Constructor for I Tetromino.
   */
  public ITetromino() {
    tetrominoShape.add(Arrays.asList(
        new ConcreteTileModel(brick, new BrickPosition(0,0)),
        new ConcreteTileModel(brick, new BrickPosition(1,0)),
        new ConcreteTileModel(brick, new BrickPosition(2,0)),
        new ConcreteTileModel(brick, new BrickPosition(3,0))
    ));

    tetrominoShape.add(Arrays.asList(
        new ConcreteTileModel(brick, new BrickPosition(0,0)),
        new ConcreteTileModel(brick, new BrickPosition(0,1)),
        new ConcreteTileModel(brick, new BrickPosition(0,2)),
        new ConcreteTileModel(brick, new BrickPosition(0,3))
    ));
  }

  /**
   * All I Tetrominos form.
   * @return List of I tetrominos.
   */
  @Override
  public List<List<TileModel>> getTetrominoShape() {
    return tetrominoShape;
  }
}
