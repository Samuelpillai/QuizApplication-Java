package Model;

import Factory.QuestionFactory;
import Implementations.QuizImpl;
import Interfaces.*;

import java.util.*;

/**
 * Represents a system for managing quizzes, quiz attempts, and revision attempts for students.
 * The system allows students to take quizzes, track quiz history, generate revision quizzes,
 * and store performance statistics.
 * <p>
 * This class manages a pool of questions, quiz attempts, and revision attempts, and allows
 * for generating quizzes, tracking answers, and generating student statistics.
 * </p>
 *
 * <p> The {@code QuizSystem} relies on factories to create instances of {@code Quiz}, {@code Question},
 * and {@code Student}, and stores data in various maps and lists for tracking purposes.
 * </p>
 *
 * <p> This class ensures that students are limited to a certain number of quiz and revision attempts.
 * </p>
 *
 * <p> The class provides multiple overloaded constructors and various methods for interacting with quizzes and students.
 * </p>
 *
 * @author Your Name
 */
public class QuizSystem {
    private final List<Question> questionPool = new ArrayList<>();
    private Map<Student, List<QuizGenerator>> studentQuizzes;
    private Map<Student, List<List<String>>> studentAnswers;
    private final Map<Student, StudentStatistics> studentStatisticsMap = new HashMap<>();
    private final Map<Student, Integer> quizAttempts = new HashMap<>();
    private final Map<Student, Integer> revisionAttempts = new HashMap<>();
    private final Map<Student, List<QuizAttempt>> studentQuizHistory;
    private QuizImpl quizFactory;

    /**
     * Constructs a {@code QuizSystem} and initializes the question pool and maps to store student quiz data.
     */
    public QuizSystem(){
        initializeQuestionPool();
        this.studentQuizzes = new HashMap<>();
        this.studentAnswers = new HashMap<>();
        this.studentQuizHistory = new HashMap<>();

    }

    /**
     * Constructs a {@code QuizSystem} with a specified {@code QuizFactory} for creating quizzes.
     *
     * @param quizFactory the factory for creating {@code Quiz} objects.
     */
    public QuizSystem(QuizImpl quizFactory){
        this.quizFactory = quizFactory;
        this.studentAnswers = new HashMap<>();
        this.studentQuizzes = new HashMap<>();
        this.studentQuizHistory = new HashMap<>();
    }

    /**
     * Initializes the question pool with a set of pre-defined free-response and multiple-choice questions.
     */
    private void initializeQuestionPool(){
        questionPool.add(QuestionFactory.createQuestion("Free", "What is the capital of france?", List.of("Paris")));
        questionPool.add(QuestionFactory.createQuestion("Free", "What is the capital of Germany?", List.of("Berlin")));
        questionPool.add(QuestionFactory.createQuestion("Free", "What is the capital of Japan?", List.of("Tokyo")));
        questionPool.add(QuestionFactory.createQuestion("Free", "What is the capital of India?", List.of("New Delhi")));
        questionPool.add(QuestionFactory.createQuestion("Free", "What is the capital of Australia?", List.of("Canberra")));

        questionPool.add(QuestionFactory.createQuestion("multiple", "Which are vowels?", List.of("a", "e", "i", "o", "u")));
        questionPool.add(QuestionFactory.createQuestion("multiple", "Which are prime numbers?", List.of("2", "3", "5", "7")));
        questionPool.add(QuestionFactory.createQuestion("multiple", "Which are colors?", List.of("red", "green", "blue")));
        questionPool.add(QuestionFactory.createQuestion("multiple", "Which are programming languages?", List.of("Java", "Python", "C++")));
        questionPool.add(QuestionFactory.createQuestion("multiple", "Which are continents?", List.of("Asia", "Europe", "Africa")));
    }

    /**
     * Generates a quiz with a specified number of random questions from the question pool.
     *
     * @param numberOfQuestions the number of questions to include in the quiz.
     * @return a {@code Quiz} object containing the selected questions.
     * @throws IllegalArgumentException if the requested number of questions exceeds the size of the question pool.
     */
    public QuizGenerator generateQuiz(int numberOfQuestions){
        if(numberOfQuestions > questionPool.size()){
            throw new IllegalArgumentException("Not enough questions in the pool.");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        Random random = new Random();

        while (selectedQuestions.size() < numberOfQuestions) {
            Question randomQuestion = questionPool.get(random.nextInt(questionPool.size()));
            selectedQuestions.add(randomQuestion);
        }

        return QuizImpl.createQuiz(new ArrayList<>(selectedQuestions), 5);
    }

    /**
     * Generates a revision quiz for a student based on their incorrect answers from previous quizzes.
     *
     * @param student the student for whom the revision quiz is generated.
     * @param numberOfQuestions the number of questions to include in the revision quiz.
     * @return a {@code Quiz} object containing the selected revision questions.
     * @throws IllegalArgumentException if the student has no incorrect questions to revise.
     */
    public QuizGenerator revise(Student student, int numberOfQuestions){
        StudentStatistics studentStatistics = studentStatisticsMap.get(student);
        List<Question> incorrectQuestions = studentStatistics.getIncorrectQuestion();

        if(incorrectQuestions.isEmpty()){
            throw new IllegalArgumentException("No incorrect questions to revise.");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        Random random = new Random();

        while (selectedQuestions.size() < numberOfQuestions && !incorrectQuestions.isEmpty()){
            Question randomQuestion = incorrectQuestions.get(random.nextInt(incorrectQuestions.size()));
            selectedQuestions.add(randomQuestion);
        }
        return QuizImpl.createQuiz(new ArrayList<>(selectedQuestions),5);
    }

    /**
     * Allows a student to take a quiz by submitting answers and returns the score based on correctness.
     *
     * @param student the student taking the quiz.
     * @param quiz the {@code Quiz} object being taken.
     * @param answers a {@code List} of answers provided by the student.
     * @return a {@code double} representing the score as a percentage of correct answers.
     * @throws IllegalArgumentException if the student has exceeded the allowed number of quiz attempts or the answers are invalid.
     */
    public double takeQuiz(Student student, QuizGenerator quiz, List<String> answers){

        if(quizAttempts.getOrDefault(student, 0) >= 2){
            throw new IllegalArgumentException("Student has failed two regular quizzes. No more attempts allowed.");
        }

        List<Question> quizQuestions = quiz.getQuestions();
        int correctAnswerCount = 0;
        if(answers == null || answers.isEmpty()){
            throw new IllegalArgumentException("Answer should not be empty");
        }
        if(answers.size() != quizQuestions.size()){
            throw new IllegalArgumentException("Number of answers provided does not match the number of quiz questions.");
        }

        for (int i = 0; i < quizQuestions.size(); i++) {
            if(quizQuestions.get(i).isAnswerCorrectOrNot(answers.get(i))){
                correctAnswerCount++;
            }
        }

        double score = (double) correctAnswerCount / quizQuestions.size();
        quizAttempts.put(student, quizAttempts.getOrDefault(student, 0) + 1);

        studentQuizzes.computeIfAbsent(student, k -> new ArrayList<>()).add(quiz);
        studentAnswers.computeIfAbsent(student, k -> new ArrayList<>()).add(answers);

        StudentStatistics studentStatistics = studentStatisticsMap.computeIfAbsent(student, s-> new StudentStatisticsImpl());
        studentStatistics.addQuizScore(score);

        if(score < 0.5){
            studentStatistics.getIncorrectQuestion();
        }
        return score;
    }

    /**
     * Generates a revision quiz based on a student's incorrect answers from previous quizzes.
     *
     * @param student the student for whom the revision quiz is generated.
     * @return a {@code Quiz} object containing the revision questions or {@code null} if no incorrect questions are found.
     */
    public QuizGenerator generateRevisionQuiz(Student student){
        List<QuizGenerator> quizzesTaken = studentQuizzes.getOrDefault(student, Collections.emptyList());
        List<List<String>> answerGiven = studentAnswers.getOrDefault(student, Collections.emptyList());
        Set<Question> incorrectQuestions = new HashSet<>();

        for (int i = 0; i < quizzesTaken.size(); i++) {
            QuizGenerator quiz = quizzesTaken.get(i);
            List<Question> quizQuestions =quiz.getQuestions();
            List<String> answers = answerGiven.get(i);

            for (int j = 0; j < quizQuestions.size(); j++) {
                Question question = quizQuestions.get(j);
                String answer = answers.get(j);

                if (!question.isAnswerCorrectOrNot(answer)){
                    incorrectQuestions.add(question);
                }
            }
        }
        System.out.println("Incorrect questions found: "+incorrectQuestions.size());
        if(incorrectQuestions.isEmpty()){
            System.out.println("No incorrect answers found, returning null for revision quiz.");
            return null;
        }
        List<Question> questionList = new ArrayList<>(incorrectQuestions);
        return quizFactory.createQuiz(questionList,questionList.size());
    }

/**
 * Allows a student to take a revision quiz by submitting answers and returns the score.
 *
 * @param student the student taking the revision quiz.
 * @param quiz the {@code Quiz} object being taken.
 * @param answers a {@code List} of answers provided by the student.
 * @return a {@code double} representing the score of the revision quiz.
 * @throws IllegalArgumentException if the student has exceeded the allowed number of revision attempts.
 */
    public double takeRevisionQuiz(Student student, QuizGenerator quiz, List<String> answers){
        if(revisionAttempts.getOrDefault(student, 0) >=2) {
            throw new IllegalArgumentException("Student has used all revision attempts.");
        }
        double score = quiz.takeQuiz(answers);
        revisionAttempts.put(student, revisionAttempts.getOrDefault(student, 0) + 1);

        StudentStatistics studentStatistics = studentStatisticsMap.get(student);
        studentStatistics.addRevisionScore(score);
        return score;
    }

    /**
     * Generates and returns a statistics report for a student, including the number of quiz and revision attempts,
     * quiz scores, and the final verdict.
     *
     * @param student the student whose statistics are to be generated.
     * @return a {@code String} containing the student's performance statistics.
     */
    public String generateStatistics(Student student){
        StudentStatistics studentStatistics = studentStatisticsMap.get(student);
        if(studentStatistics == null){
            return "No statistics available for this student.";
        }
        return String.format("Student: %s%nQuiz Attempts: %d%nRevision Attempts: %d%nScores: %s%nFinal Verdict: %s",
                student.getFullNameOfStudent(),
                studentStatistics.getNumberOfAttempts(),
                studentStatistics.getNumberOfRevisions(),
                studentStatistics.getQuizScores(),
                studentStatistics.getFinalVerdict());
    }

    /**
     * Retrieves the quiz history of all students, showing a list of quiz attempts for each student.
     *
     * @return a {@code Map} where the key is a {@code Student} and the value is a {@code List} of {@code QuizAttempt} objects representing the quiz history.
     */
    public Map<Student, List<QuizAttempt>> getStudentQuizHistory() {
        return studentQuizHistory;
    }

    /**
     * Retrieves the number of quiz attempts made by a student.
     *
     * @param student the student whose quiz attempts are being retrieved.
     * @return an {@code int} representing the number of quiz attempts by the student.
     */
    public int getQuizAttempts(Student student) {
        return quizAttempts.getOrDefault(student, 0);
    }
}
