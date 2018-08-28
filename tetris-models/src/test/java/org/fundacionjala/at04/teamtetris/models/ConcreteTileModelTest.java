package org.fundacionjala.at04.teamtetris.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javafx.scene.paint.Color;
import org.junit.Test;

/**
 * Created by abelb on 7/10/2017.
 */
public class ConcreteTileModelTest {

  /**
   * Test Brick is not Empty.
   */
  @Test
  public void testSetBrick_WhenFreeSlot() {
    TileModel model = new ConcreteTileModel();

    Brick brick = model.setBrick(new ConcreteBrick(Color.RED));

    assertNotNull(brick);
  }

  /**
   * Test Setting Position.
   */
  @Test
  public void testSetPosition_InitialPosition() {
    TileModel model = new ConcreteTileModel();

    Integer row = 1;
    Integer col = 1;
    IPosition position = new BrickPosition(row, col);
    model.setPosition(position);

    assertEquals(row, model.getRow());
    assertEquals(col, model.getCol());
  }

  /**
   * Test Changing Color.
   */
  @Test
  public void testSetColor_ChangeInitialColor() {
    Brick brick = new ConcreteBrick(Color.RED);
    Color anotherColor = Color.BLUE;
    brick.setColor(anotherColor);

    assertEquals(anotherColor, brick.getColor());
  }
}
