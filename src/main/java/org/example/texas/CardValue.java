package org.example.texas;

import java.util.Arrays;

public enum CardValue {

  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  TEN("T"),
  JACK("J"),
  QUEEN("Q"),
  KING("K"),
  ACE("A");

  public final String label;

  CardValue(String label) {
    this.label = label;
  }

  public static CardValue of(String label) {
    return Arrays.stream(CardValue.values())
        .filter(value -> value.label.equals(label))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }

}
