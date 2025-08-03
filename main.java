import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Journal Writing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Top panel with date (right) and font combo (left)
        String today = LocalDate.now().toString();
        JLabel dateLabel = new JLabel("Today is " + today);
        dateLabel.setFont(new Font("monestra", Font.BOLD, 14));

        String[] fonts = {"Serif", "Monospaced", "SansSerif"};
        JComboBox<String> fontCombo = new JComboBox<>(fonts);
        fontCombo.setSelectedIndex(0);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(fontCombo, BorderLayout.WEST);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        datePanel.add(dateLabel);
        topPanel.add(datePanel, BorderLayout.EAST);

        // Text area
        JTextArea textArea = new JTextArea(15, 40);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(textArea);

        fontCombo.addActionListener(e -> {
            String selectedFont = (String) fontCombo.getSelectedItem();
            if (selectedFont != null) {
                textArea.setFont(new Font(selectedFont, Font.PLAIN, 16));
            }
        });

