package Interfaces;

import java.util.List;

/**
 * Represents a generic question interface that defines the structure for questions
 * in a quiz or similar application.
 * Provides methods to get the question text, check if an answer is correct,
 * and retrieve the list of correct answers.
 *
 * <p> Implementing classes must define these behaviors based on their specific question type.
 * </p>
 *
 * @author Your Name
 */
public interface Question {
    /**
     * Gets the text of the question.
     *
     * @return the question text as a {@code String}.
     */
    String getQuestionText();
    /**
     * Checks if the provided answer is correct.
     *
     * @param answer the answer to be evaluated.
     * @return {@code true} if the answer is correct, {@code false} otherwise.
     */
    boolean isAnswerCorrectOrNot(String answer);
    /**
     * Retrieves a list of the correct answers.
     *
     * @return a {@code List} of {@code String} representing the correct answers.
     */
    List<String> getCorrectAnswers();
}
