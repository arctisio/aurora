package io.arctis.aurora_deep.loss;

/**
 * @author Brissach
 * @since 22.05.2024 21:45
 * © Aurora - All Rights Reserved
 */
public interface LossFunction {

  /**
   * Computes the loss value between the predicted and actual values.
   *
   * @param predicted The predicted values.
   * @param actual The actual values.
   * @return The computed loss.
   */
  double computeLoss(double[] predicted, double[] actual);

  /**
   * Computes the gradient of the loss with respect to the predicted values.
   *
   * @param predicted The predicted values.
   * @param actual The actual values.
   * @return The gradient of the loss.
   */
  double[] computeGradient(double[] predicted, double[] actual);
}