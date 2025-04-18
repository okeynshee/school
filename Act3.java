import javax.swing.*;
import java.awt.*;

public class Act3 extends JFrame {
    private final DaysAbsent[] employees = {
        new DaysAbsent("Rahma Sheikh", "Senior Software Engineer", 15000.00),
        new DaysAbsent("Brenn Merin", "Software Engineer", 20000.00),
        new DaysAbsent("AJ Caballero", "Junior Software Engineer", 40000.00)
    };

    public Act3() {
        // --- Window Setup ---
        setTitle("Employee Salary Calculator (Improved)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 625);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));

        // Main Panel with Padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // --- GUI Components ---
        // Title label
        JLabel title = new JLabel("Employee Salary Calculator");
        title.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(new Color(51, 51, 51));

        // Employee selection label
        JLabel employeeLabel = new JLabel("Select an employee:");
        employeeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        employeeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        employeeLabel.setForeground(new Color(51, 51, 51));

        // Employee combo box
        String[] employeeNames = new String[employees.length];
        for (int i = 0; i < employees.length; i++) {
            employeeNames[i] = employees[i].getName();
        }
        JComboBox<String> employeeComboBox = new JComboBox<>(employeeNames);
        employeeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        employeeComboBox.setMaximumSize(new Dimension(300, 35));
        employeeComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        employeeComboBox.setBackground(Color.WHITE);
        employeeComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Absences input label
        JLabel absencesLabel = new JLabel("Enter number of absences:");
        absencesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        absencesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        absencesLabel.setForeground(new Color(51, 51, 51));

        // Absences input field
        JTextField absencesField = new JTextField();
        absencesField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        absencesField.setMaximumSize(new Dimension(300, 35));
        absencesField.setAlignmentX(Component.LEFT_ALIGNMENT);
        absencesField.setBackground(Color.WHITE);
        absencesField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Calculate button
        JButton calculateButton = new JButton("Calculate Salary");
        calculateButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        calculateButton.setMaximumSize(new Dimension(300, 35));
        calculateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        calculateButton.setBackground(new Color(0, 120, 212));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorderPainted(false);

        // Result label
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setForeground(new Color(51, 51, 51));

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
                result.append("<html>== EMPLOYEE ==<br>");
                result.append("Name: ").append(selectedEmployee.getName()).append("<br>");
                result.append("Position: ").append(selectedEmployee.getPosition()).append("<br>");
                result.append("Salary: ").append(String.format("%.2f", selectedEmployee.getSalary())).append("<br>");
                result.append("<br>== DEDUCTIONS ==<br>");
                result.append("Days Absent: ").append(absences).append("<br>");
                result.append("Salary per day: ").append(String.format("%.2f", selectedEmployee.getSalary() / 21)).append("<br>");
                
                double deduction = absences * (selectedEmployee.getSalary() / 21);
                result.append("Deduction: ").append(String.format("%.2f", deduction)).append("<br>");
                
                double salaryAfterAbsence = selectedEmployee.getSalary() - deduction;
                double totalWelfare = PhilHealthDeduction.deduction() + SSSDeduction.deduction();
                
                if (salaryAfterAbsence < totalWelfare) {
                    result.append("<br>Not enough money to pay for welfare<br>");
                    result.append("Net Salary: ").append(String.format("%.2f", salaryAfterAbsence)).append("</html>");
                } else {
                    result.append("Philhealth: 100.00<br>");
                    result.append("SSS: 700.00<br>");
                    double netSalary = salaryAfterAbsence - totalWelfare;
                    result.append("<br>Net Salary: ").append(String.format("%.2f", netSalary)).append("</html>");
                }
                
                resultLabel.setText(result.toString());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number of absences (non-negative integer).");
            }
        });

        // --- Add Components to the Panel ---
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
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
    
        public void salary(int absences) {
            System.out.println("== EMPLOYEE ==");
            System.out.println("Name: " + name);
            System.out.println("Position: " + position);
            System.out.println("Salary: " + String.format("%.2f", salary));
            System.out.println("== DEDUCTIONS ==");
            System.out.println("Days Absent: " + absences);
            System.out.println("Salary per day: " + String.format("%.2f", salary / 21));
                double deduction = absences * (salary / 21);
            System.out.println("Deduction: " + String.format("%.2f", deduction));
                double totalDeduction;
            if ((salary - deduction) < 1 ) {
                totalDeduction = (salary - deduction);
                System.out.println("Not enough money to pay for welfare");
                System.out.println("Net Salary: " + String.format("%.2f", totalDeduction));
            } else {
                PhilHealthDeduction.display();
                SSSDeduction.display();
                totalDeduction = (salary - deduction - PhilHealthDeduction.deduction() - SSSDeduction.deduction());
                System.out.println("Net Salary: " + String.format("%.2f", totalDeduction));
            }
        }
    }
    
    class PhilHealthDeduction {
        public static double deduction() {
            return 100.00;
        }
    
        public static void display() {
            System.out.println("Philhealth: 100.00");
        }
    }
    
    class SSSDeduction {
        public static double deduction() {
            return 700.00;
        }
    
        public static void display() {
            System.out.println("SSS: 700.00");
        }
    }
}
