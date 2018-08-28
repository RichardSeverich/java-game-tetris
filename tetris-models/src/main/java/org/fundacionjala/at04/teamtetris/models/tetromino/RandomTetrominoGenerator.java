package org.fundacionjala.at04.teamtetris.models.tetromino;

import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorITetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorJTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorLTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorOTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorSTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorTTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.creators.CreatorZTetromino;
import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;



/**
 * Created by Abel on 02/07/2017.
 */
public class RandomTetrominoGenerator implements TetrominoGenerator {

  private List<TetrominoModel> tetrominosList;
  private TetrominosManager tetrominosManager;

  /**
   * Constructor for all Tetrominos.
   */
  public RandomTetrominoGenerator() {
    tetrominosList = new LinkedList<>();
    tetrominosManager = new TetrominosManager();
    tetrominosManager.setCreator(new CreatorITetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
    tetrominosManager.setCreator(new CreatorJTetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
    tetrominosManager.setCreator(new CreatorLTetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
    tetrominosManager.setCreator(new CreatorOTetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
    tetrominosManager.setCreator(new CreatorSTetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
    tetrominosManager.setCreator(new CreatorTTetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
    tetrominosManager.setCreator(new CreatorZTetromino());
    tetrominosList.add(tetrominosManager.createTetrominoModel());
  }

  /**
   * Get A Random Tetromino.
   *
   * @return Tetromino.
   */
  @Override
  public TetrominoModel getTetromino() {
    return tetrominosList.get(ThreadLocalRandom.current().nextInt(tetrominosList.size()));
  }

  @Override
  public List<TetrominoModel> getTetrominoGeneratorList() {
    return tetrominosList;
  }
}
