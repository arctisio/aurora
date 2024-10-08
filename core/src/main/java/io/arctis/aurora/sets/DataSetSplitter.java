package io.arctis.aurora.sets;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Brissach
 * @since 06.06.2024 10:14
 * © Aurora - All Rights Reserved
 */
public class DataSetSplitter {

  private final double splitRatio;
  private final long seed;

  public DataSetSplitter(double splitRatio, long seed) {
    Preconditions.checkArgument(
      splitRatio >= 0 && splitRatio <= 1,
      "Split ratio must be between 0 and 1."
    );
    this.splitRatio = splitRatio;
    this.seed = seed;
  }

  public DataSetSplitter(double splitRatio) {
    this(
      splitRatio,
      System.currentTimeMillis()
    );
  }

  public DataSplit split(double[][] inputs, double[][] outputs) {
    Preconditions.checkArgument(
      inputs.length == outputs.length,
      "Inputs and outputs must have the same number of samples."
    );

    int totalSamples = inputs.length;
    int trainSize = (int) (totalSamples * splitRatio);
    int validationSize = totalSamples - trainSize;

    List<Integer> indices = new ArrayList<>();
    for (int i = 0; i < totalSamples; i++)
      indices.add(i);

    Random random = new Random(seed);
    Collections.shuffle(indices, random);

    double[][] trainX = new double[trainSize][];
    double[][] trainY = new double[trainSize][];
    double[][] valX = new double[validationSize][];
    double[][] valY = new double[validationSize][];

    for (int i = 0; i < trainSize; i++) {
      trainX[i] = inputs[indices.get(i)];
      trainY[i] = outputs[indices.get(i)];
    }

    for (int i = 0; i < validationSize; i++) {
      valX[i] = inputs[indices.get(trainSize + i)];
      valY[i] = outputs[indices.get(trainSize + i)];
    }

    return DataSplit.of(trainX, trainY, valX, valY);
  }
}