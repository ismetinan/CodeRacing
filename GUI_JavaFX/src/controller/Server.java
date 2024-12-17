package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import guicoderacers.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    public static ArrayList<Car> carsArray= new ArrayList<>();
    private static final int PORT = 12345;
    private static final Map<String, List<PrintWriter>> lobbies = new HashMap<>();
    private static final Map<String, Map<String, Car>> lobbyCars = new HashMap<>(); // Lobby -> PlayerId -> Car
    private static final Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation() // Only serialize explicitly exposed fields
        .disableHtmlEscaping()
        .create();

    public static void main(String[] args) {
        initializeLobbies();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void initializeLobbies() {
        lobbies.put("Lobby 1", new ArrayList<>());
        lobbies.put("Lobby 2", new ArrayList<>());
        lobbies.put("Lobby 3", new ArrayList<>());
        
        lobbyCars.put("Lobby 1", new HashMap<>());
        lobbyCars.put("Lobby 2", new HashMap<>());
        lobbyCars.put("Lobby 3", new HashMap<>()); 

    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            String playerId = UUID.randomUUID().toString();
            out.println("{\"status\":\"connected\", \"playerId\":\"" + playerId + "\"}");
            
            String input;
            while ((input = in.readLine()) != null) {
                JsonObject request = gson.fromJson(input, JsonObject.class);
                String action = request.get("action").getAsString();
                String lobbyName = request.get("lobby_name").getAsString();



                if ("join_lobby".equals(action)) {
                    Car newCar = new Car(playerId, GameScreen.getCarCount());

                    carsArray.add(newCar);         
                    GameScreen.setCarCount(GameScreen.getCarCount() + 1);
                    
                    lobbyCars.get(lobbyName).put(playerId, newCar);
                    sendCarUpdate(lobbyName);
                } else if ("correct_answer".equals(action)) {
                    Car car = lobbyCars.get(lobbyName).get(playerId);
                    if (car != null);
                    sendCarUpdate(lobbyName);
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }

    private static void sendCarUpdate(String lobbyName) {
        JsonObject response = new JsonObject();
        response.addProperty("action", "update_cars");
        response.add("cars", gson.toJsonTree(lobbyCars.get(lobbyName).values()));

        for (PrintWriter writer : lobbies.get(lobbyName)) {
            writer.println(gson.toJson(response));
        }
    }
}

