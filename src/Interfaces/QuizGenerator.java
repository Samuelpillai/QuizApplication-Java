package Interfaces;

import java.util.List;

/**
 * Represents a quiz interface that provides a structure for retrieving questions
 * and evaluating a quiz based on user-provided answers.
 * <p>
 * Implementing classes must define the behavior for retrieving the list of questions
 * and calculating the result of the quiz based on given answers.
 * </p>
 *
 * @author Your Name
 */
public interface QuizGenerator {
    /**
     * Retrieves the list of questions for the quiz.
     *
     * @return a {@code List} of {@code Question} objects representing the questions in the quiz.
     */
    List<Question> getQuestions();
    /**
     * Evaluates the quiz based on the provided list of answers and returns a score.
     *
     * @param answers a {@code List} of {@code String} representing the user's answers to the questions.
     * @return a {@code double} representing the score achieved in the quiz.
     */
    double takeQuiz(List<String> answers);
}
