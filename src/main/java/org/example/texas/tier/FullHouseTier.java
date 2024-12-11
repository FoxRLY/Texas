package org.example.texas.tier;

import org.example.texas.CardValue;

public class FullHouseTier implements HandTier {

  private final CardValue setType;

  public FullHouseTier(CardValue setType) {
    this.setType = setType;
  }

  @Override
  public int compareTo(HandTier other) {
    return setType.compareTo(((FullHouseTier) other).setType);
  }
}
