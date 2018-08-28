package org.fundacionjala.at04.teamtetris.models.tetromino;

import java.util.List;

import org.fundacionjala.at04.teamtetris.models.tetromino.products.TetrominoModel;


/**
 * Created by Abel on 02/07/2017.
 */
public interface TetrominoGenerator {

  TetrominoModel getTetromino();

  List<TetrominoModel> getTetrominoGeneratorList();
}
