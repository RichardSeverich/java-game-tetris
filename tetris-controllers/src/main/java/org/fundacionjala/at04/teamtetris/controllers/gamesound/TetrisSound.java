package org.fundacionjala.at04.teamtetris.controllers.gamesound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by JoseTorrez on 7/21/2017.
 */
public class TetrisSound {

  private MediaPlayer mediaPlayerBackground;

  /**
   * Default Constructor.
   */
  public TetrisSound() {
  }

  /**
   * This method starts playing the background music for the game.
   */
  public void startBackGroundSound() {
    try {
      String bip = "resources/background-song.mp3";
      Media hit = new Media(new File(bip).toURI().toString());
      mediaPlayerBackground = new MediaPlayer(hit);
      mediaPlayerBackground.play();
    } catch (Exception e) {
      System.out.println("No encontrado.");
    }
  }

  /**
   * Plays the background music for the game.
   */
  public void playBackGroundSound() {
    mediaPlayerBackground.play();
  }

  /**
   * Stops the background music.
   */
  public void stopBackGroundSound() {
    mediaPlayerBackground.stop();
  }

  /**
   * Pause the current background music.
   */
  public void pauseBackGroundSound() {
    mediaPlayerBackground.pause();
  }

  /**
   * This method plays a sound when a player make tetris.
   */
  public void playPunishSound() {
    try {
      String bip = "resources/punish-sound.mp3";
      Media hit = new Media(new File(bip).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
    } catch (Exception e) {
      System.out.println("No encontrado.");
    }
  }

  /**
   * This method plays a sound when a player is close to lose.
   */
  public void playDangerSound() {
    //TODO
    try {
      String bip = "resources/danger-sound.mp3";
      Media hit = new Media(new File(bip).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
    } catch (Exception e) {
      System.out.println("No encontrado.");
    }
  }

  /**
   * This method plays a sound when a player is close to lose.
   */
  public void playLevelUpSound() {
    try {
      String bip = "resources/level-up-sound.mp3";
      Media hit = new Media(new File(bip).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
    } catch (Exception e) {
      System.out.println("No encontrado.");
    }
  }
}
