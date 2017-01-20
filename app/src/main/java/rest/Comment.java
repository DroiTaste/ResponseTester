package rest;

/**
 * Created by morion on 2016-12-04.
 */

public class Comment {

    private String uid;
    private String nick_name;
    private String profile_img_thumb;

    private String comment_id;
    private String comment_text;
    private String comment_created_at;


    public Comment(String uid, String nick_name, String profile_img_thumb, String comment_text, String comment_created_at) {
        this.uid = uid;
        this.nick_name = nick_name;
        this.profile_img_thumb = profile_img_thumb;
        this.comment_text = comment_text;
        this.comment_created_at = comment_created_at;
    }

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

    public String getProfile_img_thumb() {
        return profile_img_thumb;
    }

    public void setProfile_img_thumb(String profile_img_thumb) {
        this.profile_img_thumb = profile_img_thumb;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getComment_created_at() {
        return comment_created_at;
    }

    public void setComment_created_at(String comment_created_at) {
        this.comment_created_at = comment_created_at;
    }
}
