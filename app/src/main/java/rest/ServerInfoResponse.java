package rest;

/**
 * Created by morion on 2017-02-08.
 */

public class ServerInfoResponse {

    private String php_server_base;
    private String photo_server_base;
    private String article_image_base;
    private String profile_image_base;

    private String error;
    private String error_msg;

    public String getPhp_server_base() {
        return php_server_base;
    }

    public void setPhp_server_base(String php_server_base) {
        this.php_server_base = php_server_base;
    }

    public String getPhoto_server_base() {
        return photo_server_base;
    }

    public void setPhoto_server_base(String photo_server_base) {
        this.photo_server_base = photo_server_base;
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
