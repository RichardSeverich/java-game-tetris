package org.fundacionjala.at04.teamtetris.models;

/**
 * Created by abelb on 6/26/2017.
 */
public class StageModelImpl implements StageModel {

  private String title;

  /**
   * Default Contructor.
   * @param title String.
   */
  public StageModelImpl(String title) {
    this.title = title;
  }

  /**
   * Override Interface get Title.
   * @return String.
   */
  @Override
  public String getTitle() {
    return title;
  }
}
