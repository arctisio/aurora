package io.arctis.aurora_deep.initializer;

import java.util.Random;

/**
 * @author Brissach
 * @since 24.04.2024 19:13
 * © Aurora - All Rights Reserved
 */
public class HeInitializer implements WeightInitializer {
  @Override
  public ComposedWeights initialize(long seed, int inputSize) {
    Random random = new Random(seed);
    double stdDeviation = Math.sqrt(2.0 / inputSize);
    double[] weights = new double[inputSize];
    for (int i = 0; i < inputSize; i++)
      weights[i] = stdDeviation * random.nextGaussian();
    double bias = stdDeviation * random.nextGaussian();
    return ComposedWeights.of(weights, bias);
  }
}
