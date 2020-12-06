package com.example.individualproject31;

public class KidRegistryOrganizer {

    private long id;

    private String username;

    private String password;

    KidRegistryOrganizer(){}

    public KidRegistryOrganizer(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Registry{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
