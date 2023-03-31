package com.company.pronko_p;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {
    private String handString;

    public PokerHand(String hand) {
        if (hand == null || hand.isEmpty()) {
            throw new IllegalArgumentException("Строка не может быть пустой");
        }
        String[] cardStrings = hand.trim().split("\\s+");

        if (cardStrings.length != 5) {
            throw new IllegalArgumentException("Строка должна содержать только пять карт");
        }

        this.handString = hand;
    }

    public int rank() {
        String[] cardStrs = handString.split(" ");
        Integer[] ranks = new Integer[5];
        char[] suits = new char[5];
        for (int i = 0; i < 5; i++) {
            ranks[i] = "23456789TJQKA".indexOf(cardStrs[i].charAt(0)) + 2;
            suits[i] = cardStrs[i].charAt(1);
        }

        // сортировка карт чтобы легче было находить наивысшую
        Arrays.sort(ranks);
        boolean flush = suits[0] == suits[1] && suits[1] == suits[2] && suits[2] == suits[3] && suits[3] == suits[4];
        boolean straight = ranks[4] - ranks[0] == 4 && new HashSet<Integer>(Arrays.asList(ranks)).size() == 5;

        // проверка на флэш рояль не имеет смысл, потому что и так сила рука идет по наивысшей карте

        // проверка на стрит флэш
        if (flush && straight) {
            return 900 + ranks[4];
        }
        // Проверка на карэ
        else if (ranks[0] == ranks[3] || ranks[1] == ranks[4]) {
            return 800 + ranks[2];
        }
        // Проверка на фулхаус
        else if ((ranks[0] == ranks[1] && ranks[2] == ranks[4]) ||
                (ranks[0] == ranks[2] && ranks[3] == ranks[4])) {
            return 700 + ranks[2];
        }
        // Проверка на флэш
        else if (flush) {
            return 600 + ranks[4];
        }
        // Проверка на стрит
        else if (straight) {
            return 500 + ranks[4];
        }
        // Проверка на тройку
        else if (ranks[0] == ranks[2] ||
                ranks[1] == ranks[3] ||
                ranks[2] == ranks[4]) {
            return 400 + ranks[2];
        }
        // Проверка на две пары
        else if ((ranks[0] == ranks[1] && ranks[2] == ranks[3]) ||
                (ranks[0] == ranks[1] && ranks[3] == ranks[4]) ||
                (ranks[1] == ranks[2] && ranks[3] == ranks[4])) {
            int pairRank = Math.max(Math.max(ranks[0], ranks[1]), Math.max(ranks[2], ranks[3]));
            return 300 + pairRank;
        }
        // Проверка на пару
        else if (ranks[0] == ranks[1] ||
                ranks[1] == ranks[2] ||
                ranks[2] == ranks[3] ||
                ranks[3] == ranks[4]) {
            int pairRank = 0;
            for (int i = 0; i < 4; i++) {
                if (ranks[i] == ranks[i + 1]) {
                    pairRank = ranks[i];
                    break;
                }
            }
            int kicker = ranks[4];
            return 200 + 14 * pairRank + kicker;
        }
        // Наивысшая карта
        else {
            int highCard = ranks[4];
            return 14 * highCard;
        }
    }

    @Override
    public int compareTo(PokerHand o) {
        Integer selfHand = this.rank();
        Integer otherHand = o.rank();
        return selfHand.compareTo(otherHand);
    }
}
