package io.arctis.aurora.logistic;

import gg.acai.acava.annotated.Optionally;
import io.arctis.aurora.model.ActivationFunction;
import io.arctis.aurora.model.MLContext;
import io.arctis.aurora.model.MLContextProvider;
import io.arctis.aurora.model.Predictable;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * An abstract implementation of a logistic regression
 *
 * @author Brissach
 * @since 01.04.2023 15:38
 * © Aurora - All Rights Reserved
 */
public abstract class AbstractLogisticRegression implements MLContextProvider, Predictable {

  @Optionally
  protected String name;
  protected ActivationFunction activation;

  protected final double[] weights;
  protected final double[] biases;

  public AbstractLogisticRegression(double[] weights, double[] biases) {
    this.weights = weights;
    this.biases = biases;
  }

  public AbstractLogisticRegression(int inputSize, int outputSize) {
    this(new double[inputSize], new double[outputSize]);
  }

  public void setActivationFunction(ActivationFunction activation) {
    this.activation = activation;
  }

  @Override
  public double[] predict(double[] input) {
    double[] prediction = new double[biases.length];
    for (int i = 0; i < prediction.length; i++) {
      double sum = 0;
      for (int j = 0; j < input.length; j++) {
        sum += input[j] * weights[j];
      }
      sum += biases[i];
      prediction[i] = activation.apply(sum);
    }
    return prediction;
  }

  @Override
  public MLContext context() {
    return MLContext.LOGISTIC_REGRESSION;
  }

  @Nonnull
  public double[] weights() {
    return weights;
  }

  @Nonnull
  public double[] biases() {
    return biases;
  }

  @Override
  public String toString() {
    return "AbstractLogisticRegression{" +
      "name='" + name + '\'' +
      ", activation=" + activation +
      ", weights=" + Arrays.toString(weights) +
      ", biases=" + Arrays.toString(biases) +
      '}';
  }
}
