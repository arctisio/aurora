package io.arctis.aurora;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author Brissach
 * @since 08.08.2024 17:50
 * © Aurora - All Rights Reserved
 */
public interface Neuron {

  void randomize(@Nonnull Random random);

  void noisyShape(@Nonnull Random random, double rate);

  void shape(@Nonnull ContextVector y, int n);

  @Nonnull
  ContextVector shapeVector();

  @Nullable
  ContextVector sumVector();

}
