package org.wasa.scoreranking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    public void rocksRankingAtSomeGameOnTheInternets() {
        Scores scores = new Scores();

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

}
