package dbt230.group1.database;

import java.io.Serializable;

/**
 * The type Employee.
 */
public class Employee implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private int hireYear;

    /**
     * Instantiates a new Employee.
     */
    public Employee() {
    }

    /**
     * Instantiates a new Employee.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     * @param hireYear  the hire year
     */
    public Employee(int id, String firstName, String lastName, int hireYear) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setHireYear(hireYear);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        if (id < 0){
            throw new IllegalArgumentException("ID cannot be negative");
        }
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        if (firstName == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        if (lastName == null){
            throw new IllegalArgumentException("Last name cannot be null");
        }
        this.lastName = lastName;
    }

    /**
     * Gets hire year.
     *
     * @return the hire year
     */
    public int getHireYear() {
        return hireYear;
    }

    /**
     * Sets hire year.
     *
     * @param hireYear the hire year
     */
    public void setHireYear(int hireYear) {
        if (hireYear < 0){
            throw new IllegalArgumentException("Must be a valid year");
        }
        this.hireYear = hireYear;
    }

    @Override
    public String toString() {
        return "\nID: " + getId() + " Employee: " + getFirstName() + " " + getLastName() + " Hired: " + getHireYear();
    }


}
