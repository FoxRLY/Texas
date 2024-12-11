package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.texas.PokerHand;

public class Main {
  public static void main(String[] args) {
    List<PokerHand> hands = new ArrayList<>();
    hands.add(new PokerHand("9H TH JH QH KH"));
    hands.add(new PokerHand("KS 2H 5C JD TD"));
    hands.add(new PokerHand("2C 3C AC 4C 5C"));
    Collections.sort(hands);

    for (var hand : hands) {
      System.out.println(hand.getHandType().toString());
    }
  }
}