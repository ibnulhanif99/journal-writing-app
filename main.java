import javax.swing.*;
import java.time.LocalDate;

public class JournalApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Journal Writing App");

        String today = LocalDate.now().toString();
        JLabel label = new JLabel("Welcome to your Journal! Today is " + today, SwingConstants.CENTER);

        String[] moods = { "Happy", "Sad", "Excited", "Calm", "Tired" };
        JComboBox<String> moodBox = new JComboBox<>(moods);
        moodBox.setMaximumSize(moodBox.getPreferredSize());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(Box.createVerticalStrut(200)); 
        panel.add(moodBox);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
