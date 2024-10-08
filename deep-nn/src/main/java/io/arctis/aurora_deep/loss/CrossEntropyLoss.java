package io.arctis.aurora_deep.loss;

/**
 * @author Brissach
 * @since 22.05.2024 21:51
 * © Aurora - All Rights Reserved
 */
public class CrossEntropyLoss implements LossFunction {

  @Override
  public double computeLoss(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) {
      throw new IllegalArgumentException("The length of predicted and actual arrays must be the same.");
    }
    double sum = 0.0;
    for (int i = 0; i < predicted.length; i++) {
      sum += actual[i] * Math.log(predicted[i]) + (1 - actual[i]) * Math.log(1 - predicted[i]);
    }
    return -sum / predicted.length;
  }

  @Override
  public double[] computeGradient(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) {
      throw new IllegalArgumentException("The length of predicted and actual arrays must be the same.");
    }
    double[] gradient = new double[predicted.length];
    for (int i = 0; i < predicted.length; i++) {
      gradient[i] = (predicted[i] - actual[i]) / (predicted[i] * (1 - predicted[i]));
    }
    return gradient;
  }
}