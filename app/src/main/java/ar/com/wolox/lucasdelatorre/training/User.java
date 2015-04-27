package ar.com.wolox.lucasdelatorre.training;

public class User {

    private String objectId = "";
    private String username = "";
    private String authData = "";
    private String emailVerified = "";
    private String cover = "";
    private String description = "";
    private String email = "";
    private String location = "";
    private String name = "";
    private String phone = "";
    private String picture = "";
    private String createdAt = "";
    private String updatedAt = "";
    private String password = "";
    private String sessionToken = "";
    private String code = "";
    private String error = "";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthData() {
        return authData;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public String getCover() {
        return cover;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPicture() {
        return picture;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getSessionToken() { return sessionToken; }

    public String getPassword() {
        return password;
    }

    public String getCode() { return code; }

    public String getError() { return error; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }
}