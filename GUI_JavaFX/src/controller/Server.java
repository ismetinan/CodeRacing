package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static final int PORT = 12345;
    private static final Map<String, List<PrintWriter>> lobbies = new HashMap<>();
    private static final List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        initializeLobbies(); // Predefined lobbies
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void initializeLobbies() {
        lobbies.put("Lobby 1", new ArrayList<>());
        lobbies.put("Lobby 2", new ArrayList<>());
        lobbies.put("Lobby 3", new ArrayList<>());
        System.out.println("Initialized 3 predefined lobbies.");
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            clientWriters.add(out); // Keep track of all clients
            sendLobbyList(out); // Send available lobbies on connect

            String input;
            while ((input = in.readLine()) != null) {
                JsonObject request = gson.fromJson(input, JsonObject.class);
                String action = request.get("action").getAsString();

                if ("join_lobby".equals(action)) {
                    String lobbyName = request.get("lobby_name").getAsString();
                    if (lobbies.containsKey(lobbyName)) {
                        lobbies.get(lobbyName).add(out);
                        broadcastToLobby(lobbyName, "Player joined " + lobbyName);
                        System.out.println("Player joined: " + lobbyName);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } finally {
            clientWriters.removeIf(writer -> writer.checkError());
        }
    }

    private static void sendLobbyList(PrintWriter out) {
        JsonObject response = new JsonObject();
        response.addProperty("action", "lobby_list");
        response.add("lobbies", gson.toJsonTree(lobbies.keySet()));
        out.println(gson.toJson(response));
    }

    private static void broadcastToLobby(String lobbyName, String message) {
        JsonObject response = new JsonObject();
        response.addProperty("action", "lobby_update");
        response.addProperty("message", message);

        for (PrintWriter writer : lobbies.get(lobbyName)) {
            writer.println(gson.toJson(response));
        }
    }
}
