package org.example.texas.tier;

import org.example.texas.card.CardValue;

public class FourTier implements HandTier {

  private final CardValue fourType;

  public FourTier(CardValue fourType) {
    this.fourType = fourType;
  }

  @Override
  public int compareTo(HandTier other) {
    return fourType.compareTo(((FourTier) other).fourType);
  }
}
