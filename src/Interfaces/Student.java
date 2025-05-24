package Interfaces;

import java.util.Date;

/**
 * Represents a student interface that provides methods for retrieving
 * a student's full name and date of birth.
 * <p>
 * Implementing classes must define the behavior for retrieving the student's information.
 * </p>
 *
 * @author Your Name
 */
public interface Student {
    /**
     * Retrieves the full name of the student.
     *
     * @return the full name of the student as a {@code String}.
     */
    String getFullNameOfStudent();
    /**
     * Retrieves the date of birth of the student.
     *
     * @return the date of birth of the student as a {@code Date}.
     */
    Date getDateOfBirth();
}
