package org.example.texas.tier;

import org.example.texas.card.CardValue;

public class SetTier implements HandTier {

  private final CardValue setType;

  public SetTier(CardValue setType) {
    this.setType = setType;
  }

  @Override
  public int compareTo(HandTier other) {
    return setType.compareTo(((SetTier) other).setType);
  }
}
