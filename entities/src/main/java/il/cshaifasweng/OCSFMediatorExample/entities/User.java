package il.cshaifasweng.OCSFMediatorExample.entities;

import il.cshaifasweng.OCSFMediatorExample.utils.SecureUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS )
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "userID", nullable = false)
    protected int id;

    @Column(name = "username", nullable = false)
    protected String username;
    @Column(name = "salt", nullable = false)
    protected byte[] salt;
    @Column(name = "passwordHash", nullable = false)
    String hashPassword;

    public User() {
        try {
            salt = SecureUtils.getSalt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User(String username, String password) {
        try {
            this.salt = SecureUtils.getSalt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.username = username;
        this.hashPassword = SecureUtils.getSecurePassword(password, salt);
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
