import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI {
    private JPanel midPanel;  // Renamed from leftPanel
    final private JFrame frame;
    private static final String CORRECT_PASSWORD = "1";

    public UI() {
        // Create a log-in frame that asks the user for the password
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 200);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        loginPanel.setBackground(Color.WHITE);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(200, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        loginButton.setMaximumSize(new Dimension(100, 30));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(0, 122, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(passwordField.getPassword());
                if (enteredPassword.equals(CORRECT_PASSWORD)) {
                    loginFrame.dispose();
                    initializeMainFrame();
                } else {
                    JOptionPane.showMessageDialog(loginFrame,
                            "Incorrect password. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                }
            }
        });

        loginPanel.add(passwordLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(passwordField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
        
        // Initialize frame but don't make it visible yet
        frame = new JFrame("Activity Selector");
    }

    private void initializeMainFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 500);
        frame.setResizable(true);
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        // Create left panel (for the information card)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setPreferredSize(new Dimension(250, 0));

        // Add a title for the information card
        JLabel infoTitle = new JLabel("Activity Information");
        infoTitle.setFont(new Font("SF Pro Display", Font.BOLD, 18));
        infoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoTitle.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Add a description for activity information
        JTextArea infoText = new JTextArea();
        infoText.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);
        infoText.setEditable(false);
        infoText.setBackground(new Color(255, 255, 255));
        infoText.setText("Select an activity to view its information.");
        infoText.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to left panel
        leftPanel.add(infoTitle);
        leftPanel.add(infoText);

        // Create middle panel (for selected activity)
        midPanel = new JPanel(new BorderLayout());
        midPanel.setBackground(new Color(255, 255, 255));
        midPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Create right panel (for activity selection)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        rightPanel.setBackground(new Color(255, 255, 255));

        // Add components to right panel
        JLabel title = new JLabel("The OOP of Dicdican");
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel label = new JLabel("Choose an activity");
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(new Color(100, 100, 100));
        label.setBorder(new EmptyBorder(0, 0, 8, 0));

        String[] activities = {"Animal Sound Selector", "Employee Salary Calculator", "Improved Salary Calculator", "Car Information", "Student Information", "University Information"};
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
                
                // Clear the middle panel
                midPanel.removeAll();
                
                // Create an instance of the selected activity
                switch (selectedActivity) {
                    case "Animal Sound Selector":
                        Act1 act1 = new Act1();
                        act1.setInfoText(infoText);  // Pass the reference to infoText
                        midPanel.add(act1.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Employee Salary Calculator":
                        Act2 act2 = new Act2();
                        act2.setInfoText(infoText);  // Add this line
                        midPanel.add(act2.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Improved Salary Calculator":
                        Act3 act3 = new Act3();
                        midPanel.add(act3.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Car Information":
                        Act5 act5 = new Act5();
                        midPanel.add(act5.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "Student Information":
                        Act8 act8 = new Act8();
                        midPanel.add(act8.getContentPane(), BorderLayout.CENTER);
                        break;
                    case "University Information":
                        Act9 act9 = new Act9();
                        midPanel.add(act9.getContentPane(), BorderLayout.CENTER);
                        break;
                    default:
                        JLabel errorLabel = new JLabel("Activity not found");
                        errorLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
                        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        errorLabel.setForeground(new Color(100, 100, 100));
                        midPanel.add(errorLabel, BorderLayout.CENTER);
                        break;
                }
                
                // Refresh the middle panel
                midPanel.revalidate();
                midPanel.repaint();
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

        // Create split pane with three panels
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, midPanel, rightPanel);
        rightSplitPane.setDividerLocation(500);
        rightSplitPane.setResizeWeight(0.7);
        rightSplitPane.setBorder(null);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightSplitPane);
        mainSplitPane.setDividerLocation(250);
        mainSplitPane.setResizeWeight(0.0);
        mainSplitPane.setBorder(null);

        // Final assembly
        mainPanel.add(mainSplitPane, BorderLayout.CENTER);
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
