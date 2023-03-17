package gg.acai.aurora.cluster;

import gg.acai.aurora.ml.LevenshteinDistance;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author Clouke
 * @since 07.03.2023 03:50
 * © Aurora - All Rights Reserved
 */
public class Group<T> implements Iterable<T>, Predicate<T> {

  private final T[] content;
  private final List<T> nodes;
  private final Map<T, List<T>> intentNodes;

  public Group(T[] content) {
    this.content = content;
    this.nodes = new ArrayList<>();
    this.intentNodes = new HashMap<>();
  }

  public T get(int index) {
    return content[index];
  }

  public void addNode(T t) {
    nodes.add(t);
  }

  public T getHighestDegreeNode() {
    // checks for the most added one in nodes list
    T highest = null;
    int highestDegree = 0;
    for (T t : nodes) {
      int degree = 0;
      for (T other : nodes) {
        if (t.equals(other)) {
          degree++;
        }
      }
      if (degree > highestDegree) {
        highest = t;
        highestDegree = degree;
      }
    }

    return highest;
  }

  public List<T> getBestNodes(int amount, int distance) {
    List<T> bestNodes = new ArrayList<>();
    for (T t : nodes) {
      if (bestNodes.size() >= amount) {
        break;
      }

      boolean found = false;
      for (T other : bestNodes) {
        if (LevenshteinDistance.compute(t.toString(), other.toString()) <= distance) {
          found = true;
          break;
        }
      }

      if (!found) {
        bestNodes.add(t);
      }
    }
    return bestNodes;
  }

  public T getRandomNode() {
    return nodes.get((int) (Math.random() * nodes.size()));
  }

  public int degree(T t) {
    int degree = 0;
    for (T other : nodes) {
      if (t.equals(other)) {
        degree++;
      }
    }
    return degree;
  }

  public double similarity(T t) {
    if (!(t instanceof String)) {
      return 0;
    }

    String string = (String) t;
    double distance = 0;
    for (T other : content) {
      if (!(other instanceof String)) {
        continue;
      }

      String otherString = (String) other;
      distance += LevenshteinDistance.compute(string, otherString);
    }

    distance /= content.length;

    return 1 - (distance / 100);
  }

  public int size() {
    return content.length;
  }

  public T[] getContent() {
    return content;
  }

  public boolean contains(T t) {
    for (T other : content) {
      if (other.equals(t)) {
        return true;
      }
    }
    return false;
  }

  public void addIntentNode(T intent, T node) {
    List<T> nodes = intentNodes.computeIfAbsent(intent, k -> new ArrayList<>());
    nodes.add(node);
  }

  public T getHighestDegreeIntentNode(T intent) {
    List<T> nodes = intentNodes.get(intent);
    if (nodes == null) {
      return null;
    }

    T highest = null;
    int highestDegree = 0;
    for (T t : nodes) {
      int degree = 0;
      for (T other : nodes) {
        if (t.equals(other)) {
          degree++;
        }
      }
      if (degree > highestDegree) {
        highest = t;
        highestDegree = degree;
      }
    }

    return highest;
  }

  public List<T> getIntentNodes(T intent) {
    return intentNodes.get(intent);
  }

  public Map<T, List<T>> getIntentNodes() {
    return intentNodes;
  }

  public List<T> getNodes() {
    return nodes;
  }

  public boolean hasNode(T t) {
    return nodes.contains(t);
  }

  public T getNode(int index) {
    return nodes.get(index);
  }

  public int nodeSize() {
    return nodes.size();
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < content.length;
      }

      @Override
      public T next() {
        return content[index++];
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Group{");
    T highest = getHighestDegreeNode();
    if (highest != null) {
      builder.append("degreeNode=").append(highest).append(", ");
    }
    if (intentNodes.size() > 0) {
      builder.append("intentNodes=").append(intentNodes).append(", ");
    }
    for (T t : content) {
      builder.append(t).append(", ");
    }
    builder.delete(builder.length() - 2, builder.length());
    builder.append("}");
    return builder.toString();
  }

  @Override
  public boolean test(T t) {
    return contains(t);
  }
}
