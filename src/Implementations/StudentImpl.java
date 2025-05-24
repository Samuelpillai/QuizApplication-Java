package Implementations;

import Interfaces.Student;
import java.util.Date;
import java.util.Objects;
/**
 * Implementation of the {@code Student} interface that represents a student with a first name, last name,
 * and date of birth. This class provides functionality to retrieve a student's full name and date of birth,
 * and overrides common object methods like {@code equals}, {@code hashCode}, and {@code toString}.
 * <p>
 * This class ensures that the student details provided (first name, last name, and date of birth) are non-null.
 * </p>
 *
 * @author Your Name
 */
public class StudentImpl implements Student {
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;

    /**
     * Constructs a {@code StudentImp} object with the specified first name, last name, and date of birth.
     *
     * @param firstName the first name of the student, must not be {@code null}.
     * @param lastName the last name of the student, must not be {@code null}.
     * @param dateOfBirth the date of birth of the student, must not be {@code null}.
     * @throws IllegalArgumentException if any of the provided parameters are {@code null}.
     */
    public StudentImpl(String firstName, String lastName, Date dateOfBirth) {
        if (firstName == null || lastName == null || dateOfBirth == null){
            throw new IllegalArgumentException("Student details cannot be null");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }
    /**
     * Retrieves the full name of the student, combining the first name and last name.
     *
     * @return a {@code String} representing the full name of the student.
     */
    @Override
    public String getFullNameOfStudent() {
        return firstName + " " + lastName;
    }
    /**
     * Retrieves the date of birth of the student.
     *
     * @return a {@code Date} object representing the student's date of birth.
     */
    @Override
    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }
    /**
     * Compares this student object with another object for equality based on first name, last name, and date of birth.
     *
     * @param o the object to be compared with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentImpl that = (StudentImpl) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(dateOfBirth, that.dateOfBirth);
    }
    /**
     * Returns the hash code for this student object based on the first name, last name, and date of birth.
     *
     * @return an {@code int} representing the hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }
    /**
     * Returns a string representation of the student's details, including the first name, last name, and date of birth.
     *
     * @return a {@code String} representing the student's details.
     */
    @Override
    public String toString() {
        return "StudentDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
