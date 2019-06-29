package Logic;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkHandler{
    protected static ArrayList<RecentActivity> friendActivity = new ArrayList<>();
    private static Socket mySocket;
    private static Gson gson = new Gson();

    public static void sendData(){
        try {
            mySocket =  new Socket("localhost",1999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        if (MusicPlayer.getCurrentlyPlaying() != null){
                            if (User.getInstance().getMusicLibrary().getPlaylists().get(1).getSongs().contains(MusicPlayer.getCurrentlyPlaying())){
                                DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());
                                String username = User.getInstance().getUsername();
                                String songTitle = MusicPlayer.getCurrentlyPlaying().getTitle();
                                String artistName = MusicPlayer.getCurrentlyPlaying().getArtistName();
                                long timeListenedTo = MusicPlayer.getCurrentlyPlaying().getDateListenedTo();
                                RecentActivity rc = new RecentActivity(username , songTitle , artistName , timeListenedTo);
                                out.writeBytes(gson.toJson(rc));
                            }
                        }
                        //Thread.currentThread().wait(10000);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public static void receiveData(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
                        RecentActivity rc  = (RecentActivity) in.readObject();
                        System.out.println("received " + rc);
                        for (RecentActivity recentActivity : friendActivity){
                            if (recentActivity.getUserName().equals(rc.getUserName())){
                                recentActivity.update(rc);
                            }
                        }
                    }catch (IOException | ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
