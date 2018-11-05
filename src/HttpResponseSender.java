import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpResponseSender {
    public void sendPlainText(HttpExchange httpExchange, String response) {
        try {
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
            httpExchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(responseBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
