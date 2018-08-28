package org.fundacionjala.at04.teamtetris.views.gamescreen;

import java.util.List;

import org.fundacionjala.at04.teamtetris.models.BoardModel;
import org.fundacionjala.at04.teamtetris.models.TileModel;
import org.fundacionjala.at04.teamtetris.views.gamescreen.tetrisboard.TetrisBoard;

/**
 * Created by AbelBarrientos on 6/28/2017.
 */
public interface GameScreenMainView {

  GameScreenView drawGameScreenView();

  void drawCenterBoard(BoardModel board);

  void drawNextPanel(List<List<TileModel>> nextFiveTetrominos);

  void drawScorePanel(String score);

  TetrisBoard getTetrisBoardView();

  void drawLevelPanel(String level);
}
