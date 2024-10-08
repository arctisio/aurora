package io.arctis.aurora.loss;

/**
 * @author Brissach
 * @since 15.10.2023 13:56
 * © Aurora - All Rights Reserved
 */
public class MSE implements LossFunction {
  @Override
  public double computeLoss(double[] predicted, double[] actual) {
    if (predicted.length != actual.length)
      throw new IllegalArgumentException("Input vectors must have the same length.");

    double loss = 0.0;
    for (int i = 0; i < predicted.length; i++) {
      double error = actual[i] - predicted[i];
      loss += error * error;
    }
    return loss / predicted.length;
  }

  @Override
  public double computeGradient(double[] predicted, double[] actual) {
    if (predicted.length != actual.length)
      throw new IllegalArgumentException("Input vectors must have the same length.");

    double gradient = 0.0;
    for (int i = 0; i < predicted.length; i++)
      gradient += 2 * (predicted[i] - actual[i]);

    return gradient / predicted.length;
  }
}
