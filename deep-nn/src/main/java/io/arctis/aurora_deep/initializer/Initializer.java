package io.arctis.aurora_deep.initializer;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 24.04.2024 19:14
 * © Aurora - All Rights Reserved
 */
public enum Initializer {
  GAUSSIAN(new GaussianInitializer()),
  LOW_GAUSSIAN(new LowGaussianInitializer()),
  HE(new HeInitializer()),
  UNIFORM(new UniformInitializer(0.1, 0.9)),
  XAVIER(new XavierInitializer());

  private final WeightInitializer initializer;

  Initializer(@Nonnull WeightInitializer initializer) {
    this.initializer = initializer;
  }

  @Nonnull
  public WeightInitializer initializer() {
    return initializer;
  }
}
