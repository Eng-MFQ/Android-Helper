package helper;

import java.util.Random;

/**
 * Created by Arafat on 2015-07-19.
 */
public class RandomHelper {


    public static int generateRandomNumberInRange(int from, int to) {
        Random random = new Random();
        return random.nextInt(to - from) + from;
    }
}
