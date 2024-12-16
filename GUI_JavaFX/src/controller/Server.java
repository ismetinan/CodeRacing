// Enhancements to Server.java for handling multiple request types
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private static final AtomicInteger nextPort = new AtomicInteger(20000); // Starting port for dynamic lobbies
    private static ServerSocket serverSocket;
    private static final List<PrintWriter> clientWriters = Collections.synchronizedList(new ArrayList<>());

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
    
            clientWriters.add(out); // Keep track of connected clients
    
            Gson gson = new Gson();
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Lobby \"" + lobbyName + "\" received: " + input);
                JsonObject request = gson.fromJson(input, JsonObject.class);
                JsonObject response = new JsonObject();
    
                String action = request.get("action").getAsString();
                switch (action) {
                    case "submit_answer":
                        boolean isCorrect = request.get("isCorrect").getAsBoolean();
                        if (isCorrect) {
                            response.addProperty("status", "success");
                            response.addProperty("message", "Correct answer!");
                            broadcastUpdate(gson.toJson(response)); // Notify all clients
                        } else {
                            response.addProperty("status", "failure");
                            response.addProperty("message", "Incorrect answer.");
                        }
                        break;
    
                    default:
                        response.addProperty("status", "error");
                        response.addProperty("message", "Unknown action.");
                        break;
                }
    
                out.println(gson.toJson(response));
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            clientWriters.removeIf(writer -> writer.checkError());
        }
    }

    public static int getNextPort() {
        return nextPort.getAndIncrement();
    }
    private static void broadcastUpdate(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}