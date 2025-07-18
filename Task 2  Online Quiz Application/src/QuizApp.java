import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

class QuizApp extends JFrame {

    String[] questions;
    String[][] options;
    String[] correctAnswers;
    int index = 0;
    int score = 0;

    JLabel questionLabel;
    JRadioButton option1, option2, option3, option4;
    ButtonGroup optionsGroup;
    JButton nextButton, restartButton, endButton;

    // Welcome components
    JLabel welcomeMsg;
    JLabel imageLabel;
    JButton startQuizButton;

    // Timer components
    Timer timer;
    int timeLeft = 60; 
    JLabel timerLabel;
    JProgressBar progressBar;

    public QuizApp() {
        setTitle("SmartPick Quiz - Built with Java Swing");
        setSize(1250, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // quiz-banner
        ImageIcon icon = new ImageIcon("src/images/quiz_banner.png");
        imageLabel = new JLabel(icon);
        imageLabel.setBounds(100, 50, 1000, 300);
        add(imageLabel);

        // Welcome Label
        welcomeMsg = new JLabel("Welcome To The SmartPick Quiz App - Let's begin the journey");
        welcomeMsg.setBounds(185, 380, 900, 30);
        welcomeMsg.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeMsg.setForeground(Color.BLACK);
        welcomeMsg.setBackground(Color.WHITE);
        add(welcomeMsg);

        // Start Quiz Button
        startQuizButton = new JButton("Begins The Fun");
        startQuizButton.setBounds(470, 450, 200, 40);
        startQuizButton.setBackground(Color.GREEN);
        startQuizButton.setForeground(Color.WHITE);
        startQuizButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(startQuizButton);

        // Action Listener for Start Button
        startQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });

        setVisible(true);
    }

    // Method to start Quiz
    public void startQuiz() {
        System.out.println("Quiz Started");

        // Remove Welcome Components
        remove(welcomeMsg);
        remove(startQuizButton);
        remove(imageLabel);

        // Set Questions, Options, and Correct Answers
        questions = new String[]{
            "Which loop construct in Java best suits when the number of iterations is known?",
            "What is the purpose of the continue statement in a loop?",
            "Which loop construct in Java is best suited when the number of iterations is unknown?",
            "What is the key difference between a while loop and a do-while loop in Java?",
            "Which loop construct guarantees that the loop body is executed at least once?",
            "What is an infinite loop?",
            "Which statement is used to exit a loop prematurely?",
            "Which loop construct is best suited for iterating over an array or a collection?",
            "Which type of loop is best known for its boolean condition that controls entry to the loop?",
            "Which type of loop is best known for using an index or counter?"
        };

        options = new String[][]{
            {"for loop", "while loop", "do-while loop", "break statement"},
            {"To exit the loop immediately", "To skip the current iteration and move to the next iteration", "To terminate the program", "To execute a specific block of code"},
            {"for loop", "while loop", "do-while loop", "none"},
            {"The syntax used to define the loop", "The number of iterations performed", "The condition check timing", "The ability to use the break statement"},
            {"for loop", "while loop", "do-while loop", "continue statement"},
            {"A loop that executes only once", "A loop that never terminates naturally", "A loop that contains an unreachable code block", "A loop that uses the continue statement"},
            {"return statement", "continue statement", "break statement", "exit statement"},
            {"for loop", "while loop", "do-while loop", "continue statement"},
            {"do-while loop", "for (traditional)", "for-each", "while"},
            {"for loop", "while loop", "do-while loop", "for (traditional)"}
        };

        correctAnswers = new String[]{
            "for loop",
            "To skip the current iteration and move to the next iteration",
            "while loop",
            "The condition check timing",
            "do-while loop",
            "A loop that never terminates naturally",
            "break statement",
            "for loop",
            "while",
            "for (traditional)"
        };

        // Create Quiz Components
        questionLabel = new JLabel();
        questionLabel.setBounds(100, 30, 1000, 100);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setForeground(Color.BLACK);
        questionLabel.setBackground(Color.WHITE);
        add(questionLabel);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        option1.setBounds(100, 150, 500, 30);
        option2.setBounds(100, 200, 500, 30);
        option3.setBounds(100, 250, 500, 30);
        option4.setBounds(100, 300, 500, 30);

        option1.setBackground(Color.GRAY);
        option2.setBackground(Color.GRAY);
        option3.setBackground(Color.GRAY);
        option4.setBackground(Color.GRAY);

        option1.setForeground(Color.WHITE);
        option2.setForeground(Color.WHITE);
        option3.setForeground(Color.WHITE);
        option4.setForeground(Color.WHITE);

        option1.setFont(new Font("Arial", Font.BOLD, 14));
        option2.setFont(new Font("Arial", Font.BOLD, 14));
        option3.setFont(new Font("Arial", Font.BOLD, 14));
        option4.setFont(new Font("Arial", Font.BOLD, 14));

        add(option1);
        add(option2);
        add(option3);
        add(option4);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        // Timer Label
        timerLabel = new JLabel("Time Left: 60 seconds");
        timerLabel.setBounds(100, 360, 300, 30);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        // Progress Bar
        progressBar = new JProgressBar(0, 60);
        progressBar.setBounds(100, 400, 500, 30);
        progressBar.setValue(60);
        progressBar.setForeground(Color.GREEN);
        add(progressBar);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBounds(470, 450, 200, 40);
        nextButton.setBackground(Color.GREEN);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(nextButton);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next Question");
                manualNext();
            }
        });

        // Load First Question
        loadQuestion();

        revalidate();
        repaint();
    }

    public void loadQuestion() {
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);

        optionsGroup.clearSelection();
        startTimer();
    }

    public void manualNext() {
        checkAnswer();
        moveToNextQuestion();
    }

    public void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timeLeft = 60;
        progressBar.setValue(60);
        timerLabel.setText("Time Left: 60 seconds");

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + " seconds");
                progressBar.setValue(timeLeft);

                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time's up! Moving to next question...");
                    moveToNextQuestion();
                }
            }
        });
        timer.start();
    }

    public void checkAnswer() {
        String selectedAnswer = "";

        if (option1.isSelected()) {
            selectedAnswer = option1.getText();
        } else if (option2.isSelected()) {
            selectedAnswer = option2.getText();
        } else if (option3.isSelected()) {
            selectedAnswer = option3.getText();
        } else if (option4.isSelected()) {
            selectedAnswer = option4.getText();
        }

        if (selectedAnswer.equals(correctAnswers[index])) {
            score++;
        }
    }

    public void moveToNextQuestion() {
        index++;
        if (index < questions.length) {
            loadQuestion();
        } else {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Thank you for playing!\nYour Score: " + score + "/" + questions.length);
            RestartButton();
            EndQuizButton();
        }
    }

    public void RestartButton() {
        nextButton.setEnabled(false);

        restartButton = new JButton("Restart Quiz");
        restartButton.setBounds(470, 500, 200, 40);
        restartButton.setBackground(Color.GREEN);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(restartButton);

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartQuiz();
            }
        });

        revalidate();
        repaint();
    }

    public void EndQuizButton() {
        endButton = new JButton("Finish");
        endButton.setBounds(470, 550, 200, 40);
        endButton.setBackground(Color.GREEN);
        endButton.setForeground(Color.WHITE);
        endButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(endButton);

        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                endQuiz();
            }
        });

        revalidate();
        repaint();
    }

    public void restartQuiz() {
        score = 0;
        index = 0;

        remove(restartButton);
        remove(endButton);
        nextButton.setEnabled(true);
        revalidate();
        repaint();
        loadQuestion();
    }

    public void endQuiz() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
