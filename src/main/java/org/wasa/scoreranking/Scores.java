package org.wasa.scoreranking;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.binarySearch;

class Scores {

    private final Map<String, Double> scoreByName = new HashMap<>();
    private final List<Member> sortedMembers = new ArrayList<>();

    void addMember(String name, Double score) {
        scoreByName.put(name, score);

        sortedMembers.add(Member.of(name, score));
        Collections.sort(sortedMembers);
    }

    int getRank(String name) {
        Double score = scoreByName.get(name);

        return binarySearch(sortedMembers, new Member(name, score));
    }

    @Value(staticConstructor = "of")
    private static class Member implements Comparable<Member> {
        String name;
        Double score;

        @Override
        public int compareTo(Member other) {
            int scoreComparison = - getScore().compareTo(other.getScore());
            return scoreComparison != 0 ? scoreComparison : getName().compareTo(other.getName());
        }
    }
}
