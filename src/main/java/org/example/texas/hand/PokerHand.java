package org.example.texas.hand;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.texas.card.Card;
import org.example.texas.card.CardSuit;
import org.example.texas.card.CardValue;
import org.example.texas.tier.FlushRoyalTier;
import org.example.texas.tier.FlushTier;
import org.example.texas.tier.FourTier;
import org.example.texas.tier.FullHouseTier;
import org.example.texas.tier.HandTier;
import org.example.texas.tier.HighCardTier;
import org.example.texas.tier.PairTier;
import org.example.texas.tier.SetTier;
import org.example.texas.tier.StraightFlushTier;
import org.example.texas.tier.StraightTier;
import org.example.texas.tier.TwoPairTier;

public class PokerHand implements Comparable<PokerHand> {

  private String handString;
  private HandType type;
  private HandTier tier;

  public PokerHand(String hand) {
    analyzeHand(hand);
    this.handString = hand;
  }

  public HandType getHandType() {
    return this.type;
  }

  public HandTier getHandTier() {
    return this.tier;
  }

  public String getHandString() {
    return this.handString;
  }

  @Override
  public int compareTo(PokerHand pokerHand) {
    int typeComparisonResult = pokerHand.type.compareTo(type);

    if (typeComparisonResult == 0) {
      return tier.compareTo(pokerHand.tier);
    }

    return typeComparisonResult;
  }

  private void analyzeHand(String hand) {
    List<String> cardStrings = List.of(hand.split("\\s+"));

    List<Card> cards = cardStrings.stream()
        .map(Card::of)
        .sorted()
        .toList();


    boolean sequenced = isHandSequenced(cards);
    boolean oneSuit = isHandOfOneSuit(cards);


    if (sequenced) {
      if (oneSuit) {
        if (getHighestCardValue(cards).equals(CardValue.ACE)) {
          this.type = HandType.ROYAL_FLUSH;
          this.tier = new FlushRoyalTier();
        } else {
          this.type = HandType.STRAIGHT_FLUSH;
          this.tier = new StraightFlushTier(getHighestCardValue(cards));
        }
        return;
      } else {
        this.type = HandType.STRAIGHT;
        this.tier = new StraightTier(getHighestCardValue(cards));
        return;
      }
    }

    if (oneSuit) {
      this.type = HandType.FLUSH;
      this.tier = new FlushTier(cards.stream()
          .sorted(Comparator.reverseOrder())
          .map(Card::getCardValue)
          .toList());
      return;
    }

    Map<CardValue, Long> distribution = getCardDistribution(cards);

    var optionalFour = distribution.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 4)
        .findFirst();

    if (optionalFour.isPresent()) {
      this.type = HandType.FOUR;
      this.tier = new FourTier(optionalFour.get().getKey());
      return;
    }

    if (distribution.entrySet().size() == 2) {
      var fullHouse = distribution.entrySet()
          .stream()
          .sorted(Map.Entry.comparingByValue())
          .toList()
          .getLast();
      this.type = HandType.FULL_HOUSE;
      this.tier = new FullHouseTier(fullHouse.getKey());
      return;
    }

    var optionalSet = distribution.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 3)
        .findFirst();

    if (optionalSet.isPresent()) {
      this.type = HandType.SET;
      this.tier = new SetTier(optionalSet.get().getKey());
      return;
    }


    if (distribution.entrySet().size() == 3) {
      var pairList = distribution.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
      CardValue highestPair;
      CardValue lowestPair;
      CardValue kicker;

      if (pairList.get(1).getKey().compareTo(pairList.get(2).getKey()) > 0) {
        highestPair = pairList.get(1).getKey();
        lowestPair = pairList.get(2).getKey();
      } else {
        highestPair = pairList.get(2).getKey();
        lowestPair = pairList.get(1).getKey();
      }

      kicker = pairList.get(0).getKey();

      this.type = HandType.TWO_PAIRS;
      this.tier = new TwoPairTier(highestPair, lowestPair, kicker);
      return;
    }

    var pair = distribution.entrySet().stream().filter(entry -> entry.getValue() == 2).findFirst();
    if (pair.isPresent()) {
      this.type = HandType.PAIR;
      this.tier = new PairTier(pair.get().getKey(),
          getHighestCardValue(cards.stream()
              .filter(card -> card.getCardValue() != pair.get().getKey())
              .toList())
      );
      return;
    }

    this.type = HandType.HIGH_CARD;
    this.tier = new HighCardTier(getHighestCardValue(cards));
  }

  private boolean isHandSequenced(List<Card> cards) {
    for (int i = 0; i < 4; i++) {
      if (cards.get(i + 1).getCardValue().ordinal() - cards.get(i).getCardValue().ordinal() != 1) {
        return false;
      }
    }

    return true;
  }

  private boolean isHandOfOneSuit(List<Card> cards) {
    CardSuit suit = cards.getFirst().getCardSuit();
    return cards.stream().allMatch(card -> card.getCardSuit().equals(suit));
  }

  private Map<CardValue, Long> getCardDistribution(List<Card> cards) {
    return cards.stream().collect(Collectors.groupingBy(Card::getCardValue, Collectors.counting()));
  }

  private CardValue getHighestCardValue(List<Card> cards) {
    return cards.getLast().getCardValue();
  }
}
