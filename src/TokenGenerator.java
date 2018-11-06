import java.util.Random;

public class TokenGenerator {

    private String generateToken(int length) {
        Random random = new Random();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            double number = Math.random();
            if (number < 0.3) {
                tokenBuilder.append((char) (random.nextInt(26) + 'A'));
            } else if (number >= 0.3 && number < 0.6) {
                tokenBuilder.append((char) (random.nextInt(26) + 'a'));
            } else {
                tokenBuilder.append(random.nextInt(9));
            }
        }
        return tokenBuilder.toString();
    }

    public String getUniqueToken(int length) {
        String token = "";
        //possible while true, if all tokens with specified length exist
        do {
            token = generateToken(length);
        } while (DuplicatePermitter.alreadyExists(token));
        DuplicatePermitter.registerToken(token);
        return token;
    }

    public String getToken(int length) {
        return generateToken(length);
    }
}
