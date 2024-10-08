package io.arctis.aurora_deep;

import io.arctis.aurora.model.ActivationFunction;
import io.arctis.aurora_deep.initializer.WeightInitializer;

import javax.annotation.Nonnull;

/**
 * @author Brissach
 * @since 04.12.2023 11:20
 * © Aurora - All Rights Reserved
 */
public class LayerImpl implements Layer {

  protected final Neuron[] neurons;

  public LayerImpl(int inputSize, int neuronCount, ActivationFunction activation, WeightInitializer initializer, long seed) {
    neurons = new NeuronImpl[neuronCount];
    for (int i = 0; i < neuronCount; i++)
      neurons[i] = new NeuronImpl(
        inputSize,
        activation,
        initializer,
        seed
      );
  }

  @Override
  public double[] activate(double[] input) {
    double[] activations = new double[neurons.length];
    for (int i = 0; i < neurons.length; i++)
      activations[i] = neurons[i].activate(input);
    return activations;
  }

  @Override @Nonnull
  public Neuron[] neurons() {
    return neurons;
  }
}