import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class DuplicatePreventer {
    public static boolean alreadyExists(String token) {
        File[] existingTokens = new File("./existing_tokens/").listFiles();
        if (existingTokens != null) {
            for (File file : existingTokens) {
                if (file.getName().equals(token)) {
                    Server.logger.log(Level.WARNING, "token already exists!");
                    return true;
                }
            }
        }
        System.out.println("Token already exists!");
        return false;
    }

    public static void registerToken(String token) {
        Server.logger.log(Level.INFO, "token " + token + " registered");
       File file = new File("./existing_tokens/" + token);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
