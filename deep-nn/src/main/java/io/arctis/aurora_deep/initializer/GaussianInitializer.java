package io.arctis.aurora_deep.initializer;

import java.util.Random;

/**
 * @author Brissach
 * @since 24.04.2024 19:12
 * © Aurora - All Rights Reserved
 */
public class GaussianInitializer implements WeightInitializer {
  @Override
  public ComposedWeights initialize(long seed, int inputSize) {
    Random random = new Random(seed);
    double[] weights = new double[inputSize];
    for (int i = 0; i < inputSize; i++)
      weights[i] = random.nextGaussian();
    double bias = random.nextGaussian();
    return ComposedWeights.of(weights, bias);
  }
}