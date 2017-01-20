package rest;

/**
 * Created by morion on 2017-01-10.
 */

public class MyPlaceResponse {

    private MyInfo place_info;
    private MyInfo couple_info;
    private Article[] article;

    public String couple_error;
    public String article_error;

    private String error;
    public String error_msg;

    public MyInfo getPlace_info() {
        return place_info;
    }

    public void setPlace_info(MyInfo place_info) {
        this.place_info = place_info;
    }

    public MyInfo getCouple_info() {
        return couple_info;
    }

    public void setCouple_info(MyInfo couple_info) {
        this.couple_info = couple_info;
    }

    public Article[] getArticle() {
        return article;
    }

    public void setArticle(Article[] article) {
        this.article = article;
    }


    public String getCouple_error() {
        return couple_error;
    }

    public void setCouple_error(String couple_error) {
        this.couple_error = couple_error;
    }

    public String getArticle_error() {
        return article_error;
    }

    public void setArticle_error(String article_error) {
        this.article_error = article_error;
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
