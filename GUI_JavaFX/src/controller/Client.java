package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;

public class Client {
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
}
