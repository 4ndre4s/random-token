import java.io.File;
import java.util.HashSet;

public class DuplicatePermitter {
    public boolean alreadyExists(String token) {
        File[] existingTokens = new File("./existing_tokens/").listFiles();
        for (File file : existingTokens) {
            if (file.getName().equals(token)) {
                return false;
            }
        }
        return true;
    }

    public void registerToken(String token) {
        //TODO: create file with name of token
    }
}
