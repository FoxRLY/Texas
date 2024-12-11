package org.example.texas.tier;

import org.example.texas.CardSuit;
import org.example.texas.CardValue;

public class Card implements Comparable<Card> {

  private final CardValue cardValue;
  private final CardSuit cardSuit;

  public String toString() {
    return cardValue.label + cardSuit.label;
  }

  public Card(CardValue cardValue, CardSuit cardSuit) {
    this.cardValue = cardValue;
    this.cardSuit = cardSuit;
  }

  static public Card of(String cardString) {
    return new Card(
        CardValue.of(cardString.substring(0, 1)),
        CardSuit.of(cardString.substring(1)));
  }

  @Override
  public int compareTo(Card card) {
    if (cardValue.compareTo(card.cardValue) != 0) {
      return cardValue.compareTo(card.cardValue);
    }

    return cardSuit.compareTo(card.cardSuit);
  }

  public CardValue getCardValue() {
    return this.cardValue;
  }

  public CardSuit getCardSuit() {
    return this.cardSuit;
  }
}
