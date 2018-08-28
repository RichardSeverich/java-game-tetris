package org.fundacionjala.at04.teamtetris.models;

/**
 * Created by abelb on 7/23/2017.
 */
public class Level {

  private Integer currentLevel;
  private Double currentSpeed;

  /**
   * Default Constructor.
   */
  public Level() {
    currentLevel = 0;
    currentSpeed = 0.25;
  }

  /**
   * Returns current Level.
   *
   * @return Integer.
   */
  public Integer currentLevel() {
    return currentLevel;
  }

  /**
   * Increase Current level.
   */
  public void increaseLevel() {
    ++currentLevel;
  }

  /**
   * Returns Current Speed.
   *
   * @return Double.
   */
  public Double currentSpeed() {
    return currentSpeed;
  }
}
