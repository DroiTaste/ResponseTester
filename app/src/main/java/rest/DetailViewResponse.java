package rest;

/**
 * Created by morion on 2016-12-10.
 */

public class DetailViewResponse {

    private Article article;
    private Article[] grid_article;
    private Comment[] comment;
    private String error;
    public String error_msg;
    public boolean comment_error;
    public boolean grid_error;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article[] getGrid_article() {
        return grid_article;
    }

    public void setGrid_article(Article[] grid_article) {
        this.grid_article = grid_article;
    }

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

    public boolean isComment_error() {
        return comment_error;
    }

    public void setComment_error(boolean comment_error) {
        this.comment_error = comment_error;
    }

    public boolean isGrid_error() {
        return grid_error;
    }

    public void setGrid_error(boolean grid_error) {
        this.grid_error = grid_error;
    }
}
