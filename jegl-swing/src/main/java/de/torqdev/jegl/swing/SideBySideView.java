package de.torqdev.jegl.swing;

import de.torqdev.jegl.awt.FloatImageFromBufferedImageConverter;
import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FilterUtil;
import de.torqdev.jegl.filters.ImageFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

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
    private BufferedImage originalImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    private JComboBox<ImageFilter> filterChooser = new JComboBox<>();

    private SideBySideView() {
        try {
            URL url = this.getClass().getClassLoader().getResource("images/buildings.jpg");
            if(url != null) {
                originalImage = ImageIO.read(url);
            }
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
        if(fileChooser.getSelectedFile() != null) {
            try {
                originalImage = ImageIO.read(fileChooser.getSelectedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        displayImages();
    }

    private Component createFilterChooser() {
        filterChooser.setModel(getComboBoxModel());
        filterChooser.addItemListener(e -> this.displayImages());
        filterChooser.setRenderer(new ClassListCellRenderer());
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

    private BufferedImage getPreviewImage() {
        AbstractFloatImageConverter<BufferedImage> converter = new FloatImageFromBufferedImageConverter();
        BufferedImage previewImage = originalImage;

        ImageFilter filter = filterChooser.getItemAt(filterChooser.getSelectedIndex());
        if (filter != null) {
            BufferedImage transformed = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = transformed.createGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();
            previewImage = converter.fromFloatImage(filter.processImage(converter.toFloatImage(transformed)));
        }

        return previewImage;
    }

    private ComboBoxModel<ImageFilter> getComboBoxModel() {
        ImageFilter[] filters = FilterUtil.getAllFilters().toArray(new ImageFilter[]{});
        return new DefaultComboBoxModel<>(filters);
    }

    private class ClassListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel myReturn = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            myReturn.setText(value.getClass().getSimpleName());
            return myReturn;
        }
    }
}
