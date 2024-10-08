package io.arctis.aurora_deep;

import io.arctis.aurora.policy.DecayPolicy;
import io.arctis.aurora_deep.initializer.Initializer;
import io.arctis.aurora_deep.initializer.WeightInitializer;
import io.arctis.aurora_deep.loss.LossFunction;
import io.arctis.aurora_deep.loss.LossFunctions;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Brissach
 * @since 04.12.2023 14:29
 * © Aurora - All Rights Reserved
 */
public class ComplexNeuralNetworkBuilder {

  protected int inputSize;
  protected List<BuildLayer> layers = new ArrayList<>();
  protected BuildLayer outputLayer;
  protected WeightInitializer initializer = Initializer.GAUSSIAN.initializer();
  protected DecayPolicy<Double> learningRateDecay;
  protected long seed = System.currentTimeMillis();
  protected LossFunction lossFunction = LossFunctions.MSE.lossFunction();
  protected double predefinedLearningRate;
  protected boolean outputNormalization = true;

  public ComplexNeuralNetworkBuilder inputSize(int inputSize) {
    this.inputSize = inputSize;
    return this;
  }

  public ComplexNeuralNetworkBuilder addLayer(BuildLayer layer) {
    layers.add(layer);
    return this;
  }

  public ComplexNeuralNetworkBuilder outputLayer(BuildLayer layer) {
    outputLayer = layer;
    return this;
  }

  public ComplexNeuralNetworkBuilder outputNormalization(boolean outputNormalization) {
    this.outputNormalization = outputNormalization;
    return this;
  }

  public ComplexNeuralNetworkBuilder initializer(WeightInitializer initializer) {
    this.initializer = initializer;
    return this;
  }

  public ComplexNeuralNetworkBuilder seed(long seed) {
    this.seed = seed;
    return this;
  }

  public ComplexNeuralNetworkBuilder lossFunction(LossFunction lossFunction) {
    this.lossFunction = lossFunction;
    return this;
  }

  public ComplexNeuralNetworkBuilder lossFunction(LossFunctions function) {
    this.lossFunction = function.lossFunction();
    return this;
  }

  public ComplexNeuralNetworkBuilder learningRateDecay(DecayPolicy<Double> policy) {
    learningRateDecay = policy;
    return this;
  }

  public ComplexNeuralNetworkBuilder learningRate(double predefinedLearningRate) {
    this.predefinedLearningRate = predefinedLearningRate;
    return this;
  }

  public ComplexNeuralNetworkBuilder initializer(Initializer initializer) {
    return initializer(initializer.initializer());
  }

  public ComplexNeuralNetwork build() {
    return new ComplexNeuralNetwork(this);
  }

}
