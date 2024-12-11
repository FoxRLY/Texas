package org.example.texas.tier;

import org.example.texas.CardValue;

public class StraightFlushTier implements HandTier {
  private final CardValue highestType;

  public StraightFlushTier(CardValue highestType) {
    this.highestType = highestType;
  }

  @Override
  public int compareTo(HandTier other) {
    return highestType.compareTo(((StraightFlushTier) other).highestType);
  }

}
