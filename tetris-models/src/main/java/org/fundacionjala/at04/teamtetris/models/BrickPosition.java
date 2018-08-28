package org.fundacionjala.at04.teamtetris.models;

/**
 * Created by abelb on 7/10/2017.
 */
public class BrickPosition implements IPosition {

  private Integer row;
  private Integer col;

  /**
   * Default Constructor.
   * @param row Integer.
   * @param col Integer.
   */
  public BrickPosition(Integer row, Integer col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Return Row Position.
   * @return Integer.
   */
  @Override
  public Integer getRow() {
    return row;
  }

  /**
   * Return Col Position.
   * @return Integer.
   */
  @Override
  public Integer getCol() {
    return col;
  }
}
