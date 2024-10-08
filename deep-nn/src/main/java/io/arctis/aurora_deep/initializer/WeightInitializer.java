package io.arctis.aurora_deep.initializer;

/**
 * @author Brissach
 * @since 24.04.2024 19:09
 * © Aurora - All Rights Reserved
 */
public interface WeightInitializer {
  ComposedWeights initialize(
    long seed,
    int inputSize
  );
}
