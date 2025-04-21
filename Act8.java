import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Act8 extends JFrame {
    private final Student[] students = {
        new Student("Kenshee", 20, "Cebu City"),
        new Student("Rahma Sheikh", 22, "Nairobi"),
        new Student("AJ Caballero", 21, "Davao"),
        new Student("Brenn Michelle Merin", 21, "Leyte")
    };

    public Act8() {
        // --- Window Setup ---
        setTitle("Student Information");
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
        JLabel title = new JLabel("Student Information");
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Student selection label
        JLabel studentLabel = new JLabel("Select a student");
        studentLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        studentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        studentLabel.setForeground(new Color(100, 100, 100));
        studentLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        // Student combo box
        String[] studentNames = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            studentNames[i] = students[i].name;
        }
        JComboBox<String> studentComboBox = new JComboBox<>(studentNames);
        studentComboBox.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        studentComboBox.setMaximumSize(new Dimension(300, 40));
        studentComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        studentComboBox.setBackground(Color.WHITE);
        studentComboBox.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Show info button
        JButton showButton = new JButton("Show Information");
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

        // --- Button Action Listener ---
        showButton.addActionListener(_ -> {
            int selectedIndex = studentComboBox.getSelectedIndex();
            Student selectedStudent = students[selectedIndex];
            
            StringBuilder result = new StringBuilder();
            result.append("<html>");
            result.append("<div style='font-family: SF Pro Text;'>");
            result.append("<div style='margin-bottom: 8px;'>Name: ").append(selectedStudent.name).append("</div>");
            result.append("<div style='margin-bottom: 8px;'>Age: ").append(selectedStudent.getAge()).append("</div>");
            result.append("<div>Address: ").append(selectedStudent.address).append("</div>");
            result.append("</div></html>");
            
            resultLabel.setText(result.toString());
        });

        // --- Add Components to the Panel ---
        mainPanel.add(title);
        mainPanel.add(studentLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(studentComboBox);
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
            new Act8().setVisible(true);
        });
    }
}

abstract class Person {
    public String name;
    private int age;
    
    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    protected String address;
}

class Student extends Person {
    
    public Student(String name, int age, String address) {
        super(name, age, address);
    }
    
    public void displayName() {
        System.out.println("Name: " + name);
    }
    
    public void displayAge() {
        System.out.println("Age: " + getAge());
    }
    
    public void displayAddress() {
        System.out.println("Address: " + address);
    }
}