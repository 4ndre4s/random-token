import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;

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
        DuplicatePreventer duplicatePreventer = new DuplicatePreventerVolatile();
        String token = "";
        //TODO: fix possible while true, if all tokens with specified length exist
        do {
            token = generateToken(length);
        } while (duplicatePreventer.alreadyExists(token));
        duplicatePreventer.registerToken(token);
        return token;
    }

    public String getPossibleNotUniqueToken(int length) {
        return generateToken(length);
    }

    private interface DuplicatePreventer {
        boolean alreadyExists(String token);
        void registerToken(String token);
    }

    //TODO fix: Token is marked as existing every time
    private static class DuplicatePreventerPersistent implements DuplicatePreventer {

        @Override
        public boolean alreadyExists(String token) {
            File[] existingTokens = new File("./existing_tokens/").listFiles();
            if (existingTokens != null) {
                for (File file : existingTokens) {
                    if (file.getName().equals(token)) {
                        Log.logger.log(Level.WARNING, "token already exists!");
                        return true;
                    }
                }
            }
            System.out.println("Token already exists!");
            return false;
        }

        @Override
        public void registerToken(String token) {
            Log.logger.log(Level.INFO, "token " + token + " registered");
            File file = new File("./existing_tokens/" + token);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static class DuplicatePreventerVolatile implements DuplicatePreventer{
        private static final HashSet<String> registeredTokens = new HashSet<>();

        @Override
        public void registerToken(String token) {
            Log.logger.log(Level.INFO, "token " + token + " registered");
            registeredTokens.add(token);
        }

        @Override
        public boolean alreadyExists(String token) {
            return registeredTokens.contains(token);
        }

    }
}


