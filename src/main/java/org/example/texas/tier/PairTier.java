package org.example.texas.tier;

import org.example.texas.CardValue;

public class PairTier implements HandTier{
  private final CardValue pairType;
  private final CardValue kicker;

  public PairTier(CardValue pairType, CardValue kicker) {
    this.kicker = kicker;
    this.pairType = pairType;
  }

  @Override
  public int compareTo(HandTier other) {
    PairTier otherTyped = (PairTier) other;

    int mainComparison = pairType.compareTo(otherTyped.pairType);
    if (mainComparison == 0) {
      return kicker.compareTo(otherTyped.kicker);
    }

    return mainComparison;
  }
}
