import java.util.Random;

public class TokenGenerator {

    public String getRandomHash(int length) {
        Random random = new Random();
        StringBuilder hash = new StringBuilder();

        for (int i = 0; i < length; i++) {
            double number = Math.random();
            if (number < 0.3) {
                hash.append((char) (random.nextInt(26) + 'A'));
            } else if (number >= 0.3 && number < 0.6) {
                hash.append((char) (random.nextInt(26) + 'a'));
            } else {
                hash.append(random.nextInt(9));
            }
        }
        return hash.toString();
    }
}
