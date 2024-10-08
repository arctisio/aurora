package io.arctis.aurora;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * @author Brissach
 * @since 08.08.2024 17:48
 * © Aurora - All Rights Reserved
 */
public interface Layer {

  void randomize(@Nonnull Random random);

  void shape(@Nonnull ContextVector y);

  double predict(@Nonnull ContextVector x);

  int attribute();

}
