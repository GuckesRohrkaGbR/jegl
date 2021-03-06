package de.torqdev.jegl.swing;

import de.torqdev.jegl.awt.FloatImageFromBufferedImageConverter;
import de.torqdev.jegl.core.AbstractFloatImageConverter;
import de.torqdev.jegl.core.FilterUtil;
import de.torqdev.jegl.filters.ImageFilter;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static java.awt.BorderLayout.*;
import static javax.swing.BoxLayout.*;
import static org.apache.log4j.Logger.*;

/**
 * @author <a href="mailto:christopher.guckes@torq-dev.de">Christopher Guckes</a>
 * @version 1.0
 */
public class SideBySideView extends JFrame {
    private static final Logger log = getLogger(SideBySideView.class);
    private JPanel originalImagePanel = new JPanel();
    private JPanel previewImagePanel = new JPanel();
    private JLabel originalLabel = new JLabel();
    private JLabel previewLabel = new JLabel();
    private transient BufferedImage originalImage = new BufferedImage(1, 1,
                                                                      BufferedImage.TYPE_INT_ARGB);
    private JComboBox<ImageFilter> filterChooser = new JComboBox<>();

    protected SideBySideView() {
        super("SideBySideView");
        try {
            URL url = this.getClass().getClassLoader().getResource("images/hourglass.png");
            if (url != null) {
                originalImage = ImageIO.read(url);
            }
        } catch (IOException e) {
            log.warn(e);
        }
        this.display();
    }

    public static void main(String[] args) {
        new SideBySideView();
    }

    public void display() {
        fillFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        displayImages();
    }

    private void displayImages() {
        originalLabel.setIcon(new ImageIcon(originalImage));
        previewLabel.setIcon(new ImageIcon(getPreviewImage()));
    }

    private void fillFrame() {
        JPanel panel = createMainPanel();
        this.getContentPane().add(panel);
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileDialog();
            }
        });
        return button;
    }

    private void openFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this.getContentPane());
        if (fileChooser.getSelectedFile() != null) {
            try {
                originalImage = ImageIO.read(fileChooser.getSelectedFile());
            } catch (IOException e) {
                log.warn(e);
            }
        }

        displayImages();
    }

    private Component createFilterChooser() {
        filterChooser.setModel(getComboBoxModel());
        filterChooser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                displayImages();
            }
        });
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
        AbstractFloatImageConverter<BufferedImage> converter = new
                FloatImageFromBufferedImageConverter();
        BufferedImage previewImage = originalImage;

        ImageFilter filter = filterChooser.getItemAt(filterChooser.getSelectedIndex());
        if (filter != null) {
            BufferedImage transformed = new BufferedImage(originalImage.getWidth(),
                                                          originalImage.getHeight(),
                                                          BufferedImage.TYPE_INT_RGB);
            Graphics2D g = transformed.createGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();
            previewImage = converter.fromFloatImage(
                    filter.processImage(converter.toFloatImage(transformed)));
        }

        return previewImage;
    }

    private ComboBoxModel<ImageFilter> getComboBoxModel() {
        ImageFilter[] filters = FilterUtil.getAllFilters().toArray(new ImageFilter[]{});
        return new DefaultComboBoxModel<>(filters);
    }

    private class ClassListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel myReturn = (JLabel) super.getListCellRendererComponent(list, value, index,
                                                                          isSelected, cellHasFocus);
            myReturn.setText(value.getClass().getSimpleName());
            return myReturn;
        }
    }
}
