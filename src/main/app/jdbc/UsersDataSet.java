package jdbc;


import java.util.Objects;

public class UsersDataSet {
    private long id;
    private String name;
    private String last_name;
    private String password;

    public UsersDataSet(long id, String name, String last_name, String password) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.password = password;
    }

    public UsersDataSet(String name, String last_name, String password) {
        this.name = name;
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
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getPassword() {
        return password.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDataSet that = (UsersDataSet) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(last_name, that.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, last_name);
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
