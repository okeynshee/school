import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Act5 extends JFrame {
    public Act5() {
        // --- Window Setup ---
        setTitle("The Vroom");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 255));

        // Create a main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(255, 255, 255));

        // --- GUI Components ---
        // Title label
        JLabel title = new JLabel("Car Information");
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Car selection label
        JLabel label = new JLabel("Select a car brand");
        label.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(new Color(100, 100, 100));
        label.setBorder(new EmptyBorder(0, 0, 8, 0));

        // Car combo box
        String[] cars = {"Toyota", "Hyundai", "Tesla"};
        JComboBox<String> carComboBox = new JComboBox<>(cars);
        carComboBox.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        carComboBox.setMaximumSize(new Dimension(300, 40));
        carComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        carComboBox.setBackground(Color.WHITE);
        carComboBox.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Tesla type selection (initially hidden)
        JPanel teslaPanel = new JPanel();
        teslaPanel.setLayout(new BoxLayout(teslaPanel, BoxLayout.Y_AXIS));
        teslaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        teslaPanel.setVisible(false);
        teslaPanel.setBackground(new Color(255, 255, 255));

        JLabel teslaLabel = new JLabel("Select Tesla type");
        teslaLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        teslaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        teslaLabel.setForeground(new Color(100, 100, 100));
        teslaLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        String[] teslaTypes = {"Hybrid", "Electric"};
        JComboBox<String> teslaTypeComboBox = new JComboBox<>(teslaTypes);
        teslaTypeComboBox.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        teslaTypeComboBox.setMaximumSize(new Dimension(300, 40));
        teslaTypeComboBox.setBackground(Color.WHITE);
        teslaTypeComboBox.setBorder(new EmptyBorder(10, 15, 10, 15));

        teslaPanel.add(teslaLabel);
        teslaPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        teslaPanel.add(teslaTypeComboBox);

        // Show info button
        JButton showButton = new JButton("Show Info");
        showButton.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        showButton.setMaximumSize(new Dimension(300, 40));
        showButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        showButton.setBackground(new Color(0, 122, 255));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);
        showButton.setBorderPainted(false);
        showButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        showButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setForeground(new Color(0, 0, 0));
        resultLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // --- Event Listeners ---
        carComboBox.addActionListener(_ -> {
            String selectedCar = (String) carComboBox.getSelectedItem();
            teslaPanel.setVisible("Tesla".equals(selectedCar));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        showButton.addActionListener(_ -> {
            String selectedCar = (String) carComboBox.getSelectedItem();
            StringBuilder result = new StringBuilder("<html>");
            
            switch (selectedCar) {
                case "Toyota" -> {
                    Toyota toyota = new Toyota();
                    result.append("Brand: ").append(toyota.brand).append("<br>");
                    result.append("Speed: ").append(toyota.speed).append(" km/h");
                }
                case "Hyundai" -> {
                    Hyundai hyundai = new Hyundai();
                    result.append("Brand: ").append(hyundai.brand).append("<br>");
                    result.append("Speed: ").append(hyundai.speed).append(" km/h");
                }
                case "Tesla" -> {
                    Tesla tesla = new Tesla();
                    result.append("Brand: ").append(tesla.brand).append("<br>");
                    result.append("Speed: ").append(tesla.speed).append(" km/h<br>");
                    String type = (String) teslaTypeComboBox.getSelectedItem();
                    result.append("Type: ").append(type).append("-Powered");
                }
            }
            
            result.append("</html>");
            resultLabel.setText(result.toString());
        });

        // --- Add Components to the Panel ---
        mainPanel.add(title);
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(carComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(teslaPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(showButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(resultLabel);

        // Add the main panel to the frame
        add(mainPanel);

        // Center the window
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Act5().setVisible(true);
        });
    }
}

// Parent class Vehicle
class Vehicle {
    protected String brand;
    protected int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }
}

class Tesla extends Vehicle {
    public Tesla() {
        super("Tesla", 1000);
    }
}

class Toyota extends Vehicle {
    public Toyota() {
        super("Toyota", 100);
    }
}

class Hyundai extends Vehicle {
    public Hyundai() {
        super("Hyundai", 500);
    }
}