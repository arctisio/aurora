package io.arctis.aurora_deep;

import io.arctis.aurora.Aurora;
import io.arctis.aurora.GsonSpec;
import io.arctis.aurora.model.Model;

import java.util.List;

/**
 * @author Brissach
 * @since 24.04.2024 19:26
 * © Aurora - All Rights Reserved
 */
public class ComplexNeuralNetworkModel extends AbstractComplexNeuralNetwork implements Model {

  protected String version = Aurora.version();
  private transient String saveDirectory;
  private transient boolean saveOnClose;

  public ComplexNeuralNetworkModel(List<Layer> layers) {
    super(layers);
  }

  @Override
  public Model name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public Model saveDirectoryPath(String saveDirectory) {
    this.saveDirectory = saveDirectory;
    return this;
  }

  @Override
  public Model saveOnClose(boolean saveOnClose) {
    this.saveOnClose = saveOnClose;
    return this;
  }

  @Override
  public String version() {
    return version;
  }

  @Override
  public String saveDirectoryPath() {
    return saveDirectory;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void close() {
    if (saveOnClose) save();
  }

  @Override
  public String serialize() {
    return GsonSpec.standard().toJson(this);
  }
}
