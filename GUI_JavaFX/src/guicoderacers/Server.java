import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345; // Port for the server

    public static void main(String[] args) {
        Gson gson = new Gson();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                // Create input and output streams
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    // Read JSON request from client
                    String jsonRequest = reader.readLine();
                    System.out.println("Received: " + jsonRequest);

                    // Deserialize JSON into a Message object
                    Message request = gson.fromJson(jsonRequest, Message.class);

                    // Process the request
                    String responseContent = "Hello, " + request.getContent() + "!";
                    Message response = new Message("response", responseContent);

                    // Serialize the response object to JSON
                    String jsonResponse = gson.toJson(response);
                    writer.println(jsonResponse);
                    System.out.println("Sent: " + jsonResponse);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
