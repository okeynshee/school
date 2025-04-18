import javax.swing.*;
import java.awt.*;

// Act1 *is* the JFrame window now
public class Act1 extends JFrame {

    // Constructor: Sets up the GUI when an Act1 object is created
    public Act1() {
        // --- Window Setup ---
        setTitle("Animal Sound Selector");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));

        // Create a main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // --- GUI Components ---
        // Title label
        JLabel title = new JLabel("Animal Sound Selector");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(51, 51, 51));

        // Animal selection label
        JLabel label = new JLabel("Select an animal:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(new Color(51, 51, 51));

        // Animal combo box
        String[] animals = {"Dog", "Chicken", "Cat"};
        JComboBox<String> animalComboBox = new JComboBox<>(animals);
        animalComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        animalComboBox.setMaximumSize(new Dimension(300, 35));
        animalComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        animalComboBox.setBackground(Color.WHITE);
        animalComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Show sound button
        JButton showButton = new JButton("Show Sound");
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

        // --- Button Action Listener ---
        showButton.addActionListener(_ -> {
            String selectedAnimal = (String) animalComboBox.getSelectedItem();
            switch (selectedAnimal) {
                case "Dog" -> resultLabel.setText("You chose a dog: AW AW");
                case "Chicken" -> resultLabel.setText("You chose a chicken: TITILAOK");
                case "Cat" -> resultLabel.setText("You chose a cat: MEOW MEOW");
                default -> resultLabel.setText("");
            }
        });

        // --- Add Components to the Panel ---
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(animalComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(showButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(resultLabel);

        // Add the main panel to the frame
        add(mainPanel);

        // Center the window
        setLocationRelativeTo(null);
    }

    // Removed the uiAct1() method as its logic is now in the constructor.
    // Removed the main() method as this class is intended to be launched by UI.java.
    // The console interaction logic is removed.
}

// The Animal classes remain unchanged, but note they are NOT used by the GUI part above.
// The GUI uses hardcoded strings for sounds. These classes were only used by the removed console part.
class Animal {
    public void sound() {
        System.out.println("The animal makes a sound");
    }
}

class Cat extends Animal {
    @Override // Good practice to add Override annotation
    public void sound() {
        System.out.println("You chose a cat");
        System.out.println("MEOW MEOW");
    }
}

class Chicken extends Animal {
    @Override
    public void sound() {
        System.out.println("You chose a chicken");
        System.out.println("TITILAOK");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("You chose a dog");
        System.out.println("AW AW");
    }
}