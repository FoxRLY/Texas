package org.example.texas.tier;

import org.example.texas.CardValue;

public class HighCardTier implements HandTier {
  final private CardValue cardValue;

  public HighCardTier(CardValue cardValue) {
    this.cardValue = cardValue;
  }

  @Override
  public int compareTo(HandTier other) {
    HighCardTier otherTyped = (HighCardTier) other;
    return cardValue.compareTo(otherTyped.cardValue);
  }

}
