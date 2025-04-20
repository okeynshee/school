// make it aesthetic
// make sure the inputs aren't erroneaous

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BasketballSabermetrics {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JTextField[] statFields;
    private JLabel resultLabel;
    private DecimalFormat df = new DecimalFormat("#0.0");

    // Statistics labels
    private final String[] STAT_LABELS = {
        "Field Goals Made (FGM)",
        "Field Goals Attempted (FGA)",
        "3-Point Field Goals Made (3PM)",
        "3-Point Field Goals Attempted (3PA)",
        "Free Throws Made (FTM)",
        "Free Throws Attempted (FTA)",
        "Assists (AST)",
        "Turnovers (TOV)",
        "Rebounds (REB)",
        "Steals (STL)",
        "Blocks (BLK)",
        "Fouls Drawn (FD)",
        "Fouls Committed (FC)",
        "Games Played (GP)"
    };

    public BasketballSabermetrics() {
        frame = new JFrame("Basic Basketball Sabermetrics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create left panel for title, input, and button
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 20)); // Reduced top padding from 0 to 5
        
        // Add title to left panel
        JLabel titleLabel = new JLabel("The Basketball Sabermetrics");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(255, 126, 0)); // Basketball orange color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0)); // No top margin
        leftPanel.add(titleLabel);

        // Create input panel with GridBagLayout
        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // No extra padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        statFields = new JTextField[STAT_LABELS.length];
        for (int i = 0; i < STAT_LABELS.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            inputPanel.add(new JLabel(STAT_LABELS[i] + ":"), gbc);

            gbc.gridx = 1;
            statFields[i] = new JTextField(10);
            statFields[i].setPreferredSize(new Dimension(100, 25));
            inputPanel.add(statFields[i], gbc);
        }

        // Create scroll pane for input panel
        JScrollPane inputScrollPane = new JScrollPane(inputPanel);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.setBorder(BorderFactory.createEmptyBorder());
        leftPanel.add(inputScrollPane);

        // Add calculate button
        JButton calculateButton = new JButton("Calculate Statistics");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setBackground(new Color(255, 126, 0)); // Basketball orange color
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBorderPainted(false);
        calculateButton.setFocusPainted(false);
        calculateButton.setMargin(new Insets(10, 20, 10, 20)); // Add padding
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateButton.addActionListener(e -> calculateStatistics());
        leftPanel.add(Box.createVerticalStrut(10)); // Add some space before the button
        leftPanel.add(calculateButton);

        // Create right panel for results
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rightPanel.setBackground(new Color(240, 240, 240));

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane resultScrollPane = new JScrollPane(resultLabel);
        resultScrollPane.setBorder(BorderFactory.createEmptyBorder());
        rightPanel.add(resultScrollPane, BorderLayout.CENTER);

        // Create split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(5);
        splitPane.setBorder(BorderFactory.createEmptyBorder());

        // Add split pane to main panel
        mainPanel.add(splitPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void calculateStatistics() {
        try {
            // Get all statistics
            double fgm = Double.parseDouble(statFields[0].getText());
            double fga = Double.parseDouble(statFields[1].getText());
            double threePM = Double.parseDouble(statFields[2].getText());
            double threePA = Double.parseDouble(statFields[3].getText());
            double ftm = Double.parseDouble(statFields[4].getText());
            double fta = Double.parseDouble(statFields[5].getText());
            double ast = Double.parseDouble(statFields[6].getText());
            double tov = Double.parseDouble(statFields[7].getText());
            double reb = Double.parseDouble(statFields[8].getText());
            double stl = Double.parseDouble(statFields[9].getText());
            double blk = Double.parseDouble(statFields[10].getText());
            double fd = Double.parseDouble(statFields[11].getText());
            double fc = Double.parseDouble(statFields[12].getText());
            double gp = Double.parseDouble(statFields[13].getText());

            // Calculate shooting percentages
            double fgPercentage = (fga > 0) ? (fgm / fga) * 100 : 0;
            double threePPercentage = (threePA > 0) ? (threePM / threePA) * 100 : 0;
            double ftPercentage = (fta > 0) ? (ftm / fta) * 100 : 0;

            // Calculate assist/turnover ratio
            double astToRatio = (tov > 0) ? ast / tov : 0;

            // Calculate points
            double points = (fgm * 2) + (threePM * 3) + ftm;

            // Calculate NBA Efficiency
            double efficiency = (points + reb + ast + stl + blk - (fga - fgm) - (fta - ftm) - tov) / gp;

            // Calculate Performance Index Rating (PIR)
            double pir = (points + reb + ast + stl + blk + fd) - 
                        ((fga - fgm) + (fta - ftm) + tov + fc);

            // Create result string with improved formatting
            StringBuilder result = new StringBuilder();
            result.append("<html><div style='text-align: left; padding: 20px;'>");
            result.append("<h1 style='color: #FF7E00; margin-bottom: 20px; font-weight: 800;'>Player Statistics Card</h1>");
            result.append("<div style='margin-bottom: 15px;'>");
            result.append("<h2 style='color: #333; margin-bottom: 10px; font-weight: 600;'>Shooting Percentages</h3>");
                result.append("<p style='margin: 5px 0;'>Field Goal %: <span style='font-weight: bold;'>").append(df.format(fgPercentage)).append("%</span></p>");
                result.append("<p style='margin: 5px 0;'>3-Point %: <span style='font-weight: bold;'>").append(df.format(threePPercentage)).append("%</span></p>");
                result.append("<p style='margin: 5px 0;'>Free Throw %: <span style='font-weight: bold;'>").append(df.format(ftPercentage)).append("%</span></p>");
            result.append("</div>");
            result.append("<div style='margin-bottom: 15px;'>");
            result.append("<h2 style='color: #333; margin-bottom: 10px; font-weight: 600;'>Passing & Ball Handling</h3>");
                result.append("<p style='margin: 5px 0;'>Assist/Turnover Ratio: <span style='font-weight: bold;'>").append(df.format(astToRatio)).append("</span></p>");
            result.append("</div>");
            result.append("<div style='margin-bottom: 15px;'>");
            result.append("<h2 style='color: #333; margin-bottom: 10px; font-weight: 600;'>Advanced Metrics</h3>");
                result.append("<p style='margin: 5px 0;'>NBA Efficiency: <span style='font-weight: bold;'>").append(df.format(efficiency)).append("</span></p>");
                result.append("<p style='margin: 5px 0;'>Performance Index Rating: <span style='font-weight: bold;'>").append(df.format(pir)).append("</span></p>");
            result.append("</div>");
            result.append("</div></html>");

            resultLabel.setText(result.toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for all statistics.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasketballSabermetrics());
    }
} 