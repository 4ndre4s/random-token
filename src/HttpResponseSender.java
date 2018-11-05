import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public void sendHtmlFile(HttpExchange httpExchange, String path) {
        try {
            byte[] responseBytes = Files.readAllBytes(Paths.get(path));
            httpExchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(responseBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
