package Implementations;

import Interfaces.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Represents a multiple-choice question, where one or more correct answers can be provided.
 * Implements the {@code Question} interface, providing functionality to retrieve the question text,
 * check the correctness of an answer, and retrieve the correct answers.
 * <p>
 * The class ensures that the question text is not null or empty and that the correct answers
 * consist of a non-empty set of 2 to 4 elements. Answers are case-insensitive and trimmed for comparison.
 * </p>
 *
 * <p> This class is suitable for use in quizzes or assessments where multiple answers may be correct.
 * </p>
 *
 * @author Your Name
 */
public class MultipleChoiceQuestion implements Question {
    private final Set<String> correctAnswers;
    private final String questionText;

    /**
     * Constructs a {@code MultipleChoiceQuestion} with the specified question text and correct answers.
     *
     * @param questionText the text of the question, must not be null or empty.
     * @param correctAnswers a non-empty {@code Set} of correct answers (2 to 4 answers), must not be null or empty.
     * @throws IllegalArgumentException if the question text is null or empty, or if the correct answers set is null or empty.
     */
    public MultipleChoiceQuestion(String questionText, Set<String> correctAnswers) {
        if(questionText == null || questionText.isEmpty()){
            throw new IllegalArgumentException("Question text must be a non-empty or null value");
        }
        this.questionText = questionText;
        if(correctAnswers == null || correctAnswers.isEmpty()) {
            throw new IllegalArgumentException("Correct answers must be a non-empty set containing 2 to 4 elements");
        }
        this.correctAnswers = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (String answer : correctAnswers){
            this.correctAnswers.add(answer.trim().toLowerCase());
        }
    }

    /**
     * Retrieves the text of the question.
     *
     * @return the question text as a {@code String}.
     */
    @Override
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Checks if the given answer is correct. For multiple-choice questions with more than one correct answer,
     * the method compares sets of answers. For single-answer questions, the method checks the given answer against
     * the correct answer.
     *
     * @param givenAnswer the answer provided by the user, which may be a comma-separated list for multiple-choice answers.
     * @return {@code true} if the given answer matches the correct answer(s), {@code false} otherwise.
     */
    @Override
    public boolean isAnswerCorrectOrNot(String givenAnswer) {
        if (givenAnswer == null) {
            return false;
        }

        // Check for multiple correct answers (for multiple-choice questions)
        if (correctAnswers.size() > 1) {
            Set<String> correctAnswerSet = correctAnswers.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());
            Set<String> givenAnswerSet = Arrays.stream(givenAnswer.split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            return correctAnswerSet.equals(givenAnswerSet); // Match sets for multiple-choice answers
        } else {
            // For single answers (like free response questions)
            return correctAnswers.stream()
                    .anyMatch(correctAnswer -> correctAnswer.trim().equalsIgnoreCase(givenAnswer.trim()));
        }
    }

    /**
     * Retrieves the list of correct answers for the question.
     *
     * @return a {@code List} of {@code String} representing the correct answers.
     */
    @Override
    public List<String> getCorrectAnswers() {
        return List.copyOf(correctAnswers);
    }

    /**
     * Returns a string representation of the multiple-choice question.
     *
     * @return a {@code String} describing the multiple-choice question.
     */
    @Override
    public String toString() {
        return "Multiple Choice Question: " + questionText;
    }
}
