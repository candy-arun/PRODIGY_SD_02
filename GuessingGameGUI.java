import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private int randomNumber;
    private int numberOfTries;
    private JTextField guessField;
    private JLabel promptLabel, feedbackLabel, attemptLabel;
    private JButton guessButton, newGameButton;
    private final int MAX_TRIES = 10;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        // Initialize components
        promptLabel = new JLabel("Enter a guess (1-100): ");
        guessField = new JTextField(5);
        guessButton = new JButton("Guess");
        newGameButton = new JButton("New Game");
        feedbackLabel = new JLabel("Try to guess the number!");
        attemptLabel = new JLabel("Attempts: 0");

        // Add components to frame
        add(promptLabel);
        add(guessField);
        add(guessButton);
        add(newGameButton);
        add(feedbackLabel);
        add(attemptLabel);

        // Initialize game data
        initializeGame();

        // Add action listeners
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    if (guess < 1 || guess > 100) {
                        feedbackLabel.setText("Enter a number between 1 and 100.");
                        return;
                    }
                    numberOfTries++;
                    attemptLabel.setText("Attempts: " + numberOfTries);
                    checkGuess(guess);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
            }
        });

        newGameButton.addActionListener(e -> initializeGame());

        setVisible(true);
    }

    private void initializeGame() {
        randomNumber = new Random().nextInt(100) + 1;
        numberOfTries = 0;
        guessField.setText("");
        feedbackLabel.setText("Try to guess the number!");
        attemptLabel.setText("Attempts: 0");
        guessField.requestFocus();
    }

    private void checkGuess(int guess) {
        if (guess < randomNumber) {
            feedbackLabel.setText("Higher!");
        } else if (guess > randomNumber) {
            feedbackLabel.setText("Lower!");
        } else {
            String performance = evaluatePerformance(numberOfTries);
            feedbackLabel.setText("Correct! " + performance);
            guessButton.setEnabled(false);
        }
    }

    private String evaluatePerformance(int tries) {
        if (tries <= MAX_TRIES / 3) {
            return "Excellent!";
        } else if (tries <= MAX_TRIES * 2 / 3) {
            return "Good.";
        } else {
            return "Average.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessingGameGUI());
    }
}

