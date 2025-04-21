import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Act9 extends JFrame {
    private final CSDepartment csDepartment = new CSDepartment();

    public Act9() {
        // --- Window Setup ---
        setTitle("University Information");
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
        JLabel title = new JLabel("University Information");
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // University name section
        JLabel universityLabel = new JLabel("University Name");
        universityLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        universityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        universityLabel.setForeground(new Color(100, 100, 100));
        universityLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        JLabel universityValue = new JLabel(University.getUniversityName());
        universityValue.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        universityValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        universityValue.setForeground(new Color(0, 0, 0));
        universityValue.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Accreditation section
        JLabel accreditationLabel = new JLabel("Accreditation Level");
        accreditationLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        accreditationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        accreditationLabel.setForeground(new Color(100, 100, 100));
        accreditationLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        JLabel accreditationValue = new JLabel(csDepartment.getAccreditationLevel());
        accreditationValue.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        accreditationValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        accreditationValue.setForeground(new Color(0, 0, 0));
        accreditationValue.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Department info section
        JLabel departmentLabel = new JLabel("Department Information");
        departmentLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        departmentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        departmentLabel.setForeground(new Color(100, 100, 100));
        departmentLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        JLabel departmentValue = new JLabel(csDepartment.getDepartmentInfo());
        departmentValue.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        departmentValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        departmentValue.setForeground(new Color(0, 0, 0));

        // --- Add Components to the Panel ---
        mainPanel.add(title);
        mainPanel.add(infoPanel);
        
        infoPanel.add(universityLabel);
        infoPanel.add(universityValue);
        infoPanel.add(accreditationLabel);
        infoPanel.add(accreditationValue);
        infoPanel.add(departmentLabel);
        infoPanel.add(departmentValue);

        // Add the main panel to the frame
        add(mainPanel);

        // Center the window
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Act9().setVisible(true);
        });
    }
}

abstract class University {
    public static String getUniversityName() {
        return "University of the Kenshee";
    }

    final public String getAccreditationLevel() {
        return "Bachelors";
    }

    public abstract String getDepartmentInfo();
}

class CSDepartment extends University {
    @Override
    public String getDepartmentInfo() {
        return "CCS is the Best";
    }
}
