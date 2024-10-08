package io.arctis.aurora_deep.loss;

/**
 * @author Brissach
 * @since 22.05.2024 21:46
 * © Aurora - All Rights Reserved
 */
public class MeanSquaredError implements LossFunction {

  @Override
  public double computeLoss(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) throw new IllegalArgumentException("The length of predicted and actual arrays must be the same.");
    double sum = 0.0;
    for (int i = 0; i < predicted.length; i++) {
      double diff = predicted[i] - actual[i];
      sum += Math.pow(diff, 2);
    }
    return sum / predicted.length;
  }

  @Override
  public double[] computeGradient(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) throw new IllegalArgumentException("The length of predicted and actual arrays must be the same.");
    double[] gradient = new double[predicted.length];
    for (int i = 0; i < predicted.length; i++)
      gradient[i] =
        2 * (predicted[i] - actual[i]) / predicted.length;

    return gradient;
  }
}