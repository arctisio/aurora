package io.arctis.aurora;

import gg.acai.acava.io.Closeable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * @author Brissach
 * @since 08.08.2024 17:44
 * © Aurora - All Rights Reserved
 */
public class ContextVector implements Closeable, Iterable<Double> {

  public static ContextVector newVector() {
    return new ContextVector();
  }

  public static ContextVector of(Collection<Double> values) {
    return newVector().addValuesToVector(values);
  }

  public static ContextVector of(double... values) {
    return newVector().addValuesToVector(values);
  }

  public static ContextVector fill(int size, double value) {
    ContextVector vector = newVector();
    for (int i = 0; i < size; i++)
      vector.addValueToVector(value);
    return vector;
  }

  public static ContextVector fill(int size, BiConsumer<ContextVector, Integer> action) {
    ContextVector vector = newVector();
    for (int i = 0; i < size; i++)
      action.accept(vector, i);
    return vector;
  }

  public static int summationOf(ContextVector... contexts) {
    int dimensions = 0;
    for (ContextVector context : contexts)
      dimensions += context.dimension();
    return dimensions;
  }

  private final List<Double> vector = new ArrayList<>();

  public ContextVector(double... values) {
    for (double value : values) vector.add(value);
  }

  public double get(int index) {
    return vector.get(index);
  }

  public Optional<Double> fold(int index) {
    return index < dimension() ? Optional.of(get(index)) : Optional.empty();
  }

  public ContextVector addValueToVector(double value) {
    vector.add(value);
    return this;
  }

  public ContextVector addValuesToVector(double... values) {
    for (double value : values) vector.add(value);
    return this;
  }

  public ContextVector addValuesToVector(Collection<Double> values) {
    for (double val : values) addValueToVector(val);
    return this;
  }

  public ContextVector subtractValuesFromVector(double... values) {
    for (int i = 0; i < values.length; i++) {
      double value = values[i];
      double x = vector.get(i);
      double z = x - value;
      vector.set(i, z);
    }
    return this;
  }

  public ContextVector cut(int from, int to) {
    ContextVector context = new ContextVector();
    for (int i = from; i < to; i++)
      context.addValueToVector(vector.get(i));
    return context;
  }

  public ContextVector cut(int from) {
    return cut(from, dimension());
  }

  public ContextVector modify(int index, double value) {
    vector.set(index, value);
    return this;
  }

  public ContextVector multiply(int index, double value) {
    vector.set(index, vector.get(index) * value);
    return this;
  }

  public ContextVector divide(int index, double value) {
    vector.set(index, vector.get(index) / value);
    return this;
  }

  public ContextVector add(int index, double value) {
    vector.set(index, vector.get(index) + value);
    return this;
  }

  public ContextVector subtract(int index, double value) {
    vector.set(index, vector.get(index) - value);
    return this;
  }

  public Stream<Double> stream() {
    return vector.stream();
  }

  public ContextVector merge(ContextVector context) {
    if (context.dimension() != dimension())
      throw new IllegalStateException("Dimension of node x and y must be equal");

    for (int i = 0; i < context.dimension(); i++)
      add(i, context.get(i));
    return this;
  }

  public ContextVector agglutinate(ContextVector context) {
    for (int index = 0; index < context.dimension(); index++) {
      double x = get(index);
      double y = context.get(index);
      double z = (x + y) / 2;
      modify(index, z);
    }
    return this;
  }

  public double cosine(ContextVector y) {
    if (dimension() != y.dimension()) throw new IllegalArgumentException("Vectors must have the same dimension");

    double dotProduct = 0.0;
    double normX = 0.0;
    double normY = 0.0;

    for (int i = 0; i < dimension(); i++) {
      dotProduct += get(i) * y.get(i);
      normX += Math.pow(get(i), 2);
      normY += Math.pow(y.get(i), 2);
    }

    double magnitudeX = Math.sqrt(normX);
    double magnitudeY = Math.sqrt(normY);

    if (magnitudeX == 0 || magnitudeY == 0) {
      return 0.0; // division by zero
    }

    return dotProduct / (magnitudeX * magnitudeY);
  }

  public int dimension() {
    return vector.size();
  }

  public double length() {
    double sum = 0;
    for (double value : vector)
      sum += value * value;
    return Math.sqrt(sum);
  }

  @Nonnull
  public ContextVector normalize() {
    double length = length();
    if (length == 0)
      return fill(dimension(), 0);

    for (int i = 0; i < dimension(); i++)
      modify(i, vector.get(i) / length);
    return this;
  }

  @Nonnull
  public ContextVector normalizeRanged() {
    double min = Collections.min(vector);
    double max = Collections.max(vector);

    for (int i = 0; i < dimension(); i++) {
      double feature = vector.get(i);
      double norm = (feature - min) / (max - min);
      modify(i, norm);
    }

    return this;
  }

  @Nonnull
  public ContextVector normalizeRanged(double minScalar, double maxScalar) {
    double min = Collections.min(vector);
    double max = Collections.max(vector);

    for (int i = 0; i < dimension(); i++) {
      double feature = vector.get(i);
      double norm = ((feature - min) / (max - min)) * (maxScalar - minScalar) + minScalar;
      modify(i, norm);
    }

    return this;
  }

  @Nonnull
  public ContextVector normalizeStandard() {
    double sum = 0;
    for (double value : vector)
      sum += value;
    double mean = sum / dimension();
    sum = 0;
    for (double value : vector)
      sum += (value - mean) * (value - mean);
    double std = Math.sqrt(sum / dimension());
    for (int i = 0; i < dimension(); i++) {
      double feature = vector.get(i);
      double norm = (feature - mean) / std;
      modify(i, norm);
    }
    return this;
  }

  public double[] transform() {
    int dim = dimension();
    double[] transformed = new double[dim];
    for (int i = 0; i < vector.size(); i++)
      transformed[i] = vector.get(i);
    return transformed;
  }

  @Override
  public void close() {
    synchronized (vector) {
      vector.clear();
    }
  }

  @Override
  public String toString() {
    return "ContextVector{" +
      "vector=" + vector +
      '}';
  }

  @Nonnull
  public String toCSV() {
    StringBuilder builder = new StringBuilder();
    int dim = dimension();
    for (int i = 0; i < dim; i++) {
      builder.append(get(i));
      if (i < dim - 1) builder.append(",");
    }
    return builder.toString();
  }

  @Nonnull
  public ContextVector copy() {
    double[] values = transform();
    return ContextVector.newVector()
      .addValuesToVector(values);
  }

  @Override
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = prime * result + vector.hashCode();
    result = prime * result + dimension();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof ContextVector)) return false;
    ContextVector other = (ContextVector) obj;
    return other.dimension() == dimension() && hashCode() == other.hashCode();
  }

  @Override @Nonnull
  public Iterator<Double> iterator() {
    return vector.iterator();
  }
}
