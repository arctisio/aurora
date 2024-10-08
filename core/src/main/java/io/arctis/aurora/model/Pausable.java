package io.arctis.aurora.model;

/**
 * @author Brissach
 * @since 17.05.2023 01:09
 * © Aurora - All Rights Reserved
 */
public interface Pausable {

  void pause();

  void resume();

  boolean paused();

}
