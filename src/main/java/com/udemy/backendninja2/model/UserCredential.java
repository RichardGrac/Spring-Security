package com.udemy.backendninja2.model;

/**
 * The type User credential.
 */
public class UserCredential {

    private String username;
    private String password;

    /**
     * Instantiates a new User credential.
     */
    public UserCredential(){}

    /**
     * Instantiates a new User credential.
     *
     * @param username the username
     * @param password the password
     */
    public UserCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCredential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
