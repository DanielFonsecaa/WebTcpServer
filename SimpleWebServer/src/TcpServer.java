

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static void main(String[] args) {
        int portNumber = 8081;


        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            System.out.println("acepted");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String[] input = in.readLine().split(" ");
            String resource = input[1];

            if (resource.equals("/index.html")) {
                File file = new File("src/rsc/index.html");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String html = "HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length:" + file.length() + " \r\n" +
                        "\r\n";
                String line;
                String result = html;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "\n";
                }
                System.out.println(html);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(result);
                clientSocket.close();
            }

            if (resource.equals("/logo.png")) {
                File png = new File("src/rsc/logo.png");
                FileInputStream fileInputStream = new FileInputStream(png);
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                byte[] buffer = new byte[1024];
                int numBytes;
                String image = "HTTP/1.0 200 Document Follows\r\n" +
                        "Content-Type: image/png\r\n" +
                        "Content-Length:" + png.length() + "\r\n" +
                        "\r\n";
                String result = image;
                out.write(image.getBytes());
                while ((numBytes = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, numBytes);
                }

                System.out.println(result);
                fileInputStream.close();
                out.close();
            }
            if (!resource.equals("/logo.png") && !resource.equals("/index.html")) {
                File file = new File("src/rsc/404.html");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String notFound = "HTTP/1.0 404 Not Found\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length:" + file.length() + "\r\n" +
                        "\r\n";
                String line;
                String result = notFound;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "\n";
                }
                System.out.println(notFound);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(result);
                clientSocket.close();
            }

        } catch (IOException e) {
            System.out.println("error");
        }

    }
}