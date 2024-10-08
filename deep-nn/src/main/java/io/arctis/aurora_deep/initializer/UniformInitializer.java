package io.arctis.aurora_deep.initializer;

import java.util.Random;

/**
 * @author Brissach
 * @since 24.04.2024 19:13
 * © Aurora - All Rights Reserved
 */
public class UniformInitializer implements WeightInitializer {
  private final double min;
  private final double max;

  public UniformInitializer(double min, double max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public ComposedWeights initialize(long seed, int inputSize) {
    Random random = new Random(seed);
    double[] weights = new double[inputSize];
    for (int i = 0; i < inputSize; i++)
      weights[i] = min + (max - min) * random.nextDouble();
    double bias = min + (max - min) * random.nextDouble();
    return ComposedWeights.of(weights, bias);
  }
}
