package org.fundacionjala.at04.teamtetris.models.tetromino.products;

import java.util.List;

import org.fundacionjala.at04.teamtetris.models.TileModel;

/**
 * Created by abelb on 6/25/2017.
 */
public interface TetrominoModel {

  List<List<TileModel>> getTetrominoShape();
}
