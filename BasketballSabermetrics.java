import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class BasketballSabermetrics {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JTextField[] statFields;
    private JLabel resultLabel;
    private JLabel resultImageLabel; // New label for the second image
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
        frame = new JFrame("Basketball Sabermetrics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null); // Center the window

        // Create main panel with BorderLayout and gradient background
        mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 165, 0), 0, getHeight(), new Color(255, 215, 0));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create left panel for title, image, input, and button
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 126, 0), 2, true),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // Add image at the top
        JLabel imageLabel = new JLabel();
        try {
            // Load the image as a resource (assumes ball.jpg is in src/images/)
            ImageIcon icon = new ImageIcon(getClass().getResource("ball.jpg"));
            if (icon.getImage() == null) {
                throw new Exception("Image failed to load from resource path");
            }
            Image scaledImage = icon.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        } catch (Exception e) {
            e.printStackTrace();
            imageLabel.setText("Image not found");
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        leftPanel.add(imageLabel);
        leftPanel.add(Box.createVerticalStrut(10));

        // Add title to left panel
        JLabel titleLabel = new JLabel("Basketball Sabermetrics");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 126, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(15));

        // Create input panel with GridBagLayout
        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false); // Transparent background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        statFields = new JTextField[STAT_LABELS.length];
        for (int i = 0; i < STAT_LABELS.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel label = new JLabel(STAT_LABELS[i] + ":");
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setForeground(Color.BLACK);
            inputPanel.add(label, gbc);

            gbc.gridx = 1;
            statFields[i] = new JTextField(10);
            statFields[i].setFont(new Font("Arial", Font.PLAIN, 14));
            statFields[i].setPreferredSize(new Dimension(100, 30));
            statFields[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
            inputPanel.add(statFields[i], gbc);
        }

        // Create scroll pane for input panel
        JScrollPane inputScrollPane = new JScrollPane(inputPanel);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.setBorder(BorderFactory.createEmptyBorder());
        inputScrollPane.setOpaque(false);
        inputScrollPane.getViewport().setOpaque(false);
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
        rightPanel.setBackground(new Color(255, 255, 255, 220));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 126, 0), 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Create a sub-panel for the image and results
        JPanel resultContentPanel = new JPanel();
        resultContentPanel.setLayout(new BoxLayout(resultContentPanel, BoxLayout.Y_AXIS));
        resultContentPanel.setOpaque(false);

        // Add result image label (below the stats, initially hidden)
        resultImageLabel = new JLabel();
        resultImageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultImageLabel.setVisible(false); // Hidden until calculation
        resultContentPanel.add(resultImageLabel);

        // Add result label
        resultLabel = new JLabel("<html><div style='text-align: left; padding: 20px;'>Enter stats to see results.</div></html>");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        resultContentPanel.add(resultLabel);

        JScrollPane resultScrollPane = new JScrollPane(resultContentPanel);
        resultScrollPane.setBorder(BorderFactory.createEmptyBorder());
        resultScrollPane.setOpaque(false);
        resultScrollPane.getViewport().setOpaque(false);
        rightPanel.add(resultScrollPane, BorderLayout.CENTER);

        // Create split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(450);
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

            // Validate inputs
            if (fgm < 0 || fga < 0 || threePM < 0 || threePA < 0 || ftm < 0 || fta < 0 ||
                ast < 0 || tov < 0 || reb < 0 || stl < 0 || blk < 0 || fd < 0 || fc < 0 || gp <= 0) {
                JOptionPane.showMessageDialog(frame, "All statistics must be non-negative, and Games Played must be greater than 0.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                resultImageLabel.setVisible(false); // Hide image on error
                return;
            }
            if (fgm > fga || threePM > threePA || ftm > fta) {
                JOptionPane.showMessageDialog(frame, "Made shots cannot exceed attempts (FGM > FGA, 3PM > 3PA, or FTM > FTA).",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                resultImageLabel.setVisible(false); // Hide image on error
                return;
            }

            // Calculate shooting percentages
            double fgPercentage = (fga > 0) ? (fgm / fga) * 100 : 0;
            double threePPercentage = (threePA > 0) ? (threePM / threePA) * 100 : 0;
            double ftPercentage = (fta > 0) ? (ftm / fta) * 100 : 0;

            // Calculate assist/turnover ratio
            double astToRatio = (tov > 0) ? ast / tov : 0;

            // Calculate points
            double points = (fgm - threePM) * 2 + (threePM * 3) + ftm;

            // Calculate NBA Efficiency
            double efficiency = (points + reb + ast + stl + blk - (fga - fgm) - (fta - ftm) - tov) / gp;

            // Calculate Performance Index Rating (PIR)
            double pir = (points + reb + ast + stl + blk + fd) -
                        ((fga - fgm) + (fta - ftm) + tov + fc);

            // Load and display the result image
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("resultImage.jpg"));
                if (icon.getImage() == null) {
                    throw new Exception("Result image failed to load from resource path");
                }
                Image scaledImage = icon.getImage().getScaledInstance(160, 120, Image.SCALE_SMOOTH);
                resultImageLabel.setIcon(new ImageIcon(scaledImage));
                resultImageLabel.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                resultImageLabel.setText("Result image not found");
                resultImageLabel.setVisible(true);
            }

            // Create result string with improved formatting
            StringBuilder result = new StringBuilder();
            result.append("<html><div style='text-align: left; padding: 20px;'>");
            result.append("<h1 style='color: #FF7E00; margin-bottom: 20px; font-weight: 800;'>Player Statistics Card</h1>");
            result.append("<div style='margin-bottom: 15px;'>");
            result.append("<h2 style='color: #333; margin-bottom: 10px; font-weight: 600;'>Shooting Percentages</h2>");
            result.append("<p style='margin: 5px 0;'>Field Goal %: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(fgPercentage)).append("%</span></p>");
            result.append("<p style='margin: 5px 0;'>3-Point %: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(threePPercentage)).append("%</span></p>");
            result.append("<p style='margin: 5px 0;'>Free Throw %: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(ftPercentage)).append("%</span></p>");
            result.append("</div>");
            result.append("<div style='margin-bottom: 15px;'>");
            result.append("<h2 style='color: #333; margin-bottom: 10px; font-weight: 600;'>Passing & Ball Handling</h2>");
            result.append("<p style='margin: 5px 0;'>Assist/Turnover Ratio: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(astToRatio)).append("</span></p>");
            result.append("</div>");
            result.append("<div style='margin-bottom: 15px;'>");
            result.append("<h2 style='color: #333; margin-bottom: 10px; font-weight: 600;'>Advanced Metrics</h2>");
            result.append("<p style='margin: 5px 0;'>Points Per Game: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(points / gp)).append("</span></p>");
            result.append("<p style='margin: 5px 0;'>NBA Efficiency: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(efficiency)).append("</span></p>");
            result.append("<p style='margin: 5px 0;'>Performance Index Rating: <span style='font-weight: bold; color: #FF7E00;'>").append(df.format(pir)).append("</span></p>");
            result.append("</div>");
            result.append("</div></html>");

            resultLabel.setText(result.toString());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for all statistics.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            resultImageLabel.setVisible(false); // Hide image on error
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasketballSabermetrics());
    }
}