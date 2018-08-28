package org.fundacionjala.at04.teamtetris.controllers.observer;

import java.util.Observable;
import java.util.Observer;

import org.fundacionjala.at04.teamtetris.controllers.GameScreenController;

/**
 * Created by JoseTorrez on 7/21/2017.
 */
public class TetrisObserver implements Observer {

  private GameScreenController gameScreenController;

  /**
   * Constructor for the GameScreenController.
   * @param gameScreenController game Screen Controller.
   */
  public TetrisObserver(GameScreenController gameScreenController) {
    super();
    this.gameScreenController = gameScreenController;
  }

  /**
   * Method to send tha punishment.
   * @param o Observable.
   * @param arg Object.
   */
  @Override
  public void update(Observable o, Object arg) {
    gameScreenController.drawPunish();
  }
}
