package com.company.lessonsJavarush;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    public void testHighCard() {
        PokerHand hand = new PokerHand("2C 3H 4D 5S 7H");
        assertEquals(98, hand.rank());
    }

    @Test
    public void testPair() {
        PokerHand hand1 = new PokerHand("2C 2H 4D 5S 7H");
        assertEquals(235, hand1.rank());

        PokerHand hand2 = new PokerHand("2C 2H 4D 5S 8H");
        assertEquals(236, hand2.rank());
    }

    @Test
    public void testTwoPair() {
        PokerHand hand1 = new PokerHand("2C 2H 4D 4S 7H");
        assertEquals(304, hand1.rank());

        PokerHand hand2 = new PokerHand("2C 2H 4D 4S 8H");
        assertEquals(304, hand2.rank());
    }

    @Test
    public void testThreeOfAKind() {
        PokerHand hand1 = new PokerHand("2C 2H 2D 5S 7H");
        assertEquals(402, hand1.rank());

        PokerHand hand2 = new PokerHand("2C 2H 2D 5S KH");
        assertEquals(402, hand2.rank());
    }

    @Test
    public void testStraight() {
        PokerHand hand1 = new PokerHand("2C 3H 4D 5S 6H");
        assertEquals(506, hand1.rank());

        PokerHand hand2 = new PokerHand("TC JH QD KS AH");
        assertEquals(514, hand2.rank());
    }

    @Test
    public void testFlush() {
        PokerHand hand = new PokerHand("2H 4H 6H 8H TH");
        assertEquals(610, hand.rank());
    }

    @Test
    public void testFullHouse() {
        PokerHand hand1 = new PokerHand("2C 2H 2D 5S 5H");
        assertEquals(702, hand1.rank());

        PokerHand hand2 = new PokerHand("2C 2H 2D KS KH");
        assertEquals(702, hand2.rank());
    }

    @Test
    public void testFourOfAKind() {
        PokerHand hand1 = new PokerHand("2C 2H 2D 2S 7H");
        assertEquals(802, hand1.rank());

        PokerHand hand2 = new PokerHand("2C 2H 2D 2S KH");
        assertEquals(802, hand2.rank());
    }

    @Test
    public void testSameCombinationDifferentStrength() {
        PokerHand hand1 = new PokerHand("KS KH 2C 2D 4H");
        PokerHand hand2 = new PokerHand("AS AD QC QH 6S");

        assertTrue(hand2.rank() > hand1.rank());

        PokerHand hand3 = new PokerHand("2S 3H 4C 5D 6S");
        PokerHand hand4 = new PokerHand("3S 4H 5C 6D 7S");

        assertTrue(hand4.rank() > hand3.rank());
    }

    @Test
    public void testInvalidHand() {
        assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand = new PokerHand("QS 2H 5C JD");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand1 = new PokerHand(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PokerHand hand2 = new PokerHand(" ");
        });
    }

}
