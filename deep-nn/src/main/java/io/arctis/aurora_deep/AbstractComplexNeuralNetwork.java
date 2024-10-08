package io.arctis.aurora_deep;

import gg.acai.acava.annotated.Optionally;
import io.arctis.aurora.model.MLContext;
import io.arctis.aurora.model.MLContextProvider;
import io.arctis.aurora.model.ParameterContext;
import io.arctis.aurora.model.Predictable;
import io.arctis.aurora_deep.initializer.WeightInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brissach
 * @since 24.04.2024 19:23
 * © Aurora - All Rights Reserved
 */
public class AbstractComplexNeuralNetwork implements Predictable, MLContextProvider, ParameterContext {

  @Optionally
  protected String name;
  protected final List<Layer> layers;
  protected WeightInitializer initializer;
  protected long seed;
  protected boolean outputNormalization;

  public AbstractComplexNeuralNetwork(List<Layer> layers) {
    this.layers = layers;
  }

  public AbstractComplexNeuralNetwork(ComplexNeuralNetworkBuilder builder) {
    layers = new ArrayList<>();
    initializer = builder.initializer;
    outputNormalization = builder.outputNormalization;
    seed = builder.seed;
    int prevSize = builder.inputSize;
    for (BuildLayer layer : builder.layers) {
      int size = layer.size();
      layers.add(new LayerImpl(
        prevSize,
        size,
        layer.activation(),
        initializer,
        seed
      ));

      prevSize = size;
    }

    BuildLayer output = builder.outputLayer;
    layers.add(new LayerImpl(
      prevSize,
      output.size(),
      output.activation(),
      initializer,
      seed
    ));
  }

  @Override
  public double[] predict(double[] input) {
    double[] activations = input;
    for (Layer layer : layers)
      activations = layer.activate(activations);
    return activations;
  }

  @Override
  public MLContext context() {
    return MLContext.NEURAL_NETWORK;
  }

  @Override
  public int countParameters() {
    int parameters = 0;
    for (Layer layer : layers) {
      for (Neuron neuron : layer.neurons())
        parameters += neuron.weights().length + 1; // 1 for bias
    }
    return parameters;
  }
}
