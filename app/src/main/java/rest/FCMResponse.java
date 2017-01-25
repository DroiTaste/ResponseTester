package rest;

/**
 * Created by morion on 2017-01-25.
 */

public class FCMResponse {

    public String fcm_server_error;
    public String error;
    public String error_msg;

    public String getFcm_server_error() {
        return fcm_server_error;
    }

    public void setFcm_server_error(String fcm_server_error) {
        this.fcm_server_error = fcm_server_error;
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
