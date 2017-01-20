package rest;

/**
 * Created by morion on 2017-01-13.
 */

public class LikesResponse {

    private LikesItem[] likes_item;

    private String bottom_item;
    private String error;
    private String error_msg;

    public LikesItem[] getLikes_item() {
        return likes_item;
    }

    public void setLikes_item(LikesItem[] likes_item) {
        this.likes_item = likes_item;
    }

    public String getBottom_item() {
        return bottom_item;
    }

    public void setBottom_item(String bottom_item) {
        this.bottom_item = bottom_item;
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
