package Interfaces;

import java.util.List;
import java.util.Optional;

/**
 * Represents a student statistics interface that tracks and provides insights
 * about a student's quiz and revision performance.
 * <p>
 * Implementing classes must define methods for adding scores, calculating averages,
 * and retrieving performance-related data.
 * </p>
 *
 * @author Your Name
 */
public interface StudentStatistics {
    /**
     * Adds a new quiz score to the student's record.
     *
     * @param score the score to be added as a {@code double}.
     */
    void addQuizScore(double score);
    /**
     * Adds a new revision score to the student's record.
     *
     * @param score the score to be added as a {@code double}.
     */
    void addRevisionScore(double score);
    /**
     * Provides the final verdict based on the student's overall performance.
     *
     * @return a {@code String} representing the final assessment or verdict.
     */
    String getFinalVerdict();
    /**
     * Calculates and retrieves the average score of all quizzes taken by the student.
     *
     * @return a {@code double} representing the average quiz score.
     */
    double getQuizAverageScore();
    /**
     * Retrieves the list of all quiz scores recorded for the student.
     *
     * @return a {@code List} of {@code Double} containing the quiz scores.
     */
    List<Double> getQuizScores();
    /**
     * Retrieves the most recent quiz score of the student.
     *
     * @return a {@code double} representing the last quiz score.
     */
    double getLastQuizScore();
    /**
     * Retrieves the number of quiz attempts made by the student.
     *
     * @return an {@code int} representing the total number of quiz attempts.
     */
    int getNumberOfAttempts();
    /**
     * Retrieves the number of revision sessions attempted by the student.
     *
     * @return an {@code int} representing the total number of revisions.
     */
    int getNumberOfRevisions();
    /**
     * Retrieves the list of questions that the student answered incorrectly.
     *
     * @return a {@code List} of {@code Question} objects representing the incorrect answers.
     */
    List<Question> getIncorrectQuestion();
}
