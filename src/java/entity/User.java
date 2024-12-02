package entity;

import com.google.gson.annotations.Expose;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Expose
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    @Expose
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    @Expose
    private String email;

    @Column(name = "otp", nullable = false, length = 6)
    private String otp;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_status_id", nullable = false)
    @Expose
    private UserStatus user_status_id;

    public User() {

    }

    public User( String name, String email, String otp, String password, UserStatus user_status_id) {
        this.name = name;
        this.email = email;
        this.otp = otp;
        this.password = password;
        this.user_status_id = user_status_id;
    }
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUser_status_id() {
        return user_status_id;
    }

    public void setUser_status_id(UserStatus user_status_id) {
        this.user_status_id = user_status_id;
    }
}
