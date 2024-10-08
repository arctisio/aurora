package io.arctis.aurora;

import gg.acai.acava.commons.Attributes;

/**
 * @author Brissach
 * @since 10.06.2023 02:39
 * © Aurora - All Rights Reserved
 */
@FunctionalInterface
public interface Attributed {
  /**
   * Gets the attributes of this attributed marker.
   *
   * @return Returns the attributes of this attributed marker.
   */
  Attributes attributes();
}
