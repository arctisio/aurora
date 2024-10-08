package io.arctis.aurora.loss;

/**
 * @author Brissach
 * @since 15.10.2023 13:58
 * © Aurora - All Rights Reserved
 */
public class BinaryCrossEntropy implements LossFunction {
  @Override
  public double computeLoss(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) throw new IllegalArgumentException("Input vectors must have the same length.");

    double loss = 0.0;
    for (int i = 0; i < predicted.length; i++)
      loss += -(actual[i] * Math.log(predicted[i]) + (1 - actual[i]) * Math.log(1 - predicted[i]));

    return loss / predicted.length;
  }

  @Override
  public double computeGradient(double[] predicted, double[] actual) {
    if (predicted.length != actual.length) throw new IllegalArgumentException("Input vectors must have the same length.");

    double gradient = 0.0;
    for (int i = 0; i < predicted.length; i++)
      gradient += (predicted[i] - actual[i]) / (predicted[i] * (1 - predicted[i]));

    return gradient / predicted.length;
  }
}
