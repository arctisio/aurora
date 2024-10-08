package io.arctis.aurora_deep;

import gg.acai.acava.collect.Mutability;
import io.arctis.aurora.QRMath;
import io.arctis.aurora.model.Trainable;
import io.arctis.aurora_deep.loss.LossFunction;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Brissach
 * @since 04.12.2023 11:20
 * © Aurora - All Rights Reserved
 */
public class ComplexNeuralNetwork extends AbstractComplexNeuralNetwork implements Trainable {

  public double loss;
  private final double predefinedLearningRate;
  private final LossFunction lf;

  public ComplexNeuralNetwork(ComplexNeuralNetworkBuilder builder) {
    super(builder);
    predefinedLearningRate = builder.predefinedLearningRate;
    lf = builder.lossFunction;
  }

  public void train(double[][] inputs, double[][] targets, int epochs, double learningRate) {
    for (int epoch = 0; epoch < epochs; epoch++) {
      train(inputs, targets, learningRate);
    }
  }

  @Override
  public void train(double[][] inputs, double[][] outputs) {
    train(
      inputs,
      outputs,
      predefinedLearningRate
    );
  }

  public void train(double[][] inputs, double[][] targets, double learningRate) {
    double sumLoss = 0.0;
    for (int i = 0; i < inputs.length; i++) {
      double[] output = predict(inputs[i]);
      double[] target = targets[i];
      sumLoss += mse(output, target);
      //sumLoss += lf.computeLoss(output, target);
      backpropagate(
        inputs[i],
        targets[i],
        output,
        learningRate
      );
    }
    loss = sumLoss / inputs.length;
  }

  double mse(double[] output, double[] target) {
    double sum = 0.0;
    for (int i = 0; i < output.length; i++)
      sum += Math.pow(output[i] - target[i], 2);
    return sum / 2;
  }

  void backpropagate(double[] input, double[] expected, double[] output, double learningRate) {
    double[] error;
    for (int i = layers.size() - 1; i >= 0; i--) {
      Layer layer = layers.get(i);
      error = new double[layer.neurons().length];

      if (i != layers.size() - 1) {
        for (int j = 0; j < layer.neurons().length; j++) {
          double errorSum = 0;
          for (Neuron neuron : layers.get(i + 1).neurons())
            errorSum += neuron.weights()[j] * neuron.delta();

          if (Double.isNaN(errorSum)) errorSum = 1000;

          error[j] = errorSum;
        }
      } else {
        for (int j = 0; j < layer.neurons().length; j++) {
          if (expected.length != output.length)
            throw new IllegalArgumentException("Expected output size is not equal to output size");

          error[j] = output[j] - expected[j];
          //error[j] = lf.computeGradient(output, expected)[j];
        }
      }

      for (int j = 0; j < layer.neurons().length; j++) {
        Neuron neuron = layer.neurons()[j];
        neuron.setDelta(error[j] * neuron.activation().derivative(neuron.output()));

        for (int k = 0; k < neuron.weights().length; k++) {
          double inputVal = i == 0 ? input[k] : layers.get(i - 1)
            .neurons()[k]
            .output();

          double sub = learningRate * neuron.delta() * inputVal;
          double res = neuron.weights()[k] - sub;
          // if we would go out of the range, make learning rate lower - jonathan
          if (outputNormalization && QRMath.clamp(Math.abs(res), 1e-3, 1e0) != Math.abs(res))
            sub *= 0.01;
          neuron.weights()[k] -= sub;
        }

        neuron.setBias(neuron.bias() - learningRate * neuron.delta());
      }
    }
  }

  public double loss(double[][] inputs, double[][] outputs) {
    double sumLoss = 0.0;
    for (int i = 0; i < inputs.length; i++) {
      double[] output = predict(inputs[i]);
      double[] target = outputs[i];
      sumLoss += mse(output, target);
      //sumLoss += lf.computeLoss(output, target);
    }
    return sumLoss / inputs.length;
  }

  public void resetState() {
    synchronized (layers) {
      layers.forEach(layer -> Arrays
        .stream(layer.neurons())
        .forEach(neuron -> {
          neuron.setOutput(0);
          neuron.setDelta(0);
        }));
    }
  }

  public List<Layer> layers(@Nonnull Mutability mutability) {
    return mutability == Mutability.MUTABLE
      ? layers
      : Collections.unmodifiableList(layers);
  }

  @Override
  public double accuracy() {
    return 0;
  }
}