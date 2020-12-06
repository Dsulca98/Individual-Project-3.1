package com.example.individualproject31;

public class DatabaseOrganizer {


    private long id;

    private String email;

    private String password;

    DatabaseOrganizer(){}

    public DatabaseOrganizer(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String noteDescription) {
        this.password = noteDescription;
    }

    @Override
    public String toString() {
        return "Registry{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
