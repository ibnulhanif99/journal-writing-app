package Journl_App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;

public class JournalApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("🌿 Journal Writing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 250, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Top, Left, Bottom, Right

        String today = LocalDate.now().toString();
        JLabel label = new JLabel("📖 Today is " + today);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title Field
        JTextField titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(300, 30));
        titleField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));

        // Mood dropdown
        String[] moods = {"😊 Happy", "😢 Sad", "😄 Excited", "😌 Calm", "😴 Tired"};
        JComboBox<String> moodBox = new JComboBox<>(moods);
        moodBox.setMaximumSize(new Dimension(300, 30));
        moodBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        moodBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Text area for journal entry
        JTextArea textArea = new JTextArea(40     , 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Save button
        JButton saveButton = new JButton("💾 Save Entry");
        saveButton.setBackground(new Color(200, 120, 215));
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // View button
        JButton viewButton = new JButton("📂 View Saved Entries");
        viewButton.setBackground(new Color(0, 150, 100));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        viewButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(titleField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(moodBox);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));
        panel.add(saveButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(viewButton);

        frame.add(panel);
        frame.setVisible(true);

        // Save entry to file
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mood = (String) moodBox.getSelectedItem();
                String title = titleField.getText().trim();
                String entry = textArea.getText().trim();

                if (entry.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please write something before saving.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (title.isEmpty()) {
                    title = "Untitled";
                }

                String content = "Date: " + today + "\nTitle: " + title + "\nMood: " + mood + "\n\n" + entry + "\n\n---\n";

                try {
                    Files.write(Paths.get("journal_entries.txt"), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    JOptionPane.showMessageDialog(frame, "Entry saved successfully!");
                    textArea.setText("");
                    titleField.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving entry: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View saved entries
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Path path = Paths.get("journal_entries.txt");
                    if (!Files.exists(path)) {
                        JOptionPane.showMessageDialog(frame, "No saved entries yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    String content = Files.readString(path);
                    JTextArea viewArea = new JTextArea(content, 20, 50);
                    viewArea.setWrapStyleWord(true);
                    viewArea.setLineWrap(true);
                    viewArea.setEditable(false);

                    JScrollPane viewScroll = new JScrollPane(viewArea);
                    viewScroll.setPreferredSize(new Dimension(500, 400));
                    JOptionPane.showMessageDialog(frame, viewScroll, "📔 Your Saved Entries", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading entries: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

