package org.fundacionjala.at04.teamtetris.controllers.observer;

import java.util.Observable;

import org.fundacionjala.at04.teamtetris.models.Level;

/**
 * Created by abelb on 7/23/2017.
 */
public class LevelObservable extends Observable {

  private Level level;

  /**
   * Default Constructor.
   */
  public LevelObservable(Level level) {
    super();
    this.level = level;
  }

  /**
   * Fires the punish event.
   */
  public void increaseLevel() {
    level.increaseLevel();
    setChanged();
    notifyObservers();
  }
}
