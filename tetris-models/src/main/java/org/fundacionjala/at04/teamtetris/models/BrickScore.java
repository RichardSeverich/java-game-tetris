package org.fundacionjala.at04.teamtetris.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by OvidioMiranda on 7/17/2017.
 */
public class BrickScore implements Score {

  private Integer currentScore;
  private Integer totalScore;
  private static Map<Integer, Integer> linesValue = new HashMap<Integer, Integer>() {
    {
      put(1, 40);
      put(2, 100);
      put(3, 300);
      put(4, 1200);
    }
  };

  /**
   * Default constructor.
   */
  public BrickScore() {
    currentScore = 0;
    totalScore = 0;
  }

  /**
   * Returns Current Score.
   * @return Integer.
   */
  @Override
  public Integer getCurrentScore() {
    return currentScore;
  }

  /**
   * Calculates Scores.
   * @param level Integer.
   * @param line Integer.
   */
  @Override
  public void calculateCurrentScore(Integer level, Integer line) {
    currentScore = linesValue.get(line) * (level + 1);
    totalScore += currentScore;
  }

  /**
   * Returns Total Score.
   * @return Integer.
   */
  @Override
  public Integer getTotalScore() {
    return totalScore;
  }
}

