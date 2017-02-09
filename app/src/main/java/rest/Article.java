package rest;

/**
 * Created by morion on 2016-12-03.
 */

public class Article {

    private String uid;
    private String nick_name;
    private String profile_img;
    private String profile_img_thumb;

    private String article_id;
    private String article_photo_url;
    private String article_photo_thumb_url;
    private String article_text;
    private String article_secret;
    private String article_created_at;

    private String article_like_cnt;
    private String article_view_cnt;
    private String article_comment_cnt;

    private String article_like_state;
    private String article_follow_state;
    private String article_wishlist_state;

    private String wishlist_text;

    public Article(String uid, String nick_name, String profile_img, String profile_img_thumb, String article_id, String article_photo_url, String article_photo_thumb_url, String article_text, String article_secret, String article_created_at, String article_like_cnt, String article_view_cnt, String article_comment_cnt, String article_like_state, String article_follow_state) {
        this.uid = uid;
        this.nick_name = nick_name;
        this.profile_img = profile_img;
        this.profile_img_thumb = profile_img_thumb;
        this.article_id = article_id;
        this.article_photo_url = article_photo_url;
        this.article_photo_thumb_url = article_photo_thumb_url;
        this.article_text = article_text;
        this.article_secret = article_secret;
        this.article_created_at = article_created_at;
        this.article_like_cnt = article_like_cnt;
        this.article_view_cnt = article_view_cnt;
        this.article_comment_cnt = article_comment_cnt;
        this.article_like_state = article_like_state;
        this.article_follow_state = article_follow_state;
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

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getProfile_img_thumb() {
        return profile_img_thumb;
    }

    public void setProfile_img_thumb(String profile_img_thumb) {
        this.profile_img_thumb = profile_img_thumb;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_photo_url() {
        return article_photo_url;
    }

    public void setArticle_photo_url(String article_photo_url) {
        this.article_photo_url = article_photo_url;
    }

    public String getArticle_photo_thumb_url() {
        return article_photo_thumb_url;
    }

    public void setArticle_photo_thumb_url(String article_photo_thumb_url) {
        this.article_photo_thumb_url = article_photo_thumb_url;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public String getArticle_secret() {
        return article_secret;
    }

    public void setArticle_secret(String article_secret) {
        this.article_secret = article_secret;
    }

    public String getArticle_created_at() {
        return article_created_at;
    }

    public void setArticle_created_at(String article_created_at) {
        this.article_created_at = article_created_at;
    }

    public String getArticle_like_cnt() {
        return article_like_cnt;
    }

    public void setArticle_like_cnt(String article_like_cnt) {
        this.article_like_cnt = article_like_cnt;
    }

    public String getArticle_view_cnt() {
        return article_view_cnt;
    }

    public void setArticle_view_cnt(String article_view_cnt) {
        this.article_view_cnt = article_view_cnt;
    }

    public String getArticle_comment_cnt() {
        return article_comment_cnt;
    }

    public void setArticle_comment_cnt(String article_comment_cnt) {
        this.article_comment_cnt = article_comment_cnt;
    }

    public String getArticle_like_state() {
        return article_like_state;
    }

    public void setArticle_like_state(String article_like_state) {
        this.article_like_state = article_like_state;
    }

    public String getArticle_follow_state() {
        return article_follow_state;
    }

    public void setArticle_follow_state(String article_follow_state) {
        this.article_follow_state = article_follow_state;
    }

    public String getArticle_wishlist_state() {
        return article_wishlist_state;
    }

    public void setArticle_wishlist_state(String article_wishlist_state) {
        this.article_wishlist_state = article_wishlist_state;
    }

    public String getWishlist_text() {
        return wishlist_text;
    }

    public void setWishlist_text(String wishlist_text) {
        this.wishlist_text = wishlist_text;
    }
}
