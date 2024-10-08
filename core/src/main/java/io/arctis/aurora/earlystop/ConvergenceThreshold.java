package io.arctis.aurora.earlystop;

import gg.acai.acava.commons.Attributes;

/**
 * @author Brissach
 * @since 13.05.2024 15:41
 * © Aurora - All Rights Reserved
 */
public class ConvergenceThreshold implements EarlyStop {

  public static Builder builder() {
    return new Builder();
  }

  private final double threshold;
  private final int buffers;
  private final int minEpochs;

  public ConvergenceThreshold(Builder builder) {
    threshold = builder.threshold;
    buffers = builder.buffers;
    minEpochs = builder.minEpochs;
  }

  private double prevLoss = Double.MAX_VALUE;
  private int epochsElapsed = 0;
  private boolean reached;
  private int reachedCount;

  @Override
  public void tick(Attributes attributes) {
    double loss = attributes.fold("loss")
      .map(d -> (double) d)
      .orElseThrow(() -> new RuntimeException(
        "ConvergenceThreshold is not applicable because the trainable model being used lacks the 'loss' attribute."
      ));

    reached = Math.abs(prevLoss - loss) < threshold;
    reachedCount += reached ? 1 : 0;
    prevLoss = loss;
    epochsElapsed++;
  }

  @Override
  public boolean terminable() {
    return reached && epochsElapsed >= minEpochs && reachedCount >= buffers;
  }

  @Override
  public String terminationMessage() {
    return "Convergence criteria met." + " Threshold: " + threshold +
      ", Min Epochs: " + minEpochs +
      ", Buffers: " + buffers +
      ", Epochs Elapsed: " + epochsElapsed;
  }

  public static class Builder {
    private int buffers = -1;
    private int minEpochs = -1;
    private double threshold = 0.0001;

    public Builder buffers(int buffers) {
      this.buffers = buffers;
      return this;
    }

    public Builder minEpochs(int minEpochs) {
      this.minEpochs = minEpochs;
      return this;
    }

    public Builder threshold(double threshold) {
      this.threshold = threshold;
      return this;
    }

    public ConvergenceThreshold build() {
      return new ConvergenceThreshold(this);
    }
  }

}
