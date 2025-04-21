// make sure act2 is same as act3 with few differences

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Act2 extends JFrame {
    private final DaysAbsent[] employees = {
        new DaysAbsent("Rahma Sheikh", "Senior Software Engineer", 15000.00),
        new DaysAbsent("Brenn Merin", "Software Engineer", 20000.00),
        new DaysAbsent("AJ Caballero", "Junior Software Engineer", 40000.00)
    };

    public Act2() {
        // --- Window Setup ---
        setTitle("Employee Salary Calculator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 625);
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 255));

        // Create a main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(255, 255, 255));

        // --- GUI Components ---
        // Title label
        JLabel title = new JLabel("Employee Salary Calculator");
        title.setFont(new Font("SF Pro Display", Font.PLAIN, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Employee selection label
        JLabel employeeLabel = new JLabel("Select an employee");
        employeeLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        employeeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        employeeLabel.setForeground(new Color(100, 100, 100));
        employeeLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        // Employee combo box
        String[] employeeNames = new String[employees.length];
        for (int i = 0; i < employees.length; i++) {
            employeeNames[i] = employees[i].getName();
        }
        JComboBox<String> employeeComboBox = new JComboBox<>(employeeNames);
        employeeComboBox.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        employeeComboBox.setMaximumSize(new Dimension(300, 40));
        employeeComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        employeeComboBox.setBackground(Color.WHITE);
        employeeComboBox.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Absences input label
        JLabel absencesLabel = new JLabel("Enter number of absences");
        absencesLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        absencesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        absencesLabel.setForeground(new Color(100, 100, 100));
        absencesLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        // Absences input field
        JTextField absencesField = new JTextField();
        absencesField.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        absencesField.setMaximumSize(new Dimension(300, 40));
        absencesField.setAlignmentX(Component.LEFT_ALIGNMENT);
        absencesField.setBackground(Color.WHITE);
        absencesField.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Calculate button
        JButton calculateButton = new JButton("Calculate Salary");
        calculateButton.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        calculateButton.setMaximumSize(new Dimension(300, 40));
        calculateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        calculateButton.setBackground(new Color(0, 122, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorderPainted(false);
        calculateButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setForeground(new Color(0, 0, 0));
        resultLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // --- Button Action Listener ---
        calculateButton.addActionListener(_ -> {
            try {
                int selectedIndex = employeeComboBox.getSelectedIndex();
                int absences = Integer.parseInt(absencesField.getText());
                
                if (absences < 0) {
                    throw new NumberFormatException();
                }

                DaysAbsent selectedEmployee = employees[selectedIndex];
                StringBuilder result = new StringBuilder();
                result.append("<html>");
                result.append("<div style='font-family: SF Pro Text;'>");
                result.append("<div style='margin-bottom: 8px;'>== EMPLOYEE ==</div>");
                result.append("<div style='margin-bottom: 8px;'>Name: ").append(selectedEmployee.getName()).append("</div>");
                result.append("<div style='margin-bottom: 8px;'>Position: ").append(selectedEmployee.getPosition()).append("</div>");
                result.append("<div style='margin-bottom: 8px;'>Salary: ").append(String.format("%.2f", selectedEmployee.getSalary())).append("</div>");
                result.append("<div style='margin-bottom: 8px;'>== DEDUCTIONS ==</div>");
                result.append("<div style='margin-bottom: 8px;'>Days Absent: ").append(absences).append("</div>");
                result.append("<div style='margin-bottom: 8px;'>Salary per day: ").append(String.format("%.2f", selectedEmployee.getSalary() / 21)).append("</div>");
                
                double deduction = absences * (selectedEmployee.getSalary() / 21);
                result.append("<div style='margin-bottom: 8px;'>Deduction: ").append(String.format("%.2f", deduction)).append("</div>");
                result.append("<div style='margin-bottom: 8px;'>Philhealth: 100.00</div>");
                result.append("<div style='margin-bottom: 8px;'>SSS: 700.00</div>");
                
                double netSalary = selectedEmployee.getSalary() - deduction - PhilHealthDeduction.deduction() - SSSDeduction.deduction();
                result.append("<div>Net Salary: ").append(String.format("%.2f", netSalary)).append("</div>");
                result.append("</div></html>");
                
                resultLabel.setText(result.toString());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number of absences (non-negative integer)");
            }
        });

        // --- Add Components to the Panel ---
        mainPanel.add(title);
        mainPanel.add(employeeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(employeeComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(absencesLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(absencesField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(calculateButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(resultLabel);

        // Add the main panel to the frame
        add(mainPanel);

        // Center the window
        setLocationRelativeTo(null);
    }

    public class DaysAbsent {
        private final String name;
        private final String position;
        private final double salary;

        public DaysAbsent(String name, String position, double salary) {
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public double getSalary() {
            return salary;
        }
    }

    public class PhilHealthDeduction {
        public static double deduction() {
            return 100.00;
        }
    }

    public class SSSDeduction {
        public static double deduction() {
            return 700.00;
        }
    }
}
