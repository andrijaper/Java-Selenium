package automaticity_academy.utils;

import java.util.Random;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class General {

    public static String generateRandomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            result.append(alphabet.charAt(index));
        }
        return result.toString();
    }

    public static int generateRandomInt(int length) {
        String numbers = "123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numbers.length());
            result.append(numbers.charAt(index));
        }
        return Integer.parseInt(result.toString());
    }

    public static long generateRandomNumber(long min, long max) {
        Random random = new Random();
        return min + (long) (random.nextDouble() * (max - min + 1));
    }

    public static LocalDate generateRandomDateBetween(LocalDate start, LocalDate end) {
        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomEpochDay);
    }
}
