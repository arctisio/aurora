package io.arctis.aurora;

/**
 * @author Brissach
 * @since 08.08.2024 17:53
 * © Aurora - All Rights Reserved
 */
@FunctionalInterface
public interface ShapingFunction {
  ContextVector apply(
    Neuron neuron,
    int n,
    ContextVector w
  );
}

