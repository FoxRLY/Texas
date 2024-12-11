package org.example.texas;

import java.util.Arrays;

public enum CardSuit {

  SPADES("S"),
  HEARTS("H"),
  CLUBS("C"),
  DIAMONDS("D");

  public final String label;

  CardSuit(String label) {
    this.label = label;
  }

  public static CardSuit of(String label) {
    return Arrays.stream(CardSuit.values())
        .filter(value -> value.label.equals(label))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }
}
