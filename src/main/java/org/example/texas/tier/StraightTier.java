package org.example.texas.tier;

import org.example.texas.card.CardValue;

public class StraightTier implements HandTier {

  private final CardValue highestType;

  public StraightTier(CardValue highestTier) {
    this.highestType = highestTier;
  }

  @Override
  public int compareTo(HandTier other) {
    return highestType.compareTo(((StraightTier) other).highestType);
  }

}
