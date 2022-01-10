package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="userID")
@Table(name = "clinicEmployees")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="employeeType",
        discriminatorType = DiscriminatorType.STRING)
public abstract class ClinicEmployee extends User {
    @Column(name = "employeeNum", nullable = false)
    protected int employeeNum;

    @Column(name = "firstName", nullable = false)
    protected String firstName;

    @Column(name = "lastName", nullable = false)
    protected String lastName;

    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "role", nullable = false)
    protected String role;

    public ClinicEmployee() {
    }

    public ClinicEmployee(int employeeNum, String firstName, String lastName, String email, String role) {
        this.employeeNum = employeeNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public ClinicEmployee(String username, String password, int employeeNum, String firstName, String lastName, String email, String role) {
        super(username, password);
        this.employeeNum = employeeNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
