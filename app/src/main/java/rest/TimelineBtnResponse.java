package rest;

/**
 * Created by morion on 2017-02-16.
 */

public class TimelineBtnResponse {

    public User[] user;

    public String error;
    public String error_msg;

    public User[] getUser() {
        return user;
    }

    public void setUser(User[] user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
