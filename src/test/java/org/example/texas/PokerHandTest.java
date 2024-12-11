package org.example.texas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class PokerHandTest {

  @Test
  public void testSorting() {
    List<PokerHand> hands = new ArrayList<>();
    hands.add(new PokerHand("2C 3C AC 4C 5C"));
    hands.add(new PokerHand("9H TH JH QH KH"));
    hands.add(new PokerHand("KS 2H 5C JD TD"));

    Collections.sort(hands);

    assertIterableEquals(
        Arrays.asList(HandType.STRAIGHT_FLUSH, HandType.FLUSH, HandType.HIGH_CARD),
        hands.stream().map(PokerHand::getHandType).toList());
  }


  @Test
  public void testFlushRoyals() {
    List<String> flushRoyals = Arrays.asList(
        "AH KH QH JH TH",
        "AS KS QS JS TS",
        "KH AH QH JH TH",
        "TD JD QD KD AD"
    );
    boolean flushRoyalResult = flushRoyals.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.ROYAL_FLUSH));
    assertTrue(flushRoyalResult);
  }


  @Test
  public void testNotFlushRoyals() {
    List<String> flushRoyals = Arrays.asList(
        "AH KH QH JH 9H",
        "AS KS QD JS TS",
        "KH KH QH JH TH",
        "9D TD JD QD KD"
    );
    boolean notFlushRoyalResult = flushRoyals.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.ROYAL_FLUSH));
    assertTrue(notFlushRoyalResult);
  }

  @Test
  public void testStraightFlushes() {
    List<String> straightFlushes = Arrays.asList(
        "9H TH JH QH KH",
        "2D 3D 4D 5D 6D",
        "5S 4S 2S 3S 6S",
        "7C 9C 8C TC JC"
    );
    boolean straightFlushResult = straightFlushes.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.STRAIGHT_FLUSH));
    assertTrue(straightFlushResult);
  }


  @Test
  public void testNotStraightFlushes() {
    List<String> straightFlushes = Arrays.asList(
        "9H TH JH QC KH",
        "2D 3D 4D 5D 7D",
        "5S 3S 7S 3S 5S",
        "7C 9H 8C TC JC"
    );
    boolean notStraightFlushResult = straightFlushes.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.STRAIGHT_FLUSH));
    assertTrue(notStraightFlushResult);
  }

  @Test
  public void testFour() {
    List<String> fours = Arrays.asList(
        "2D 2C 2H 2S 5D",
        "4D 4C 4H 2S 4S",
        "KS KC KH 2S KD",
        "2D 2C 2H 2S 5D"
    );
    boolean fourResult = fours.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.FOUR));
    assertTrue(fourResult);
  }

  @Test
  public void testNotFour() {
    List<String> fours = Arrays.asList(
        "2D 2C 3H 2S 5D",
        "4D 4C 4H 2S TS",
        "2S 2C KH 2S KD",
        "2D 3C 2H 2S 5D"
    );
    boolean fourResult = fours.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.FOUR));
    assertTrue(fourResult);
  }

  @Test
  public void testFullHouse() {
    List<String> fullHouses = Arrays.asList(
        "2D 2C 2H 5S 5D",
        "4D 4C 4H 2H 2S",
        "2S KC KH 2H KD",
        "2D 2C 2H TD TD"
    );
    boolean fullHouseResult = fullHouses.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.FULL_HOUSE));
    assertTrue(fullHouseResult);
  }


  @Test
  public void testNotFullHouse() {
    List<String> fullHouses = Arrays.asList(
        "2D 2C 2H 3S 5D",
        "4D 4C 3H 2H 2S",
        "2S 2C QH 2H KD",
        "2D 2C 2H TD 5D"
    );
    boolean fullHouseResult = fullHouses.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.FULL_HOUSE));
    assertTrue(fullHouseResult);
  }

  @Test
  public void testFlush() {
    List<String> flushes = Arrays.asList(
        "7D 2D 3D 4D 5D",
        "7S 2S 3S 4S 5S",
        "QC 2C 5C 4C 5C",
        "7H 2H 3H 4H 5H"
    );
    boolean flushResult = flushes.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.FLUSH));
    assertTrue(flushResult);
  }

  @Test
  public void testNotFlush() {
    List<String> flushes = Arrays.asList(
        "7S 2D 3D 4D 5D",
        "2S 3S 4S 5S 6S",
        "QC 2H 5C 4C 5C",
        "7H 2D 3D 4D 5D"
    );
    boolean flushResult = flushes.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.FLUSH));
    assertTrue(flushResult);
  }

  @Test
  public void testStraight() {
    List<String> straights = Arrays.asList(
        "2D 3D 4D 5H 6S",
        "3S 4S 5D 6S 7H",
        "7H 5D 3S 4C 6C",
        "7D 6S 5H 4H 3H"
    );
    boolean straightResult = straights.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.STRAIGHT));
    assertTrue(straightResult);
  }

  @Test
  public void testNotStraight() {
    List<String> straights = Arrays.asList(
        "2D 3D 4D 5H 7S",
        "3S 4S 8D 6S 7H",
        "7H TD 3S 4C 6C",
        "7D 6S KH 4H 3H"
    );
    boolean straightResult = straights.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.STRAIGHT));
    assertTrue(straightResult);
  }

  @Test
  public void testSet() {
    List<String> sets = Arrays.asList(
        "2D 2D 2D 5H 6S",
        "3S 3S 3D 6S 7H",
        "4H 5D 3S 4C 4C",
        "2D 6S 2H 4H 2H"
    );
    boolean setResult = sets.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.SET));
    assertTrue(setResult);
  }

  @Test
  public void testNotSet() {
    List<String> sets = Arrays.asList(
        "2D 2D 2D 5H 5S",
        "3S 3S 2D 6S 7H",
        "4H 4D 2S 4C 4C",
        "2D 2S 2H 4H 2H"
    );
    boolean setResult = sets.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.SET));
    assertTrue(setResult);
  }

  @Test
  public void testTwoPairs() {
    List<String> sets = Arrays.asList(
        "2D 2D 3D 5H 5S",
        "3S 3S 6D 6S 7H",
        "5H 5D 3S 4C 4C",
        "2D 4S 3H 4H 2H"
    );
    boolean twoPairsResult = sets.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.TWO_PAIRS));
    assertTrue(twoPairsResult);
  }

  @Test
  public void testNotTwoPairs() {
    List<String> sets = Arrays.asList(
        "2D 2D 5D 5H 5S",
        "3S 2S 6D 6S 7H",
        "5H 5D 4S 4C 4C",
        "2D 4S 2H 4H 2H"
    );
    boolean twoPairsResult = sets.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.TWO_PAIRS));
    assertTrue(twoPairsResult);
  }

  @Test
  public void testPair() {
    List<String> pairs = Arrays.asList(
        "2D 2D 3D 4H 5S",
        "3S 2S 6D 6S 7H",
        "5H 6D 3S 4C 4C",
        "2D 4S 3H 8H 2H"
    );
    boolean pairResult = pairs.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.PAIR));
    assertTrue(pairResult);
  }

  @Test
  public void testNotPair() {
    List<String> pairs = Arrays.asList(
        "2D 9D 3D 4H 5S",
        "3S 6S 6D 6S 7H",
        "5H 5D 3S 4C 4C",
        "2D 4S 3H 4H 2H"
    );
    boolean pairResult = pairs.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.PAIR));
    assertTrue(pairResult);
  }

  @Test
  public void testHighCard() {
    List<String> pairs = Arrays.asList(
        "2D 9D 3D 4H 5S",
        "3S 2S 6D TS 7H",
        "5H QD 3S 4C 2C",
        "KD 4S 3H 8H 2H"
    );
    boolean pairResult = pairs.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .allMatch(handType -> handType.equals(HandType.HIGH_CARD));
    assertTrue(pairResult);
  }

  @Test
  public void testNotHighCard() {
    List<String> pairs = Arrays.asList(
        "2D 9D 9D 4H 5S",
        "3S 2S 6D 2S 7H",
        "5H QD 3S 3C 2C",
        "KD 4S 4H 8H 2H"
    );
    boolean pairResult = pairs.stream()
        .map(PokerHand::new)
        .map(PokerHand::getHandType)
        .noneMatch(handType -> handType.equals(HandType.HIGH_CARD));
    assertTrue(pairResult);
  }
}