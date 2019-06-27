package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MusicPlayerArea extends JPanel{
    private JSlider timeSlider = new JSlider(0,1000,0);
    private JSlider soundSlider = new JSlider();
    private JButton replayButton = new JButton();
    private JButton playButton = new JButton();
    private JButton forwardButton = new JButton();
    private JButton backwardButton = new JButton();
    private JButton addToPlayList = new JButton();
    private JLabel totalTime = new JLabel("0:00");
    private JLabel timePassed = new JLabel("00:00");
    private JLabel songName = new JLabel();
    private JLabel songSinger = new JLabel();
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel southPanel = new JPanel(new FlowLayout());
    private JPanel westPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JPanel gridSongInfo = new JPanel(new GridLayout(2,1));
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);


    public MusicPlayerArea() throws IOException {
        super();
        this.setLayout(new BorderLayout());
        prepareButtonToAdd(replayButton,"Icons/replayIconWhite.png",20);
        prepareButtonToAdd(playButton,"Icons/play.png",40);
        prepareButtonToAdd(backwardButton,"Icons/backwardIconWhite.png",20);
        prepareButtonToAdd(forwardButton,"Icons/forwardIconWhite.png",20);
        prepareButtonToAdd(addToPlayList,"Icons/listFreeIconWhite.png",20);
        prepareButtonPanelToAdd(NEAR_VERY_DARK_GRAY);
        prepareNorthPanelToAdd(NEAR_VERY_DARK_GRAY);
        prepareSouthPanelToAdd(NEAR_VERY_DARK_GRAY);
        prepareWestPanelToAdd(NEAR_VERY_DARK_GRAY);
        prepareCenterPanelToAdd(NEAR_VERY_DARK_GRAY);
        prepareGridSongInfoPanelToAdd(new Dimension(200,70),NEAR_VERY_DARK_GRAY);
        prepareSliderToAdd(soundSlider,NEAR_VERY_DARK_GRAY,new Dimension(70,0));
        //still have problem with this line
        prepareSliderToAdd(timeSlider,NEAR_VERY_DARK_GRAY,new Dimension(500,14));
        prepareLabelToAdd(totalTime,Color.WHITE);
        prepareLabelToAdd(timePassed,Color.WHITE);
        prepareSongInfoToAdd(songName,Color.WHITE,"Your Safe With Me",addFont("Fonts/greataris_destain-alternative/destain.ttf",19));
        prepareSongInfoToAdd(songSinger,Color.WHITE,"Sheriff",addFont("Fonts/greataris_destain-alternative/destain.ttf",14));
        this.add(westPanel,BorderLayout.WEST);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(soundSlider,BorderLayout.EAST);
        soundSlider.setMaximum(100);
        soundSlider.setMinimum(0);
    }
    private void prepareButtonPanelToAdd (Color backGroundColor){
        buttonPanel.add(replayButton);
        buttonPanel.add(backwardButton);
        buttonPanel.add(playButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(addToPlayList);
        buttonPanel.setBackground(backGroundColor);
    }
    private void prepareNorthPanelToAdd(Color backGroundColor) {
        northPanel.add(buttonPanel,BorderLayout.CENTER);
        northPanel.setBackground(backGroundColor);

    }
    private void prepareSouthPanelToAdd(Color backGroundColor){
        southPanel.add(timePassed);
        southPanel.add(timeSlider);
        southPanel.add(totalTime);
        southPanel.setBackground(backGroundColor);

    }
    private void prepareWestPanelToAdd(Color backGroundColor){
        westPanel.add(gridSongInfo,BorderLayout.NORTH);
        westPanel.setBackground(backGroundColor);
    }
    private void prepareCenterPanelToAdd(Color backGroundColor){
        centerPanel.add(southPanel,BorderLayout.SOUTH);
        centerPanel.add(northPanel,BorderLayout.NORTH);
        centerPanel.setBackground(backGroundColor);
    }
    private void prepareGridSongInfoPanelToAdd(Dimension dimension,Color backGroundColor){
        gridSongInfo.setMaximumSize(dimension);
        gridSongInfo.add(songName);
        gridSongInfo.add(songSinger);
        gridSongInfo.setBackground(backGroundColor);
    }
    private void prepareSongInfoToAdd(JLabel label,Color forGroundColor,String text,Font font){
        label.setForeground(forGroundColor);
        label.setText(text);
        label.setFont(font);
    }
    private void prepareSliderToAdd(JSlider slider,Color backGroundColor , Dimension dimension){
        slider.setBackground(backGroundColor);
        slider.setPreferredSize(dimension);
    }
    private void prepareButtonToAdd(JButton btn,String path,int size) throws IOException {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(size,size));
        setImageIconForButton(path,btn,size);
        btn.setBackground(NEAR_VERY_DARK_GRAY);
    }
    private void prepareLabelToAdd(JLabel label,Color forGroundColor){
        label.setForeground(forGroundColor);
    }
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = getScaledInstance(img,size,size,RenderingHints.VALUE_INTERPOLATION_BICUBIC,true);
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        btn.setIcon(new ImageIcon(finalImg));
    }
    /**
     * Convenience method that returns a scaled instance of the
     * provided {@code BufferedImage}.
     *
     * @param img the original image to be scaled
     * @param targetWidth the desired width of the scaled instance,
     *    in pixels
     * @param targetHeight the desired height of the scaled instance,
     *    in pixels
     * @param hint one of the rendering hints that corresponds to
     *    {@code RenderingHints.KEY_INTERPOLATION} (e.g.
     *    {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     *    {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     *    {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     * @param higherQuality if true, this method will use a multi-step
     *    scaling technique that provides higher quality than the usual
     *    one-step technique (only useful in downscaling cases, where
     *    {@code targetWidth} or {@code targetHeight} is
     *    smaller than the original dimensions, and generally only when
     *    the {@code BILINEAR} hint is specified)
     * @return a scaled version of the original {@code BufferedImage}
     */
    public BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality) {
        int type = (img.getTransparency() == Transparency.OPAQUE) ?
                BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage)img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }
    private Font addFont(String path,float size)  {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException e) {
            System.out.println("the font wan not there");
        } catch (IOException e) {
            System.out.println("the font wan not there");
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(customFont);
        return customFont;
    }
    public JSlider getTimeSlider() {
        return timeSlider;
    }
    public JSlider getSoundSlider() {
        return soundSlider;
    }
    public JButton getReplayButton() {
        return replayButton;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public JButton getForwardButton() {
        return forwardButton;
    }
    public JButton getBackwardButton() {
        return backwardButton;
    }
    public JButton getAddToPlayList() {
        return addToPlayList;
    }
    public JLabel getTotalTime() {
        return totalTime;
    }
    public JLabel getTimePassed() {
        return timePassed;
    }
    public JLabel getSongName() {
        return songName;
    }
    public JLabel getSongSinger() {
        return songSinger;
    }
}
