import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpResponseSender {
    public void sendPlainText(HttpExchange httpExchange, String response) {
            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);
            sendHttpResponse(httpExchange, responseBytes);
    }

    public void sendHtmlFile(HttpExchange httpExchange, String path) {
        byte[] responseBytes = new byte[0];
        try {
            responseBytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendHttpResponse(httpExchange, responseBytes);
    }

    private void sendHttpResponse(HttpExchange httpExchange, byte[] responseBytes) {
        try {
            httpExchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(responseBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
