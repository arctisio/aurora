package io.arctis.aurora.sets;

/**
 * @author Brissach
 * @since 06.06.2024 10:16
 * © Aurora - All Rights Reserved
 */
public class DataSplit {

  private final double[][] trainX;
  private final double[][] trainY;
  private final double[][] valX;
  private final double[][] valY;

  public static DataSplit of(double[][] trainX, double[][] trainY, double[][] valX, double[][] valY) {
    return new DataSplit(trainX, trainY, valX, valY);
  }

  DataSplit(double[][] trainX, double[][] trainY, double[][] valX, double[][] valY) {
    this.trainX = trainX;
    this.trainY = trainY;
    this.valX = valX;
    this.valY = valY;
  }

  public double[][] trainX() {
    return trainX;
  }

  public double[][] trainY() {
    return trainY;
  }

  public double[][] valX() {
    return valX;
  }

  public double[][] valY() {
    return valY;
  }
}
