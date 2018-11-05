import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    private static final Logger logger = Logger.getLogger(Logger.class.getName());

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
            logger.log(Level.INFO, "Server created on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
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
