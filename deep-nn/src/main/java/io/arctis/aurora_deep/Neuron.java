package io.arctis.aurora_deep;

import io.arctis.aurora.model.ActivationFunction;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 04.12.2023 14:27
 * © Aurora - All Rights Reserved
 */
public interface Neuron {

  @Nonnull
  ActivationFunction activation();

  double[] weights();

  double bias();

  double output();

  double delta();

  void setDelta(double delta);

  void setBias(double bias);

  void setOutput(double output);

  double activate(double[] input);

}
