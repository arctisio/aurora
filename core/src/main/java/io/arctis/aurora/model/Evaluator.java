package io.arctis.aurora.model;

/**
 * A single evaluator metric provider.
 *
 * @author Brissach
 * @since 09.04.2023 20:28
 * © Aurora - All Rights Reserved
 */
@FunctionalInterface
public interface Evaluator {
  /**
   * Evaluates the model.
   *
   * @return Returns the evaluation score.
   */
  double evaluate();
}
