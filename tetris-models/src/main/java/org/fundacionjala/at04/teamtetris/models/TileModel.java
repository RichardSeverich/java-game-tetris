package org.fundacionjala.at04.teamtetris.models;

import javafx.scene.paint.Color;

/**
 * Created by abelb on 7/10/2017.
 */
public interface TileModel {

  Brick setBrick(Brick brick);

  Brick getBrick();

  void setPosition(IPosition position);

  Integer getRow();

  Integer getCol();

  void setColor(Color backgroundColor);

  Color getColor();
}
