import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TokenHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) {
        String request = httpExchange.getRequestURI().toString().replace("/", "");
        HttpResponseSender httpResponseSender = new HttpResponseSender();

        String response;

        final int MAX_LENGTH = 200;
        //TODO: create new class for case distinction
        if (request.matches("[0-9]+")) {
            int length = 0;
            try {
                length = Integer.parseInt(request);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response = "LENGTH must be less than " + (MAX_LENGTH + 1) + "!";
                httpResponseSender.sendPlainText(httpExchange, response);
            }
            if (length > 0 && length < 201) {
                TokenGenerator tokenGenerator = new TokenGenerator();
                response = tokenGenerator.getUniqueToken(length);
            } else {
                response = "LENGTH must be less than "  + (MAX_LENGTH + 1) + "!";
            }
        } else {
            response = "Please specify the length of your token via http://random-token.xyz/LENGTH"
            + "\nLENGTH must be an int between 1 and " + MAX_LENGTH + "!";
        }

        httpResponseSender.sendPlainText(httpExchange, response);
    }
}
