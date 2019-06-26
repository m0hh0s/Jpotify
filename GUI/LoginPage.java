package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private final String WINDOWS_TITLE = "Login";
    private final int WIDTH = 500, HEIGHT = 400;
    private final int X = 140, Y = 140;
    private JTextField id = new JTextField();
    private JButton loginButton = new JButton("Login");
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);
    private JPanel textPanel = new JPanel(new GridLayout(1,1));
    private JPanel buttonPanel = new JPanel(new GridLayout(1,1));
    private JPanel motherPanel = new JPanel();
    private BoxLayout boxLayout = new BoxLayout(motherPanel,BoxLayout.PAGE_AXIS);
    public LoginPage(){
        super();
        this.setLayout(new BorderLayout());
        this.setTitle(WINDOWS_TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
        motherPanel.setLayout(boxLayout);
//        motherPanel.setMaximumSize(new Dimension(350,120));
        textPanel.setMaximumSize(new Dimension(250,40));
        buttonPanel.setMaximumSize(new Dimension(250,50));
        textPanel.add(id);
        buttonPanel.add(loginButton);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(VERY_DARK_GRAY);
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(350,50));
        loginButton.setSize(new Dimension(350,50));
        motherPanel.add(Box.createRigidArea(new Dimension(5,140)));
        motherPanel.add(textPanel);
        motherPanel.add(buttonPanel);
        this.add(motherPanel);
        this.setVisible(true);
    }
}
