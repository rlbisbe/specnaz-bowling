package net.rlbisbe.bowling;


import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> rolls;

    public Game() {
        reset();
    }

    public int getScore() {
        int score = 0;
        int frame = 0;

        for (int i = 0; i < rolls.size(); i++) {

            if (tryGet(rolls, i) == 10) {
                score += tryGet(rolls, i) + tryGet(rolls, i + 1) + tryGet(rolls, i + 2);
            } else if (tryGet(rolls, i) + tryGet(rolls, i + 1) == 10) {
                score += tryGet(rolls, i) + tryGet(rolls, i + 1) + tryGet(rolls, i + 2);
                i++;
            } else {
                score += tryGet(rolls, i) + tryGet(rolls, i + 1);
                i++;
            }

            frame += 1;
            if (frame == 10){
                break;
            }
        }
        return score;
    }

    private int tryGet(List<Integer> list, int index) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return 0;
        }
    }

    public void roll(int pins) {
        if (pins > 10){
            throw new IllegalArgumentException();
        }

        rolls.add(pins);
    }

    public void reset() {
        rolls = new ArrayList<>();
    }
}
