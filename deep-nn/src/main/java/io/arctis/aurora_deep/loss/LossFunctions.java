package io.arctis.aurora_deep.loss;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 22.05.2024 21:54
 * © Aurora - All Rights Reserved
 */
public enum LossFunctions {
  MSE(new MeanSquaredError()),
  HINGE(new HingeLoss()),
  CROSS_ENTROPY(new CrossEntropyLoss());

  private final LossFunction lossFunction;

  LossFunctions(LossFunction lossFunction) {
    this.lossFunction = lossFunction;
  }

  @Nonnull
  public LossFunction lossFunction() {
    return lossFunction;
  }

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
