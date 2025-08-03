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

        // Save button
        JButton saveButton = new JButton("Save Entry");
        saveButton.setBackground(new Color(0, 120, 215));
        saveButton.setForeground(Color.BLACK);
        saveButton.setFocusPainted(false);

        saveButton.addActionListener(e -> {
            String entry = textArea.getText().trim();
            if (entry.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please write something before saving.");
                return;
            }

            try {
                FileWriter fw = new FileWriter("journal_" + today + ".txt", true);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.write(entry);
                writer.newLine();
                writer.newLine();
                writer.close();

                JOptionPane.showMessageDialog(frame, "Journal entry saved!");
                textArea.setText("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Frame layout
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(30, 15, 15, 15));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

