package jdbc;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UsersDataSet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", updatable = false)
    private String user_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "password")
    private String password;

    @Column(name = "user_right")
    private String user_right;

    public UsersDataSet(long id, String user_name, String last_name, String password, String user_right) {
        this.id = id;
        this.user_name = user_name;
        this.last_name = last_name;
        this.password = password;
        this.user_right = user_right;
    }

    public UsersDataSet(String user_name, String last_name, String password, String user_right) {
        this.user_right = user_right;
        this.user_name = user_name;
        this.last_name = last_name;
        this.password = password;
    }

    public UsersDataSet(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return user_name;
    }

    public String getLastName() {
        return last_name;
    }

    public int getPassword() {
        return password.hashCode();
    }

    public String getUserRight() {return user_right;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDataSet that = (UsersDataSet) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user_name, that.user_name) &&
                Objects.equals(last_name, that.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_name, last_name);
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "user_name='" + user_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
