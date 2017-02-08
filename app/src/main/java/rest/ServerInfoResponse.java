package rest;

/**
 * Created by morion on 2017-02-08.
 */

public class ServerInfoResponse {

    private String server_base;
    private String article_image_base;
    private String profile_image_base;

    private String error;
    private String error_msg;

    public String getServer_base() {
        return server_base;
    }

    public void setServer_base(String server_base) {
        this.server_base = server_base;
    }

    public String getArticle_image_base() {
        return article_image_base;
    }

    public void setArticle_image_base(String article_image_base) {
        this.article_image_base = article_image_base;
    }

    public String getProfile_image_base() {
        return profile_image_base;
    }

    public void setProfile_image_base(String profile_image_base) {
        this.profile_image_base = profile_image_base;
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
