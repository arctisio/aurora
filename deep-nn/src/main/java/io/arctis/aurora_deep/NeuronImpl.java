package io.arctis.aurora_deep;

import io.arctis.aurora.model.ActivationFunction;
import io.arctis.aurora_deep.initializer.ComposedWeights;
import io.arctis.aurora_deep.initializer.WeightInitializer;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 12.03.2024 18:27
 * © Aurora - All Rights Reserved
 */
public class NeuronImpl implements Neuron {

  protected final ActivationFunction activationFunction;
  protected double[] weights;
  protected double bias;
  protected double output;
  protected double delta; // error derivative

  public NeuronImpl(int inputSize, ActivationFunction activationFunction, WeightInitializer initializer, long seed) {
    this.activationFunction = activationFunction;
    ComposedWeights composer = initializer.initialize(seed, inputSize);
    weights = composer.weights();
    bias = composer.bias();
  }

  @Override @Nonnull
  public ActivationFunction activation() {
    return activationFunction;
  }

  @Override
  public double[] weights() {
    return weights;
  }

  @Override
  public double bias() {
    return bias;
  }

  @Override
  public double output() {
    return output;
  }

  @Override
  public double delta() {
    return delta;
  }

  @Override
  public void setDelta(double delta) {
    this.delta = delta;
  }

  @Override
  public void setBias(double bias) {
    this.bias = bias;
  }

  @Override
  public void setOutput(double output) {
    this.output = output;
  }

  @Override
  public double activate(double[] input) {
    double activation = bias;
    for (int i = 0; i < input.length; i++)
      activation += weights[i] * input[i];
    output = activationFunction.apply(activation);
    return output;
  }
}
