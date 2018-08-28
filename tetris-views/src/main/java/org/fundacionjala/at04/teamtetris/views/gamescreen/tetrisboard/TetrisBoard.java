package org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard;

import org.fundacionjala.at04.teamtetris.models.BoardModel;

/**
 * Created by AbelBarrientos on 6/28/2017.
 */
public interface TetrisBoard {

  TetrisBoardView getTetrisPane();

  void drawTetrisBoard(BoardModel boardView);
}
