package org.fundacionjala.at04.teamtetris.models;

/**
 * Created by OvidioMiranda on 7/17/2017.
 */
public interface Score {
  Integer getCurrentScore();

  void calculateCurrentScore(Integer level, Integer line);

  Integer getTotalScore();
}

