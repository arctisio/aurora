package io.arctis.aurora.loss;

/**
 * @author Brissach
 * @since 15.10.2023 13:54
 * © Aurora - All Rights Reserved
 */
public interface LossFunction {

  double computeLoss(double[] predicted, double[] actual);

  double computeGradient(double[] predicted, double[] actual);

}
