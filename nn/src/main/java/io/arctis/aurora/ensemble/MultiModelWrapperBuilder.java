package io.arctis.aurora.ensemble;

import io.arctis.aurora.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brissach
 * @since 10.09.2023 15:46
 * © Aurora - All Rights Reserved
 */
public class MultiModelWrapperBuilder {

  private boolean partitioning = false;
  private double partitionRate = 0.8;
  private final List<NeuralNetwork> networks = new ArrayList<>();

  public MultiModelWrapperBuilder partitioning(boolean partitioning) {
    this.partitioning = partitioning;
    return this;
  }

  public MultiModelWrapperBuilder partition() {
    return partitioning(true);
  }

  public MultiModelWrapperBuilder partitionRate(double partitionRate) {
    this.partitionRate = partitionRate;
    return this;
  }

  public MultiModelWrapperBuilder add(NeuralNetwork network) {
    networks.add(network);
    return this;
  }

  public MultiModelWrapperBuilder add(NeuralNetwork... networks) {
    for (NeuralNetwork network : networks) {
      add(network);
    }
    return this;
  }

  public MultiModelWrapperBuilder add(List<NeuralNetwork> networks) {
    this.networks.addAll(networks);
    return this;
  }

  public MultiModelWrapper build() {
    return new MultiModelWrapper(
      networks,
      partitioning,
      partitionRate
    );
  }

}
