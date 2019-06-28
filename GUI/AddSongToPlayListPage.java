package GUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;

public class AddSongToPlayListPage extends JFrame {
    private final String WINDOWS_TITLE = "Add Song To PlayList";
    private final int WIDTH = 500, HEIGHT = 400;
    private final int X = 500, Y = 250;
    private JButton addSongTOPlayListButton = new JButton("Add");
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);
    private Color LIGHT_GRAY = new Color(70,70,70);
    private JPanel motherPanel = new JPanel();
    private JPanel buttonPanel = new JPanel(new GridLayout(1,1));
    private BoxLayout boxLayout = new BoxLayout(motherPanel,BoxLayout.PAGE_AXIS);
    private JScrollPane scrollPane = new JScrollPane();
    public AddSongToPlayListPage(ArrayList<PlayList> playLists){
        super();
        this.setLayout(new BorderLayout());
        this.setTitle(WINDOWS_TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(VERY_DARK_GRAY);
        this.setLocation(X, Y);
        addSongTOPlayListButton.setBorderPainted(false);
        addSongTOPlayListButton.setFocusPainted(false);
        addSongTOPlayListButton.setMaximumSize(new Dimension(140,50));
        addSongTOPlayListButton.setBackground(LIGHT_GRAY);
        addSongTOPlayListButton.setForeground(Color.WHITE);
        buttonPanel.setMaximumSize(new Dimension(140,50));
        motherPanel.setBackground(VERY_DARK_GRAY);
        motherPanel.setLayout(boxLayout);
        motherPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        motherPanel.setMaximumSize(new Dimension(300,350));
        for(PlayList playList:playLists){
            JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox();
            JLabel playListName = new JLabel();
            Font playListNameFont = new Font("optima",Font.BOLD,14);
            playListName.setText(playList.getName());
            playListName.setFont(playListNameFont);
            playListName.setForeground(Color.WHITE);
            playListName.setBackground(NEAR_VERY_DARK_GRAY);
            flowPanel.setMaximumSize(new Dimension(300,50));
            flowPanel.setBackground(NEAR_VERY_DARK_GRAY);
            checkBox.setBackground(NEAR_VERY_DARK_GRAY);
            flowPanel.add(checkBox);
            flowPanel.add(playListName);
            motherPanel.add(flowPanel);
        }
        motherPanel.add(Box.createRigidArea(new Dimension(0, 14)));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(VERY_DARK_GRAY));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        scrollPane.setViewportView(motherPanel);
        buttonPanel.add(addSongTOPlayListButton);
        motherPanel.add(buttonPanel);
        this.add(scrollPane);
//        this.add(addSongTOPlayListButton);
        this.setVisible(true);
    }
}
