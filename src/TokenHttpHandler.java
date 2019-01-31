import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.logging.Level;

public class TokenHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        Log.logger.log(Level.INFO, "requested: " + httpExchange.getRequestURI());

        String request = httpExchange.getRequestURI().toString().replace("/", "");
        HttpResponseSender httpResponseSender = new HttpResponseSender();

        httpResponseSender.sendPlainText(httpExchange, generateResponse(request));
    }

    private String generateResponse(String request) {
        final int MAX_LENGTH = 200;

        if (!request.matches("[0-9]+")) {
            return "Please specify the length of your token via http://random-token.xyz/LENGTH"
                    + "\nLENGTH must be an int between 1 and " + MAX_LENGTH + "!";
        }

        int length;

        try {
            length = Integer.parseInt(request);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return  "LENGTH must be less than " + (MAX_LENGTH + 1) + "!";
        }

        if (length < 0) {
            return "LENGTH must be greater than 0";
        }

        if (length > MAX_LENGTH) {
            return "LENGTH must be less than "  + (MAX_LENGTH + 1) + "!";
        }

        TokenGenerator tokenGenerator = new TokenGenerator();
        return tokenGenerator.getUniqueToken(length);
    }
}
