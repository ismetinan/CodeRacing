import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        Gson gson = new Gson();

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Create a request message
            Message request = new Message("request", "Code Racer");
            String jsonRequest = gson.toJson(request);

            // Send the JSON request to the server
            writer.println(jsonRequest);
            System.out.println("Sent: " + jsonRequest);

            // Read JSON response from the server
            String jsonResponse = reader.readLine();
            System.out.println("Received: " + jsonResponse);

            // Deserialize JSON response into a Message object
            Message response = gson.fromJson(jsonResponse, Message.class);
            System.out.println("Response content: " + response.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
