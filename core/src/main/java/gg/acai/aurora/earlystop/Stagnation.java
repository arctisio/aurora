package gg.acai.aurora.earlystop;

import gg.acai.aurora.Attributes;

/**
 * @author Clouke
 * @since 23.04.2023 03:49
 * © Aurora - All Rights Reserved
 */
public class Stagnation extends AbstractEarlyStop {

  private final CycleBuffer buffer;
  private final double maxAccuracy;
  private double lastAccuracy;
  private boolean stagnated;

  public Stagnation(int maxCycles, double maxAccuracy) {
    this.buffer = new CycleBuffer(maxCycles);
    this.maxAccuracy = maxAccuracy;
  }

  public Stagnation(int maxCycles) {
    this(maxCycles, 80.0);
  }

  @Override
  public void tick(Attributes attributes) {
    double accuracy = attributes.get("accuracy");
    if (accuracy == lastAccuracy && accuracy < maxAccuracy)
      stagnated = buffer.reached();
    else
      buffer.wipe();
    lastAccuracy = accuracy;
  }

  @Override
  public boolean shouldStop() {
    return stagnated;
  }
}
