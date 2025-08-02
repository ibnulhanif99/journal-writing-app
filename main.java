import javax.swing.*;
import java.time.LocalDate;

public class a {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Journal Writing App");

        String today = LocalDate.now().toString();
        JLabel label = new JLabel("Welcome to your Journal! Today is " + today, SwingConstants.CENTER);

        frame.add(label);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}