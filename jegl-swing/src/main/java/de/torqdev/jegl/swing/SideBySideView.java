package de.torqdev.jegl.swing;

import de.torqdev.jegl.awt.FloatImageFromBufferedImageConverter;
import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.filters.ImageFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.PAGE_START;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class SideBySideView {
    private JFrame frame;
    private JPanel originalImagePanel = new JPanel();
    private JPanel previewImagePanel = new JPanel();
    private JLabel originalLabel = new JLabel();
    private JLabel previewLabel = new JLabel();
    private BufferedImage originalImage;
    private JComboBox<ImageFilter> filterChooser = new JComboBox<>();

    public SideBySideView() {
        try {
            originalImage = ImageIO.read(this.getClass().getClassLoader().getResource("images/buildings.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new JFrame("SideBySideView");
    }

    public static void main(String[] args) {
        SideBySideView window = new SideBySideView();
        window.show();
    }

    private void show() {
        fillFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        displayImages();
    }

    private void displayImages() {
        originalLabel.setIcon(new ImageIcon(originalImage));
        previewLabel.setIcon(new ImageIcon(getPreviewImage()));
    }

    private void fillFrame() {
        JPanel panel = createMainPanel();
        frame.getContentPane().add(panel);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel headPanel = createHeadPanel();
        panel.add(headPanel, PAGE_START);
        JPanel corePanel = createCorePanel();
        panel.add(corePanel, CENTER);
        return panel;
    }

    private JPanel createHeadPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setLayout(new BoxLayout(headPanel, X_AXIS));
        headPanel.add(createOpenFileButton());
        headPanel.add(createFilterChooser());
        return headPanel;
    }

    private Component createOpenFileButton() {
        JButton button = new JButton("Open File");
        button.addActionListener(e -> openFileDialog());
        return button;
    }

    private void openFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(frame.getContentPane());
        try {
            originalImage = ImageIO.read(fileChooser.getSelectedFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayImages();
    }

    private Component createFilterChooser() {
        return filterChooser;
    }

    private JPanel createCorePanel() {
        JPanel corePanel = new JPanel();
        corePanel.setLayout(new BoxLayout(corePanel, X_AXIS));
        corePanel.add(originalImagePanel);
        corePanel.add(previewImagePanel);
        originalImagePanel.add(originalLabel);
        previewImagePanel.add(previewLabel);
        return corePanel;
    }

    public BufferedImage getPreviewImage() {
        AbstractFloatImageConverter<BufferedImage> converter = new FloatImageFromBufferedImageConverter();
        BufferedImage previewImage = originalImage;

        ImageFilter filter = filterChooser.getItemAt(filterChooser.getSelectedIndex());
        if (filter != null) {
            previewImage = converter.fromFloatImage(filter.processImage(converter.toFloatImage(previewImage)));
        }

        return previewImage;
    }
}
