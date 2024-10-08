package io.arctis.aurora_deep;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 04.12.2023 14:26
 * © Aurora - All Rights Reserved
 */
public interface Layer {

  double[] activate(double[] input);

  @Nonnull
  Neuron[] neurons();

}
