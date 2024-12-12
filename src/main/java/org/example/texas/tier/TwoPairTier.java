package org.example.texas.tier;

import org.example.texas.card.CardValue;

public class TwoPairTier implements HandTier{

  private final CardValue firstPair;
  private final CardValue secondPair;
  private final CardValue kicker;

  public TwoPairTier(CardValue firstPair, CardValue secondPair, CardValue kicker) {
    this.firstPair = firstPair;
    this.secondPair = secondPair;
    this.kicker = kicker;
  }

  @Override
  public int compareTo(HandTier other) {
    TwoPairTier otherTyped = (TwoPairTier) other;

    if (firstPair.compareTo(otherTyped.firstPair) != 0) {
      return firstPair.compareTo(otherTyped.firstPair);
    }

    if (secondPair.compareTo(otherTyped.secondPair) != 0) {
      return secondPair.compareTo(otherTyped.secondPair);
    }

    return kicker.compareTo(otherTyped.kicker);
  }

}
