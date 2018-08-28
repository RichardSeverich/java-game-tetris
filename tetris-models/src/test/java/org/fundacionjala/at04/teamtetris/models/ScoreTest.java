package org.fundacionjala.at04.teamtetris.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Created by OvidioMiranda on 7/17/2017.
 */
public class ScoreTest {
  @Test
  public void testInitScore_AllwaysInZero() {
    Score score = new BrickScore();

    Integer expectedResult = 0;

    assertEquals(expectedResult, score.getCurrentScore(), 0);
  }

  @Test
  public void testCurrentScore_LevelZeroAndOneRowClean() {
    Score score = new BrickScore();

    Integer level = 0;
    Integer line = 1;
    Integer expectedResult = 40;
    score.calculateCurrentScore(level, line);

    assertEquals(expectedResult, score.getCurrentScore());

  }

  @Test
  public void testCurrentScore_LevelZeroAndTwoRowClean() {
    Score score = new BrickScore();

    Integer level = 0;
    Integer line = 2;
    Integer expectedResult = 100;
    score.calculateCurrentScore(level, line);

    assertEquals(expectedResult, score.getCurrentScore());

  }

  @Test
  public void testCurrentScore_LevelTenAndFourRowClean() {
    Score score = new BrickScore();

    Integer level = 9;
    Integer line = 4;
    Integer expectedResult = 12000;
    score.calculateCurrentScore(level, line);

    assertEquals(expectedResult, score.getCurrentScore());
  }

  @Test
  public void testTotalScore_LevelZeroAndOneRowClean() {
    Score score = new BrickScore();

    Integer level = 0;
    Integer line = 1;
    Integer expectedResult = 40;
    score.calculateCurrentScore(level, line);

    assertEquals(expectedResult, score.getTotalScore());

  }

  @Test
  public void testTotalScore_LevelZeroAndTwoRowClean() {
    Score score = new BrickScore();

    Integer level = 0;
    Integer oneLine = 1;
    Integer twoLines = 2;
    Integer expectedResult = 140;
    score.calculateCurrentScore(level, oneLine);
    score.calculateCurrentScore(level, twoLines);

    assertEquals(expectedResult, score.getTotalScore());

  }
}

