package rest;

/**
 * Created by morion on 2017-01-13.
 */

public class LikesContets {

    private String uid;
    private String nick_name;

    private String article_id;
    private String article_photo_thumb_url;
    private String comment_text;

    private String follow_state;

//    private String bottom_flag;
    private String contents_error;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_photo_thumb_url() {
        return article_photo_thumb_url;
    }

    public void setArticle_photo_thumb_url(String article_photo_thumb_url) {
        this.article_photo_thumb_url = article_photo_thumb_url;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getFollow_state() {
        return follow_state;
    }

    public void setFollow_state(String follow_state) {
        this.follow_state = follow_state;
    }

//    public String getBottom_flag() {
//        return bottom_flag;
//    }
//
//    public void setBottom_flag(String bottom_flag) {
//        this.bottom_flag = bottom_flag;
//    }

    public String getContents_error() {
        return contents_error;
    }

    public void setContents_error(String contents_error) {
        this.contents_error = contents_error;
    }
}
