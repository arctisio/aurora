package io.arctis.aurora_deep.loss;

/**
 * @author Brissach
 * @since 22.05.2024 21:51
 * © Aurora - All Rights Reserved
 */
public class HingeLoss implements LossFunction {

  @Override
  public double computeLoss(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) {
      throw new IllegalArgumentException("The length of predicted and actual arrays must be the same.");
    }
    double sum = 0.0;
    for (int i = 0; i < predicted.length; i++) {
      sum += Math.max(0, 1 - actual[i] * predicted[i]);
    }
    return sum / predicted.length;
  }

  @Override
  public double[] computeGradient(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) {
      throw new IllegalArgumentException("The length of predicted and actual arrays must be the same.");
    }
    double[] gradient = new double[predicted.length];
    for (int i = 0; i < predicted.length; i++) {
      gradient[i] = (predicted[i] * actual[i] < 1) ? -actual[i] : 0;
    }
    return gradient;
  }
}