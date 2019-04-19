package net.rlbisbe.bowling;

import org.specnaz.params.junit.SpecnazParamsJUnit;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.specnaz.params.Params2.p2;


public class BowlingParamsSpec extends SpecnazParamsJUnit {
    {
        describes("A Parametrized Game", it -> {
            Game game = new Game();
            it.beginsEach(game::reset);

            it.should("properly calculate the gameplays of %1 equal %2", (List<Integer> rolls, Integer expected) -> {
                rolls.forEach(game::roll);
                assertThat(game.getScore()).isEqualTo(expected);
            }).provided(
                    p2(Arrays.asList(2, 3), 5),
                    p2(Arrays.asList(4, 6, 4, 0), 18),
                    p2(Arrays.asList(2, 4, 6, 3), 15),
                    p2(Arrays.asList(10, 3, 6), 28),
                    p2(Arrays.asList(10, 10, 10, 0, 0), 60),
                    p2(Arrays.asList(0, 0, 10, 10, 10), 60),
                    p2(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10), 300)
            );
        });
    }
}
