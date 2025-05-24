package Implementations;

import Interfaces.Question;
import Interfaces.QuizGenerator;

import java.util.*;

/**
 * Factory class for creating instances of {@code Quiz} objects.
 * This class provides a static method to generate a quiz with a random selection of questions from a question pool.
 * The number of questions in the generated quiz can be specified, and the selection process ensures no duplicates.
 *
 * <p>
 * This class follows the Factory design pattern, encapsulating the logic for creating a {@code Quiz} object,
 * which includes a subset of questions from a provided question pool.
 * </p>
 *
 * <p> The generated quiz allows the user to take the quiz by submitting answers and returns a score.
 * </p>
 *
 * <p> The quiz object returned is an anonymous implementation of the {@code Quiz} interface.
 * </p>
 *
 * @author Your Name
 */
public class QuizImpl {

    /**
     * Creates and returns a new {@code Quiz} object with a specified number of randomly selected questions
     * from the given question pool.
     *
     * @param questionPool a {@code List} of {@code Question} objects representing the pool of available questions.
     * @param numberOfQuestions the number of questions to include in the generated quiz.
     * @return a {@code Quiz} object containing the selected questions.
     * @throws IllegalArgumentException if the requested number of questions exceeds the size of the question pool.
     */
    public static QuizGenerator createQuiz(List<Question> questionPool, int numberOfQuestions){

        if(numberOfQuestions > questionPool.size()) {
            throw new IllegalArgumentException("Number of requested questions exceeds the size of the question pool.");
        }
        List<Question> selectedQuestion = new ArrayList<>();
        Random random = new Random();
        // Randomly select unique questions from the pool
        while (selectedQuestion.size() < numberOfQuestions){
            Question question = questionPool.get(random.nextInt(questionPool.size()));
            if(!selectedQuestion.contains(question)){
                selectedQuestion.add(question);
            }
        }
        // Return an anonymous implementation of the Quiz interface
        return new QuizGenerator() {
            private final List<Question> quizQuestions = List.copyOf(selectedQuestion);

            /**
             * Retrieves the list of questions included in the quiz.
             *
             * @return a {@code List} of {@code Question} objects representing the quiz questions.
             */
            @Override
            public List<Question> getQuestions() {
                return List.copyOf(quizQuestions);
            }

        /**
         * Evaluates the quiz by checking the correctness of the provided answers and returns a score.
         *
         * @param answers a {@code List} of {@code String} representing the answers given by the student.
         * @return a {@code double} representing the score as the ratio of correct answers to total questions.
         * @throws IllegalArgumentException if the answers list is empty or null.
         */
            @Override
            public double takeQuiz( List<String> answers) {
                if(answers != null && answers.isEmpty()){
                    throw new IllegalArgumentException("Answers should not be empty");
                }
                int correctAnswers = 0;
                for (int i = 0; i < quizQuestions.size(); i++) {
                    if (quizQuestions.get(i).isAnswerCorrectOrNot(answers.get(i)));
                    correctAnswers++;
                }
                return (double) correctAnswers / quizQuestions.size();
            }
        };
    }
}