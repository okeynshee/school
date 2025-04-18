import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public UI() {
        // Create the frame with a modern look
        JFrame frame = new JFrame("Activity Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setResizable(false);  // Make the window non-resizable
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Create a main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Setting title object
        JLabel title = new JLabel("Welcome to Kenshee's OOP!");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(51, 51, 51));

        // setting label object
        JLabel label = new JLabel("Choose an activity:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(new Color(51, 51, 51));

        // Create a combo box with modern styling
        String[] activities = {"Act1", "Act2", "Act3", "Act4", "Act5", "Act6", "Act7", "Act8", "Act9"};
        JComboBox<String> activityComboBox = new JComboBox<>(activities);
        activityComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        activityComboBox.setMaximumSize(new Dimension(300, 35));
        activityComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityComboBox.setBackground(Color.WHITE);
        activityComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Create a button with modern styling
        JButton showButton = new JButton("Show Activity");
        showButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        showButton.setMaximumSize(new Dimension(300, 35));
        showButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        showButton.setBackground(new Color(0, 120, 212));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);
        showButton.setBorderPainted(false);

        // Create a label to display the selected activity with modern styling
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setForeground(new Color(51, 51, 51));

        // Add action listener to the button
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedActivity = (String) activityComboBox.getSelectedItem();
                resultLabel.setText("You selected: " + selectedActivity);

                // Create an instance of the selected activity and make it visible
                switch (selectedActivity) {
                    case "Act1":
                        SwingUtilities.invokeLater(() -> {
                            Act1 act1Window = new Act1();
                            act1Window.setVisible(true);
                        });
                        break;
                    case "Act2":
                        SwingUtilities.invokeLater(() -> {
                            Act2 act2Window = new Act2();
                            act2Window.setVisible(true);
                        });
                        break;
                    // Note: Make sure that the program doesn't have negative salary amount
                    case "Act3":
                        Act3 act3Window = new Act3();
                        act3Window.setVisible(true);
                        break;
                    /* 
                    case "Act5":
                        Act5 act5Window = new Act5();
                        act5Window.setVisible(true);
                        break;
                    case "Act8":
                        Act8 act8Window = new Act8();
                        act8Window.setVisible(true);
                        break;
                    case "Act9":
                        Act9 act9Window = new Act9();
                        act9Window.setVisible(true);
                        break; */
                    default:
                        resultLabel.setText("Activity not found.");
                        break;
                }
            }
        });

        // Add components to the main panel with proper spacing
        mainPanel.add(title);
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(activityComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(showButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(resultLabel);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Center the frame on screen
        frame.setLocationRelativeTo(null);
        
        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Set the look and feel to match the system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create an instance of the UI class to display the GUI
        javax.swing.SwingUtilities.invokeLater(() -> new UI());
    }
}
