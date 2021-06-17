package org.wasa.scoreranking;

import lombok.Value;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoresTest {

    @Test
    public void rocksRankingAtSomeGameOnTheInternets() {
        Scores scores = new Scores(16);

        scores.addMember("Ale", 0D);
        scores.addMember("Roger", 10D);
        scores.addMember("Jordi", 20D);
        scores.addMember("David", 30D);
        scores.addMember("David", 50D);
        scores.addMember("Ale", 60D);
        scores.addMember("Oscar", 70D);

        assertEquals(1, scores.getRank("Ale"),
                "Ale is the second best, so rank is 1 since it starts at 0. "
                        + "Also, Ale also has a pretty terrible score, but best score is taken into account");
    }

    @Test
    public void addOperationScalesUp() {
        int numberOftests = 1000;

        List<Performance> summary = IntStream.range(0, numberOftests).boxed()
                .map(numberOfOps -> addRandomMembers(new Scores(numberOftests), numberOfOps))
                .collect(Collectors.toList());

        summary.forEach(System.out::println);
    }

    private Performance addRandomMembers(Scores scores, int count) {
        long start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            scores.addMember(computeRandomName(), computeRandomScore());
        }
        return new Performance(count, System.nanoTime() - start);
    }

    private Double computeRandomScore() {
        return Math.random();
    }

    private String computeRandomName() {
        return RandomStringUtils.randomAlphanumeric(42);
    }

    @Value
    private static class Performance {
        int numberOfAdds;
        long timeInNano;

        @Override
        public String toString() {
            return stringify(numberOfAdds) + " " + repeat('-', timeInNano / 10000L);
        }

        private String stringify(int numberOfAdds) {
            String numberOfAddsStr = String.valueOf(numberOfAdds);
            return repeat(' ', 10 - numberOfAddsStr.length()) + numberOfAddsStr;
        }

        private String repeat(char character, long times) {
            StringBuilder res = new StringBuilder();
            for (long i = 0; i < times; i++) {
                res.append(character);
            }
            return res.toString();
        }
    }

}
