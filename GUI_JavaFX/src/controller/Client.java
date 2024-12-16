// Enhancements to Client.java for sending various requests
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import guicoderacers.GameScreen;

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
public void listenForUpdates(GameScreen gameScreen) {
    new Thread(() -> {
        try {
            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                System.out.println("Server update: " + serverResponse);
                JsonObject response = gson.fromJson(serverResponse, JsonObject.class);

                if (response.has("action") && "update_positions".equals(response.get("action").getAsString())) {
                    JsonArray carUpdates = response.getAsJsonArray("cars");
                    for (JsonElement carElement : carUpdates) {
                        JsonObject carData = carElement.getAsJsonObject();
                        String playerId = carData.get("playerId").getAsString();
                        double x = carData.get("x").getAsDouble();
                        double y = carData.get("y").getAsDouble();

                        gameScreen.updateCarPosition(playerId, x, y);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error listening for updates: " + e.getMessage());
        }
    }).start();
}

}
