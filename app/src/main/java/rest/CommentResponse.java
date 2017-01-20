package rest;

/**
 * Created by morion on 2016-12-04.
 */

public class CommentResponse {

    private Comment[] comment;
    private String error;
    public String error_msg;


    public Comment[] getComment() {
        return comment;
    }

    public void setComment(Comment[] comment) {
        this.comment = comment;
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
