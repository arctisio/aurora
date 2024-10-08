package io.arctis.aurora_deep.initializer;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Brissach
 * @since 24.04.2024 19:07
 * © Aurora - All Rights Reserved
 */
public class ComposedWeights {

  private final double[] weights;
  private final double bias;

  @Nonnull
  public static ComposedWeights of(double[] weights, double bias) {
    return new ComposedWeights(
      weights,
      bias
    );
  }

  ComposedWeights(double[] weights, double bias) {
    this.weights = weights;
    this.bias = bias;
  }

  public double[] weights() {
    return weights;
  }

  public double bias() {
    return bias;
  }

  @Override
  public String toString() {
    return "ComposedWeights{" +
      "weights=" + Arrays.toString(weights) +
      ", bias=" + bias +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComposedWeights that = (ComposedWeights) o;
    return Double.compare(bias, that.bias) == 0 && Arrays.equals(weights, that.weights);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(bias);
    result = 31 * result + Arrays.hashCode(weights);
    return result;
  }
}
