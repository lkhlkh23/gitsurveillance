package support;

import java.util.Random;

public class RandomNumber {

    public static int getRandomNumber(int size) {
        return new Random().nextInt(size);
    }
}
