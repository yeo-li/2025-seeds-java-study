package code.seeds;

import java.util.List;
import java.util.Random;

public class RandomWordGenerator {
    private static final List<String> WORD_LIST = List.of("apple", "baker", "cider", "delta", "eagle", "paper", "place", "plane");
    private static final Random RANDOM = new Random();

    public static String getRandomWord() {
        return WORD_LIST.get(RANDOM.nextInt(WORD_LIST.size()));
    }
}