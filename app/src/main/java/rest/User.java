package rest;


/**
 * 사용자의 모든 정보
 *
 */
public class User {

    private String uid;
    private String fb_id;
    private String kt_id;
    private String name;
    private String gender;
    private String email;
    private String nick_name;
    private String birthday;
    private String phone_number;
    private String profile_img;
    private String profile_img_thumb;
    private String self_introduce;
    private String website;
    private String created_at;
    private String login_method;
    private String user_follow_state;

    public User(String uid, String fb_id, String kt_id, String name, String gender, String email, String nick_name, String phone_number, String profile_img, String profile_img_thumb, String self_introduce, String created_at, String login_method, String user_follow_state) {
        this.uid = uid;
        this.fb_id = fb_id;
        this.kt_id = kt_id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.nick_name = nick_name;
        this.phone_number = phone_number;
        this.profile_img = profile_img;
        this.profile_img_thumb = profile_img_thumb;
        this.self_introduce = self_introduce;
        this.created_at = created_at;
        this.login_method = login_method;
        this.user_follow_state = user_follow_state;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getKt_id() {
        return kt_id;
    }

    public void setKt_id(String kt_id) {
        this.kt_id = kt_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public String getSelf_introduce() {
        return self_introduce;
    }

    public void setSelf_introduce(String self_introduce) {
        this.self_introduce = self_introduce;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLogin_method() {
        return login_method;
    }

    public void setLogin_method(String login_method) {
        this.login_method = login_method;
    }

    public String getUser_follow_state() {
        return user_follow_state;
    }

    public void setUser_follow_state(String user_follow_state) {
        this.user_follow_state = user_follow_state;
    }
}
