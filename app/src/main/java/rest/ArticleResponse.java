package rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by morion on 2016-12-03.
 */

public class ArticleResponse {

    private Article[] article;
    private boolean refresh_result;
    private String error;
    public String error_msg;

    public Article[] getArticle() {
        return article;
    }

    public void setArticle(Article[] article) {
        this.article = article;
    }

    public boolean getRefresh_result() {
        return refresh_result;
    }

    public void setRefresh_result(boolean refresh_result) {
        this.refresh_result = refresh_result;
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
