package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;

@Entity
@Table(name = "patients")
public class User {
    /* FIELDS AND COLUMNS */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    protected int id;

    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "salt", nullable = false)
    protected String SALT;

    public User() { }
    public User(String username, String SALT) {
        this.username = username;
        this.SALT = SALT;
    }

    /* GETTERS & SETTERS*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSALT() {
        return SALT;
    }

    public void setSALT(String SALT) {
        this.SALT = SALT;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
