/**
 * Represents a person with basic personal information, 
 * including first name, last name, birth date, address, and phone number.
 * This class serves as a base class for other person-related classes.
 * 
 * <p>
 * It contains fields to store the person's details and provides getter methods 
 * to access these details. The fields are protected to allow subclasses to access them.
 * </p>
 * 
 * @author [Jose Luis Hernandez]
 * @version 1.3
 */
public class Person {

    /**
     * The first name of the person.
     */
    protected String firstName;

    /**
     * The last name of the person.
     */
    protected String lastName;

    /**
     * The birth date of the person in the format YYYY-MM-DD.
     */
    protected String birthDate;

    /**
     * The address of the person.
     */
    protected String address;

    /**
     * The phone number of the person.
     */
    protected String phoneNumber;

    /**
     * Constructs a new Person with the specified details.
     *
     * @param firstName   The first name of the person.
     * @param lastName    The last name of the person.
     * @param birthDate   The birth date of the person in the format YYYY-MM-DD.
     * @param address     The address of the person.
     * @param phoneNumber The phone number of the person.
     */
    public Person(String firstName, String lastName, String birthDate, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the birth date of the person.
     *
     * @return The birth date of the person.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the address of the person.
     *
     * @return The address of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
