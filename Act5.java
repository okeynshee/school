import javax.swing.*;
import java.awt.*;

public class Act5 extends JFrame {
    public Act5() {
        // --- Window Setup ---
        setTitle("The Vroom");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));

        // Create a main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // --- GUI Components ---
        // Title label
        JLabel title = new JLabel("Car Information");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(51, 51, 51));

        // Car selection label
        JLabel label = new JLabel("Select a car brand:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(new Color(51, 51, 51));

        // Car combo box
        String[] cars = {"Toyota", "Hyundai", "Tesla"};
        JComboBox<String> carComboBox = new JComboBox<>(cars);
        carComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        carComboBox.setMaximumSize(new Dimension(300, 35));
        carComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        carComboBox.setBackground(Color.WHITE);
        carComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Tesla type selection (initially hidden)
        JPanel teslaPanel = new JPanel();
        teslaPanel.setLayout(new BoxLayout(teslaPanel, BoxLayout.Y_AXIS));
        teslaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        teslaPanel.setVisible(false);

        JLabel teslaLabel = new JLabel("Select Tesla type:");
        teslaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        teslaLabel.setForeground(new Color(51, 51, 51));

        String[] teslaTypes = {"Hybrid", "Electric"};
        JComboBox<String> teslaTypeComboBox = new JComboBox<>(teslaTypes);
        teslaTypeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        teslaTypeComboBox.setMaximumSize(new Dimension(300, 35));
        teslaTypeComboBox.setBackground(Color.WHITE);
        teslaTypeComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        teslaPanel.add(teslaLabel);
        teslaPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        teslaPanel.add(teslaTypeComboBox);

        // Show info button
        JButton showButton = new JButton("Show Info");
        showButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        showButton.setMaximumSize(new Dimension(300, 35));
        showButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        showButton.setBackground(new Color(0, 120, 212));
        showButton.setForeground(Color.WHITE);
        showButton.setFocusPainted(false);
        showButton.setBorderPainted(false);

        // Result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setForeground(new Color(51, 51, 51));

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
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(carComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(teslaPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
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