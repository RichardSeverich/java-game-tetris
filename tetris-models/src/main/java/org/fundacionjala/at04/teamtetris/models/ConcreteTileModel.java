package org.fundacionjala.at04.teamtetris.models;

import javafx.scene.paint.Color;

/**
 * Created by abelb on 7/10/2017.
 */
public class ConcreteTileModel implements TileModel {

  private Brick brick;
  private IPosition position;
  private Color backgroundColor;

  @Override
  public Color getColor() {
    return backgroundColor;
  }

  /**
   * Default Constructor.
   */
  public ConcreteTileModel() {
  }

  /**
   * Constructor with Brick and Position.
   *
   * @param brick    Brick.
   * @param position IPosition.
   */
  public ConcreteTileModel(Brick brick, IPosition position) {
    this.brick = brick;
    this.position = position;
    this.backgroundColor = brick.getColor();
  }

  /**
   * Return Brick.
   *
   * @return Brick.
   */
  @Override
  public Brick getBrick() {
    return brick;
  }

  /**
   * Set Brick.
   *
   * @param brick Brick.
   * @return Brick.
   */
  @Override
  public Brick setBrick(Brick brick) {
    return this.brick = brick;
  }

  /**
   * Return Tile Row.
   *
   * @return Integer.
   */
  @Override
  public Integer getRow() {
    return position.getRow();
  }

  /**
   * Set Tile Color.
   *
   * @param backgroundColor Color FX.
   */
  @Override
  public void setColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * Return Tile Col.
   *
   * @return Integer.
   */
  @Override
  public Integer getCol() {
    return position.getCol();
  }

  /**
   * Set Tile Position.
   *
   * @param position IPosition.
   */
  @Override
  public void setPosition(IPosition position) {
    this.position = position;
  }
}
