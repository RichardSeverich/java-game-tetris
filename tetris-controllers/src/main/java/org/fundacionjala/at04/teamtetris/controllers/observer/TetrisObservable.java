package org.fundacionjala.at04.teamtetris.controllers.observer;

import java.util.Observable;

/**
 * Created by JoseTorrez on 7/21/2017.
 */
public class TetrisObservable extends Observable {

  /**
   * Default Constructor.
   */
  public TetrisObservable() {
    super();
  }

  /**
   * Fires the punish event.
   */
  public void punish() {
    setChanged();
    notifyObservers();
  }
}
