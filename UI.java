import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI {
    private JPanel leftPanel;
    final private JFrame frame;

    public UI() {
        // Create the main frame
        frame = new JFrame("Activity Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setResizable(true);
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        // Create left panel (for selected activity)
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Create right panel (for activity selection)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        rightPanel.setBackground(new Color(255, 255, 255));

        // Add components to right panel
        JLabel title = new JLabel("Welcome to Kenshee's OOP");
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel label = new JLabel("Choose an activity");
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(new Color(100, 100, 100));
        label.setBorder(new EmptyBorder(0, 0, 8, 0));

        String[] activities = {"Act1", "Act2", "Act3", "Act5", "Act8", "Act9"};
        JComboBox<String> activityComboBox = new JComboBox<>(activities);
        activityComboBox.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        activityComboBox.setMaximumSize(new Dimension(300, 40));
        activityComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        activityComboBox.setBackground(Color.WHITE);
        activityComboBox.setBorder(new EmptyBorder(10, 15, 10, 15));

        JButton showButton = new JButton("Show Activity");
        showButton.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        showButton.setMaximumSize(new Dimension(300, 40));
        showButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        showButton.setBackground(new Color(0, 122, 255));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);
        showButton.setBorderPainted(false);
        showButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        showButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener to the button
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedActivity = (String) activityComboBox.getSelectedItem();
                
                // Clear the left panel
                leftPanel.removeAll();
                
                // Create an instance of the selected activity
                switch (selectedActivity) {
                    case "Act1":
                        Act1 act1 = new Act1();
                        leftPanel.add(act1.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Act2":
                        Act2 act2 = new Act2();
                        leftPanel.add(act2.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Act3":
                        Act3 act3 = new Act3();
                        leftPanel.add(act3.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Act5":
                        Act5 act5 = new Act5();
                        leftPanel.add(act5.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Act8":
                        Act8 act8 = new Act8();
                        leftPanel.add(act8.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Act9":
                        Act9 act9 = new Act9();
                        leftPanel.add(act9.getContentPane(), BorderLayout.CENTER);
                        break;
                    default:
                        JLabel errorLabel = new JLabel("Activity not found");
                        errorLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
                        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        errorLabel.setForeground(new Color(100, 100, 100));
                        leftPanel.add(errorLabel, BorderLayout.CENTER);
                        break;
                }
                
                // Refresh the left panel
                leftPanel.revalidate();
                leftPanel.repaint();
            }
        });

        // Add components to right panel with proper spacing
        rightPanel.add(title);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(label);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(activityComboBox);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(showButton);

        // Create split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.7);
        splitPane.setBorder(null);

        // Final assembly
        mainPanel.add(splitPane, BorderLayout.CENTER);
        frame.add(mainPanel);

        // Center the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new UI());
    }
}
