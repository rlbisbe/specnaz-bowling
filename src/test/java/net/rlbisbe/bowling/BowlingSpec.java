package net.rlbisbe.bowling;

import org.specnaz.junit.SpecnazJUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class BowlingSpec extends SpecnazJUnit {
    {
        describes("A Game", it -> {
            Game game = new Game();
            it.beginsEach(game::reset);

            it.should("keep the score for a full frame", () -> {
                game.roll(4);
                game.roll(0);
                assertThat(game.getScore()).isEqualTo(4);
            });
            it.should("show 0 as score when first created", () -> assertThat(game.getScore()).isEqualTo(0));

            it.shouldThrow(IllegalArgumentException.class, "when trying to roll more than 10 bowls in a single roll", () -> {
               game.roll(11);
            });

            it.describes("when throwing a spare", () -> {

                it.should("account for the number of pins knocked down by next roll", () -> {
                    game.roll(4);
                    game.roll(6);
                    game.roll(4);
                    game.roll(0);
                    assertThat(game.getScore()).isEqualTo(18);
                });

                it.should("not account for the number of pins of previous play", () -> {
                    game.roll(2);
                    game.roll(4);
                    game.roll(6);
                    game.roll(3);
                    assertThat(game.getScore()).isEqualTo(15);
                });
            });

            it.describes("when throwing a strike", () -> {
                it.should("account for the number of pins knocked down by next two rolls", () -> {
                    game.roll(10);
                    game.roll(3);
                    game.roll(6);
                    assertThat(game.getScore()).isEqualTo(28);
                });

                it.should("account for three strikes in a row", () -> {
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(0);
                    game.roll(0);

                    assertThat(game.getScore()).isEqualTo(60);
                });

                it.should("account for three strikes in a row", () -> {
                    game.roll(0);
                    game.roll(0);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);

                    assertThat(game.getScore()).isEqualTo(60);
                });

                it.should("account for the perfect game", () -> {
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);
                    game.roll(10);

                    assertThat(game.getScore()).isEqualTo(300);
                });
            });
        });
    }
}
