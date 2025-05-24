package Factory;

import Implementations.StudentImpl;
import Interfaces.Student;

import java.util.Date;

/**
 * Factory class for creating instances of {@code Student}.
 * This class provides a static method to create a new {@code Student} object
 * using the {@code StudentImp} implementation.
 * <p>
 * This class follows the Factory design pattern, which encapsulates the object creation
 * process and allows for flexible instantiation of {@code Student} objects.
 * </p>
 *
 * <p> The method ensures that a valid {@code Student} object is created with the provided
 * first name, last name, and date of birth.
 * </p>
 *
 * @author Your Name
 */
public class StudentFactory {

    /**
     * Creates and returns a new {@code Student} object.
     *
     * @param firstName the first name of the student, must not be {@code null}.
     * @param lastName the last name of the student, must not be {@code null}.
     * @param dateOfBirth the date of birth of the student, must not be {@code null}.
     * @return a new {@code Student} object created with the provided details.
     * @throws IllegalArgumentException if any of the parameters are {@code null} or invalid.
     */
    public static Student createStudent(String firstName, String lastName, Date dateOfBirth){
        return new StudentImpl(firstName, lastName, dateOfBirth);
    }
}
