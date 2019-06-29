package Logic;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class JpotifyServer {
    private static ArrayList<Socket> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    private JpotifyServer(){
        try {
            serverSocket = new ServerSocket(1999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket newClient = serverSocket.accept();
                        System.out.println("server: " + newClient + " connected");
                        clients.add(newClient);
                        ClientHandler ch = new ClientHandler(newClient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        serverThread.start();
    }
    static ArrayList<Socket> getClients() {
        return clients;
    }
    public static void main(String[] args) {
        JpotifyServer jpotifyServer = new JpotifyServer();
    }
}

class ClientHandler {
    private Socket clientToHandle;
    private DataInputStream in;
    private BufferedReader bufferedReader;
    private Gson gson = new Gson();
    ClientHandler(Socket socket) {
        clientToHandle = socket;
        handle();
    }

    private void handle(){
        try {
            in = new DataInputStream(clientToHandle.getInputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            bufferedReader = new BufferedReader(inputStreamReader);

        }catch (IOException e){
            e.printStackTrace();
        }
        Thread clientHandlerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println("here");
                        RecentActivity rc = gson.fromJson( bufferedReader.readLine() , RecentActivity.class);
                        System.out.println("server: received " + rc);
                        for (Socket target : JpotifyServer.getClients()){
                            if (target != clientToHandle){
                                ObjectOutputStream out = new ObjectOutputStream(target.getOutputStream());
                                out.writeObject(rc);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        clientHandlerThread.start();
    }
}
