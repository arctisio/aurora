package io.arctis.aurora_deep;

import io.arctis.aurora.model.ActivationFunction;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 04.12.2023 14:47
 * © Aurora - All Rights Reserved
 */
public class BuildLayer {

  private int size;
  private ActivationFunction activation;

  public BuildLayer size(int size) {
    this.size = size;
    return this;
  }

  public BuildLayer activation(@Nonnull ActivationFunction activation) {
    this.activation = activation;
    return this;
  }

  public int size() {
    return size;
  }

  public ActivationFunction activation() {
    return activation;
  }

}
