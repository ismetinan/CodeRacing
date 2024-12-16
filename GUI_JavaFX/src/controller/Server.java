// Enhancements to Server.java for handling multiple request types
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final AtomicInteger nextPort = new AtomicInteger(20000); // Starting port for dynamic lobbies
    private static ServerSocket serverSocket;

    public static void startServerInBackground(int port, String lobbyName) {
        new Thread(() -> {
            try {
                startServer(port, lobbyName);
            } catch (IOException e) {
                System.out.println("Server error: " + e.getMessage());
            }
        }).start();
    }

    public static void startServer(int port, String lobbyName) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Lobby \"" + lobbyName + "\" started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected to lobby \"" + lobbyName + "\": " + clientSocket.getInetAddress());
            new Thread(() -> handleClient(clientSocket, lobbyName)).start();
        }
    }

    private static void handleClient(Socket clientSocket, String lobbyName) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            Gson gson = new Gson();
            String input = in.readLine();
            System.out.println("Lobby \"" + lobbyName + "\" received: " + input);

            JsonObject request = gson.fromJson(input, JsonObject.class);
            JsonObject response = new JsonObject();

            String action = request.get("action").getAsString();

            switch (action) {
                case "join_lobby":
                    response.addProperty("status", "success");
                    response.addProperty("message", "Welcome to lobby \"" + lobbyName + "\"");
                    break;

                case "submit_score":
                    String username = request.get("username").getAsString();
                    int score = request.get("score").getAsInt();
                    Database.addScore(username, score);
                    response.addProperty("status", "success");
                    response.addProperty("message", "Score submitted successfully for \"" + username + "\"");
                    break;

                default:
                    response.addProperty("status", "error");
                    response.addProperty("message", "Unknown action: " + action);
                    break;
            }

            out.println(gson.toJson(response));
        } catch (IOException e) {
            System.out.println("Error handling client in lobby \"" + lobbyName + "\": " + e.getMessage());
        }
    }

    public static int getNextPort() {
        return nextPort.getAndIncrement();
    }
}