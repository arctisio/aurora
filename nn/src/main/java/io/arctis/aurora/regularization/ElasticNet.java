package io.arctis.aurora.regularization;

/**
 * <p>
 * Elastic Net Regularization - A regularization technique that combines both L1 (Lasso) and L2 (Ridge) regularization.
 *
 * @author Brissach
 * @since 23.11.2023 15:08
 * © Aurora - All Rights Reserved
 */
public class ElasticNet implements Regularization {

  private final double l1Rate;
  private final double l2Rate;

  public ElasticNet(double l1Rate, double l2Rate) {
    this.l1Rate = l1Rate;
    this.l2Rate = l2Rate;
  }

  @Override
  public void apply(double[][] weights_input_to_hidden, double[][] weights_hidden_to_output, double[] biases_hidden, double[] biases_output, double learningRate) {
    for (int j = 0; j < weights_input_to_hidden[0].length; j++) {
      for (int k = 0; k < weights_input_to_hidden.length; k++) {
        double l1Penalty = l1Rate * Math.signum(weights_input_to_hidden[k][j]);
        double l2Penalty = l2Rate * weights_input_to_hidden[k][j];
        weights_input_to_hidden[k][j] -= learningRate * (l1Penalty + l2Penalty);
      }
    }
    for (int j = 0; j < weights_hidden_to_output[0].length; j++) {
      for (int k = 0; k < weights_hidden_to_output.length; k++) {
        double l1Penalty = l1Rate * Math.signum(weights_hidden_to_output[k][j]);
        double l2Penalty = l2Rate * weights_hidden_to_output[k][j];
        weights_hidden_to_output[k][j] -= learningRate * (l1Penalty + l2Penalty);
      }
    }
  }
}
