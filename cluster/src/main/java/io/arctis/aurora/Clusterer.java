package io.arctis.aurora;

import io.arctis.aurora.model.MLContext;
import io.arctis.aurora.model.MLContextProvider;
import io.arctis.aurora.model.Predictable;

/**
 * A clusterer marker interface.
 *
 * @author Brissach
 * @since 09.04.2023 20:05
 * © Aurora - All Rights Reserved
 */
public interface Clusterer extends Predictable, MLContextProvider {

  @Override
  default MLContext context() {
    return MLContext.CLUSTERING;
  }

}
