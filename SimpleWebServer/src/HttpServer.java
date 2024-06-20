import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.file.Files;

public class HttpServer {

    public static void main(String[] args) {

        int portNumber = 8080;
        ServerSocket serverSocket;
        BufferedReader in;
        PrintWriter out;

        try {
            com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/", new Handler404());
            server.createContext("/logo.png", new HandlerPng());

            server.createContext("/index.html", new Handler());

            server.setExecutor(null);
            server.start();

            System.out.println("server port 8080");

        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public static class HandlerPng implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = new File("src/rsc/logo.png");
            exchange.sendResponseHeaders(200, file.length());
            OutputStream os = exchange.getResponseBody();
            Files.copy(file.toPath(), os);
            String png = "HTTP/1.0 200 Document Follows\r\n" +
                    "Content-Type: image/<image_file_extension> \r\n" +
                    "Content-Length: <file_byte_size> \r\n" +
                    "\r\n";
            System.out.println(png);
            os.close();
        }
    }

    public static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = new File("src/rsc/index.html");
            exchange.sendResponseHeaders(200, file.length());
            OutputStream os = exchange.getResponseBody();
            Files.copy(file.toPath(), os);
            String html = "HTTP/1.0 200 Document Follows\r\n" + "Content-Type: text/html; charset=UTF-8\r\n" + "Content-Length: <file_byte_size> \r\n" + "\r\n";
            System.out.println(html);
            os.close();
        }
    }

    public static class Handler404 implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = new File("src/rsc/404.html");
            exchange.sendResponseHeaders(404, file.length());
            OutputStream os = exchange.getResponseBody();
            Files.copy(file.toPath(), os);
            String notFound = "HTTP/1.0 404 Not Found\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: <file_byte_size> \r\n" +
                    "\r\n";
            System.out.println(notFound);
            os.close();
        }
    }

  /*private static String httpHeader(int returnCode, int typeOfFile){

    }*/
}
