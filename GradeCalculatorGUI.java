import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame {

    private JTextField numSubjectsField;
    private JTextArea resultArea;

    public GradeCalculatorGUI() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel numSubjectsLabel = new JLabel("Enter the number of subjects:");
        numSubjectsField = new JTextField(10);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults();
            }
        });

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(numSubjectsLabel);
        panel.add(numSubjectsField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(calculateButton);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(resultArea);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private void calculateAndDisplayResults() {
        try {
            int numSubjects = Integer.parseInt(numSubjectsField.getText());

            int totalMarks = 0;

            for (int i = 1; i <= numSubjects; i++) {
                String input = JOptionPane.showInputDialog("Enter marks obtained (out of 100) for Subject " + i + ":");
                int marks = Integer.parseInt(input);

                // Validate input
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Marks should be between 0 and 100. Exiting...");
                    return;
                }

                totalMarks += marks;
            }

            // Calculate average percentage
            double averagePercentage = (double) totalMarks / numSubjects;

            // Grade calculation
            char grade = calculateGrade(averagePercentage);

            // Display results
            String resultText = String.format("Total Marks: %d\nAverage Percentage: %.2f%%\nGrade: %c",
                    totalMarks, averagePercentage, grade);

            resultArea.setText(resultText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorGUI().setVisible(true);
            }
        });
    }
}
