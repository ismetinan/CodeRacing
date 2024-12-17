package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import guicoderacers.GameScreen;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Client instance;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson;

    public Client(String serverHost, int serverPort) {
        gson = new Gson();
        try {
            socket = new Socket(serverHost, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to server on port " + serverPort);
        } catch (IOException e) {
            System.out.println("Failed to connect to server: " + e.getMessage());
        }
    }

    public void requestLobbies() {
        try {
            String response;
            while ((response = in.readLine()) != null) {
                JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
                String action = jsonResponse.get("action").getAsString();

                if ("lobby_list".equals(action)) {
                    System.out.println("Available Lobbies: " + jsonResponse.get("lobbies"));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from server: " + e.getMessage());
        }
    }

    public void joinLobby(String lobbyName) {
        JsonObject request = new JsonObject();
        request.addProperty("action", "join_lobby");
        request.addProperty("lobby_name", lobbyName);
        out.println(gson.toJson(request));
    }
    public static Client getInstance(String serverHost, int serverPort) {
        if (instance == null) {
            instance = new Client(serverHost, serverPort);
        }
        return instance;
    }
    public void submitCorrectAnswer(String lobbyName) {
    JsonObject request = new JsonObject();
    request.addProperty("action", "correct_answer");
    request.addProperty("lobby_name", lobbyName);
    out.println(gson.toJson(request));
}

public void listenForUpdates(GameScreen gameScreen) {
    new Thread(() -> {
        try {
            String response;
            while ((response = in.readLine()) != null) {
                JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
                if ("update_cars".equals(jsonResponse.get("action").getAsString())) {
                    JsonArray cars = jsonResponse.getAsJsonArray("cars");
                    gameScreen.updateCarPositions(cars);
                }
            }
        } catch (IOException e) {
            System.out.println("Disconnected: " + e.getMessage());
        }
    }).start();
}

}
