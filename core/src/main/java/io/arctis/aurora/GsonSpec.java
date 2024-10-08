package io.arctis.aurora;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.arctis.aurora.model.ActivationFunction;
import io.arctis.aurora.model.ActivationFunctionSerializer;
import io.arctis.aurora.sets.DataSetSerializer;

/**
 * A Gson specification for Aurora.
 *
 * @author Brissach
 * @since 02.03.2023 12:40
 * © Aurora - All Rights Reserved
 */
public final class GsonSpec {

  /**
   * The standard Gson specification.
   */
  private static final Gson STANDARD = new GsonBuilder()
    .registerTypeAdapter(ActivationFunction.class, new ActivationFunctionSerializer())
    .registerTypeAdapter(DataSetSerializer.class, new DataSetSerializer())
    .create();

  /**
   * The pretty Gson specification.
   */
  private static final Gson PRETTY = new GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(ActivationFunction.class, new ActivationFunctionSerializer())
    .registerTypeAdapter(DataSetSerializer.class, new DataSetSerializer())
    .create();

  /**
   * Gets the standard Gson specification.
   *
   * @return Returns the standard Gson specification
   */
  public static Gson standard() {
    return STANDARD;
  }

  /**
   * Gets the pretty Gson specification.
   *
   * @return Returns the pretty Gson specification
   */
  public static Gson pretty() {
    return PRETTY;
  }
}
