package il.cshaifasweng.OCSFMediatorExample.entities;
import il.cshaifasweng.OCSFMediatorExample.utils.SecureUtils;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    protected int id;

    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "passwordHash", nullable = false)
    String hashPassword;

    @Column(name = "salt", nullable = false)
    protected byte[] salt;

    public User() {
        try {
            salt = SecureUtils.getSalt();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public User(String username, byte[] SALT) {
        this.username = username;
        this.salt = SALT;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        hashPassword = SecureUtils.getSecurePassword(password, this.salt);
    }

    public byte[] getSALT() {
        return salt;
    }

    public void setSALT(byte[] SALT) {
        this.salt = SALT;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
