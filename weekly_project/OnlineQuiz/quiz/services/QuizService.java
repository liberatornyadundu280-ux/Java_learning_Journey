package quiz.services;

import quiz.interfaces.Quiz;
import quiz.models.Question;
import quiz.utils.InputValidator;

// import java.util.Scanner;

public class QuizService implements Quiz {
    private Question[] questions;
    private String[] userAnswers;
    private int score = 0;

    public QuizService(Question[] questions) {
        if (questions == null || questions.length == 0) {
            System.err.println("Error: No questions were provided to the quiz service. Results will show 0/0.   ");
            this.questions = new Question[0];
        } else {
            this.questions = questions;
        }
        this.userAnswers = new String[this.questions.length];
    }

    @Override
    public void start() {
        // Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Quiz!");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQ" + (i + 1) + ": " + questions[i].getQuestionText());

            String[] options = questions[i].getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            // System.out.print("Enter your answer (1-" + options.length + "): ");

            int answerIndex = InputValidator.getValidInt("Enter your answer (1-" + options.length + "): ", 1,
                    options.length);
            userAnswers[i] = options[answerIndex - 1];

            /*
             * try {
             * answerIndex = Integer.parseInt(input);
             * if (answerIndex < 1 || answerIndex > options.length) {
             * System.out.println("Invalid option, answer recorded as blank.");
             * userAnswers[i] = "";
             * } else {
             * userAnswers[i] = options[answerIndex - 1];
             * }
             * } catch (NumberFormatException e) {
             * System.out.println("Invalid input, answer recorded as blank.");
             * userAnswers[i] = "";
             * }
             */

        }
        // scanner.close();
    }

    @Override
    public void submitAnswer(int questionIndex, String answer) {
        if (questionIndex >= 0 && questionIndex < userAnswers.length) {
            userAnswers[questionIndex] = answer;
        }
    }

    @Override
    public void showResults() {
        score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].getCorrectAnswer().equalsIgnoreCase(userAnswers[i])) {
                score++;
            }
        }
        System.out.println("\nQuiz Completed!");
        System.out.println("Your score: " + score + " out of " + questions.length);
    }
}