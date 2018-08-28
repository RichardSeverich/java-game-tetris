package org.fundacionjala.at04.teamtetris.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by abelb on 7/23/2017.
 */
public class LevelTest {

  /**
   * Test Increase Level.
   */
  @Test
  public void testIncreaseLevel_StartLevel() {
    Level level = new Level();

    Integer result = 0;

    assertEquals(result, level.currentLevel());
  }

  /**
   * Test Next level.
   */
  @Test
  public void testIncreaseLevel_NextLevel() {
    Level level = new Level();

    level.increaseLevel();
    Integer result = 1;

    assertEquals(result, level.currentLevel());
  }

  /**
   * Test Normal Speed.
   */
  @Test
  public void testCurrentSpeed_NormalSpeed() {
    Level level = new Level();

    Double result = 0.25;

    assertEquals(result, level.currentSpeed(), 0.0);
  }
}
