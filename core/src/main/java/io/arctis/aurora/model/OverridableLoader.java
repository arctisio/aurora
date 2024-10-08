package io.arctis.aurora.model;

/**
 * @author Brissach
 * @since 05.07.2023 22:49
 * © Aurora - All Rights Reserved
 */
@FunctionalInterface
public interface OverridableLoader<M extends Model> {
  M load(String json);
}
