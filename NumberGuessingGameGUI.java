import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int generatedNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int roundsWon;
    private int totalAttempts;

    private JTextField guessField;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;
    private JLabel totalAttemptsLabel; // New label for total attempts
    private JButton guessButton;
    private JButton newGameButton;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGame();

        guessField = new JTextField(10);
        resultLabel = new JLabel("Enter your guess.");
        scoreLabel = new JLabel("Score: " + roundsWon);
        attemptsLabel = new JLabel("Attempts: " + attempts);
        totalAttemptsLabel = new JLabel("Total Attempts: " + totalAttempts); // Initialize total attempts label
        guessButton = new JButton("Guess");
        newGameButton = new JButton("New Game");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        setLayout(new GridLayout(7, 1)); // Increase row count for the new label
        add(guessField);
        add(resultLabel);
        add(scoreLabel);
        add(attemptsLabel);
        add(totalAttemptsLabel);
        add(guessButton);
        add(newGameButton);
    }

    private void initializeGame() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void checkGuess() {
        String userInput = guessField.getText();

        try {
            int userGuess = Integer.parseInt(userInput);
            attempts++;

            if (userGuess == generatedNumber) {
                resultLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                roundsWon++;
                totalAttempts += attempts;
                scoreLabel.setText("Score: " + roundsWon);
                attemptsLabel.setText("Attempts: " + attempts);
                totalAttemptsLabel.setText("Total Attempts: " + totalAttempts); // Update total attempts label
                newGameButton.setEnabled(true);
                guessButton.setEnabled(false);
            } else if (userGuess < generatedNumber) {
                resultLabel.setText("Too low! Try again.");
            } else {
                resultLabel.setText("Too high! Try again.");
            }

            if (attempts == maxAttempts) {
                resultLabel.setText("Sorry, you've reached the maximum number of attempts. The correct number was: "
                        + generatedNumber);
                newGameButton.setEnabled(true);
                guessButton.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private void startNewGame() {
        initializeGame();
        resultLabel.setText("Enter your guess.");
        attemptsLabel.setText("Attempts: 0");
        totalAttemptsLabel.setText("Total Attempts: " + totalAttempts); // Update total attempts label
        guessField.setText("");
        guessButton.setEnabled(true);
        newGameButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
