package Model;

import Interfaces.Question;
import Interfaces.StudentStatistics;

import java.util.*;

/**
 * Implements the {@code StudentStatistics} interface, providing functionality to track and manage
 * a student's quiz and revision scores, as well as their quiz attempts and incorrect questions.
 *
 * <p>This class is responsible for storing quiz and revision scores, counting attempts, and providing
 * a final verdict based on the student's performance. It also tracks incorrect questions for potential revisions.
 * </p>
 *
 * <p>The class ensures that quiz scores are valid (between 0.0 and 1.0) and provides various methods for
 * retrieving statistics such as average quiz scores, number of attempts, and a final verdict.</p>
 *
 * <p>It also manages quiz history and stores details about incorrect questions for students who need to revise their performance.</p>
 *
 * @author Your Name
 */
public class StudentStatisticsImpl implements StudentStatistics {

    private List<Double> quizScores = new ArrayList<>();
    private Map<Integer, Double> quizHistory;
    private int quizAttemptCounter;
    private final List<Double> revisionScores = new ArrayList<>();
    private final List<Question> incorrectQuestion = new ArrayList<Question>();
    private int numberOfQuizAttempts;
    private int numberOfRevisionAttempts;

    /**
     * Constructs a {@code StudentStatisticsImp} object and initializes the internal tracking structures
     * for quiz scores, revision scores, incorrect questions, and quiz history.
     */
    public StudentStatisticsImpl(){
        this.quizScores = new ArrayList<>();
        this.quizHistory = new LinkedHashMap<>();
        this.quizAttemptCounter = 0;
    }

    /**
     * Adds a quiz score to the student's record.
     *
     * @param scores the quiz score to be added, must be between 0.0 and 1.0.
     * @throws IllegalArgumentException if the score is not within the valid range.
     */
    @Override
    public void addQuizScore(double scores) {
        if(scores < 0.0 || scores > 1.0){
            throw new IllegalArgumentException("Score must be between 0.0 and 1.0");
        }
        quizScores.add(scores);
        numberOfQuizAttempts++;
    }

    /**
     * Adds a revision score to the student's record.
     *
     * @param score the revision score to be added.
     */
    @Override
    public void addRevisionScore(double score) {
        revisionScores.add(score);
        numberOfRevisionAttempts++;
    }

    /**
     * Adds the incorrect questions based on the student's answers.
     *
     * @param questions a {@code List} of questions from the quiz.
     * @param answers a {@code List} of answers provided by the student.
     */
    public void addIncorrectQuestion(List<Question> questions, List<String> answers){
        for (int i = 0; i < questions.size(); i++) {
            if(!questions.get(i).isAnswerCorrectOrNot(answers.get(i))){
                incorrectQuestion.add(questions.get(i));
            }
        }
    }

    /**
     * Provides the final verdict based on the student's quiz performance.
     *
     * @return a {@code String} that represents the student's final result:
     *         "PASS" if any quiz score is 0.5 or higher, "FAIL" if the student has failed two or more quizzes,
     *         and "TBD" (To Be Decided) otherwise.
     */
    @Override
    public String getFinalVerdict() {
        if(quizScores.stream().anyMatch(score -> score >= 0.5)){
            return "PASS";
        } else if (numberOfQuizAttempts >= 2) {
            return "FAIL";
        }
        return "TBD";
    }

    /**
     * Calculates and returns the average quiz score for the student.
     *
     * @return the average quiz score as a {@code double}. If no scores are available, returns 0.0.
     */
    public double getQuizAverageScore(){
        if(quizScores.isEmpty()){
            return 0.0;
        }
        double totalScore = 0.0;
        for (double score: quizScores){
            totalScore += score;
        }
        return totalScore / quizScores.size();
    }

    /**
     * Retrieves the list of all quiz scores for the student.
     *
     * @return a {@code List} of {@code Double} representing the student's quiz scores.
     */
    @Override
    public List<Double> getQuizScores() {
        return List.copyOf(quizScores);
    }

    /**
     * Retrieves the quiz history of the student, mapping quiz attempts to their scores.
     *
     * @return a {@code Map} where the key is the quiz attempt number, and the value is the score.
     */
    public Map<Integer, Double> studentQuizHistory(){
        return new LinkedHashMap<>(quizHistory);
    }

    /**
     * Retrieves the last quiz score recorded for the student.
     *
     * @return the most recent quiz score as a {@code double}.
     * @throws IllegalArgumentException if no quiz scores are available.
     */
    @Override
    public double getLastQuizScore() {
        if(quizScores.isEmpty()){
            throw new IllegalArgumentException("No quiz scores available.");
        }
        return quizScores.get(quizScores.size() -1);
    }

    /**
     * Retrieves the number of quiz attempts made by the student.
     *
     * @return the number of quiz attempts as an {@code int}.
     */
    @Override
    public int getNumberOfAttempts() {
        return numberOfQuizAttempts;
    }

    /**
     * Retrieves the number of revision attempts made by the student.
     *
     * @return the number of revision attempts as an {@code int}.
     */
    @Override
    public int getNumberOfRevisions() {
        return numberOfRevisionAttempts;
    }

    /**
     * Retrieves the list of incorrect questions for the student based on their past quiz performance.
     *
     * @return a {@code List} of {@code Question} objects representing the incorrect questions.
     */
    public List<Question> getIncorrectQuestion(){
        return List.copyOf(incorrectQuestion);
    }
}
