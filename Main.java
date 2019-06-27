import Controller.Controller;
import GUI.JpotifyGUI;
import GUI.LoginPage;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.setupJpotify();
    }
}
