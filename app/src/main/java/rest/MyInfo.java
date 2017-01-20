package rest;

/**
 * Created by morion on 2017-01-10.
 */

public class MyInfo {


    private String name;
    private String nick_name;
    private String profile_img;
    private String self_introduce;

    private String article_count;
    private String follower_count;
    private String following_count;

    private String couple_day;

    public MyInfo(String name, String nick_name, String profile_img, String self_introduce, String article_count, String follower_count, String following_count, String couple_day) {
        this.name = name;
        this.nick_name = nick_name;
        this.profile_img = profile_img;
        this.self_introduce = self_introduce;
        this.article_count = article_count;
        this.follower_count = follower_count;
        this.following_count = following_count;
        this.couple_day = couple_day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSelf_introduce() {
        return self_introduce;
    }

    public void setSelf_introduce(String self_introduce) {
        this.self_introduce = self_introduce;
    }

    public String getArticle_count() {
        return article_count;
    }

    public void setArticle_count(String article_count) {
        this.article_count = article_count;
    }

    public String getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(String follower_count) {
        this.follower_count = follower_count;
    }

    public String getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(String following_count) {
        this.following_count = following_count;
    }

    public String getCouple_day() {
        return couple_day;
    }

    public void setCouple_day(String couple_day) {
        this.couple_day = couple_day;
    }
}
