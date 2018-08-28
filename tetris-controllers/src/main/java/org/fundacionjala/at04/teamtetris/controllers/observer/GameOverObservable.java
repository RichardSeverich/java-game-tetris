package org.fundacionjala.at04.teamtetris.controllers.observer;

import java.util.Observable;

/**
 * Created by abelb on 7/23/2017.
 */
public class GameOverObservable extends Observable {

  /**
   * Default Constructor.
   */
  public GameOverObservable() {
    super();
  }

  /**
   * Fires the GameOver event.
   */
  public void isGameOver() {
    setChanged();
    notifyObservers();
  }
}
