package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private static final int PORT = 12345;
    private static final Map<String, List<PrintWriter>> lobbies = new HashMap<>();
    private static final List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();
    private static final Gson gson = new Gson();
    private static final Map<Integer, String> userPasswords = new HashMap<>();
    private static int clientNum = 1;

    public static void main(String[] args) {
        initializeLobbies(); // Predefined lobbies
        initializePasswords(); // Initialize predefined passwords for clients
        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName("10.204.113.233"))) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                int assignedClientNum = clientNum;
                String assignedPassword = userPasswords.getOrDefault(clientNum, "DEFAULT");
                clientNum++;

                // Start handling the client
                new Thread(() -> handleClient(clientSocket, assignedClientNum, assignedPassword)).start();
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

    private static void initializePasswords() {
        userPasswords.put(1, "AZ");
        userPasswords.put(2, "BZ");
        userPasswords.put(3, "CZ");
        userPasswords.put(4, "DZ");
        userPasswords.put(5, "EZ");
        System.out.println("Initialized passwords for clients.");
    }
    public static String getUserPasswordsAsJson() {
        // Convert the HashMap into a JSON String
        JsonObject json = new JsonObject();
        json.add("userPasswords", gson.toJsonTree(userPasswords));
        return gson.toJson(json);
    }


    private static void handleClient(Socket clientSocket, int clientNum, String password) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            clientWriters.add(out); // Keep track of all clients
            sendLobbyList(out); // Send available lobbies on connect
            sendPasswordToClient(out, clientNum, password); // Send password information to the client
            out.println(getUserPasswordsAsJson());
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

    private static void sendPasswordToClient(PrintWriter out, int clientNum, String password) {
        JsonObject response = new JsonObject();
        response.addProperty("action", "assign_password");
        response.addProperty("client_number", clientNum);
        response.addProperty("password", password);
        out.println(gson.toJson(response));
        System.out.println("Sent JSON: " + gson.toJson(response));

        System.out.println("Assigned password '" + password + "' to client " + clientNum);
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
