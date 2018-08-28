package org.fundacionjala.at04.teamtetris.models;

import javafx.scene.paint.Color;

/**
 * Created by abelb on 7/10/2017.
 */
public class ConcreteBrick implements Brick {

  private Color color;

  /**
   * Default Constructor.
   * @param color Color FX.
   */
  public ConcreteBrick(Color color) {
    this.color = color;
  }

  /**
   * Return the Color of the Brick.
   * @return Color FX.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Set the Color of the Brick.
   * @param color Color FX.
   */
  @Override
  public void setColor(Color color) {
    this.color = color;
  }
}
