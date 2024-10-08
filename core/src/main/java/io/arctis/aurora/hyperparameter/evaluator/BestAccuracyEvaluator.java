package io.arctis.aurora.hyperparameter.evaluator;

import io.arctis.aurora.hyperparameter.EvaluatorScoreContext;
import io.arctis.aurora.hyperparameter.TuningEvaluator;
import io.arctis.aurora.model.Trainable;

/**
 * A simple evaluator which returns the best accuracy.
 *
 * @author Brissach
 * @since 16.04.2023 21:22
 * © Aurora - All Rights Reserved
 */
public class BestAccuracyEvaluator implements TuningEvaluator {

  @Override
  public double evaluate(Trainable trainable, double[][] inputs, double[][] outputs) {
    trainable.train(inputs, outputs);
    return trainable.accuracy();
  }

  @Override
  public EvaluatorScoreContext context() {
    return EvaluatorScoreContext.HIGHEST;
  }
}
