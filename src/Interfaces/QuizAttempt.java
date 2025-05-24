package Interfaces;

import java.util.List;

/**
 * Represents an attempt made by a student to take a quiz.
 * Stores the quiz and the list of answers provided during the attempt.
 * <p>
 * This class encapsulates the details of the quiz attempt, including the quiz itself
 * and the student's responses to the quiz questions.
 * </p>
 *
 * @author Your Name
 */
public class QuizAttempt {
    private QuizGenerator quiz;
    private List<Answer> answers;
    /**
     * Constructs a {@code QuizAttempt} with the specified quiz and answers.
     *
     * @param quiz the {@code Quiz} object representing the quiz taken.
     * @param answers a {@code List} of {@code Answer} objects representing the answers given by the student.
     */
    public QuizAttempt(QuizGenerator quiz, List<Answer> answers){
        this.quiz =quiz;
        this.answers = answers;
    }
    /**
     * Retrieves the quiz associated with this quiz attempt.
     *
     * @return the {@code Quiz} object representing the quiz taken.
     */
    public QuizGenerator getQuiz(){
        return quiz;
    }
    /**
     * Retrieves the list of answers provided during this quiz attempt.
     *
     * @return a {@code List} of {@code Answer} objects representing the answers given by the student.
     */
    public List<Answer> getAnswers(){
        return answers;
    }
}
