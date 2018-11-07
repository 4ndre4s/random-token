import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;


public class Server {

    public static void main(String[] args) {
        Server webserver = new Server();
    }


    public Server() {
        this(2089);
    }

    public Server(int port) {
        HttpServer server = null;
        HttpResponseSender httpResponseSender = new HttpResponseSender();
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            Log.logger.log(Level.INFO, "Server created on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
            Log.logger.log(Level.WARNING, e.getMessage());
        }

        if (server != null) {
            server.createContext("/", new TokenHttpHandler());
            server.createContext("/readme", httpExchange -> {
               httpResponseSender.sendHtmlFile(httpExchange, "./README.md");
            });
            server.setExecutor(null);
            server.start();
        }
    }
}
