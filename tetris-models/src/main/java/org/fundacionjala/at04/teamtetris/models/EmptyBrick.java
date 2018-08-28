package org.fundacionjala.at04.teamtetris.models;

import javafx.scene.paint.Color;

/**
 * Created by abelb on 7/13/2017.
 */
public class EmptyBrick implements Brick {

  private Color color;

  /**
   * Default Constructor.
   */
  public EmptyBrick() {
    color = Color.TRANSPARENT;
  }

  /**
   * Get Color.
   * @return Color FX.
   */
  @Override
  public Color getColor() {
    return color;
  }

  /**
   * Set Color.
   * @param color Color FX.
   */
  @Override
  public void setColor(Color color) {

  }
}
