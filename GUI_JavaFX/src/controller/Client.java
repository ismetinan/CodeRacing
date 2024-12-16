// Enhancements to Client.java for sending various requests
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.*;
import java.net.Socket;

public class Client {
    private String serverHost;
    private int serverPort;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson;

    public Client(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.gson = new Gson();
    }

    public void connect() {
        try {
            socket = new Socket(serverHost, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to the server on port " + serverPort);
        } catch (IOException e) {
            System.out.println("Client connection error: " + e.getMessage());
        }
    }

    public String sendRequest(String action, JsonObject payload) {
        try {
            JsonObject request = new JsonObject();
            request.addProperty("action", action);
            request.add("payload", payload);

            out.println(gson.toJson(request)); // Send request
            return in.readLine();             // Read response
        } catch (IOException e) {
            System.out.println("Error sending request: " + e.getMessage());
        }
        return null;
    }

    public void disconnect() {
        try {
            if (socket != null) socket.close();
            if (out != null) out.close();
            if (in != null) in.close();
            System.out.println("Disconnected from the server.");
        } catch (IOException e) {
            System.out.println("Error disconnecting: " + e.getMessage());
        }
    }
}
