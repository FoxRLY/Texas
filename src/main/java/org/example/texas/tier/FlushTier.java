package org.example.texas.tier;

import java.util.List;
import org.example.texas.card.CardValue;

public class FlushTier implements HandTier {

  private final List<CardValue> cardValues;

  public FlushTier(List<CardValue> cardValues) {
    this.cardValues = cardValues;
  }

  @Override
  public int compareTo(HandTier other) {
    FlushTier otherTyped = (FlushTier) other;

    for (int i = 0; i < 5; i++) {
      int comparisonResult = cardValues.get(i).compareTo(otherTyped.cardValues.get(i));

      if (comparisonResult != 0) {
        return comparisonResult;
      }
    }

    return 0;
  }

}
