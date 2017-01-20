package rest;

/**
 * Created by morion on 2017-01-13.
 */

public class LikesItem {

    private String category;
    private String following_user_uid;
    private String following_user_nickName;
    private String following_user_profile_img_thumb;
    private String created_at;

    private LikesContets[] contents;
//    private String bottom_flag;
//    private String sort;
//    private String contents_error;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFollowing_user_uid() {
        return following_user_uid;
    }

    public void setFollowing_user_uid(String following_user_uid) {
        this.following_user_uid = following_user_uid;
    }

    public String getFollowing_user_nickName() {
        return following_user_nickName;
    }

    public void setFollowing_user_nickName(String following_user_nickName) {
        this.following_user_nickName = following_user_nickName;
    }

    public String getFollowing_user_profile_img_thumb() {
        return following_user_profile_img_thumb;
    }

    public void setFollowing_user_profile_img_thumb(String following_user_profile_img_thumb) {
        this.following_user_profile_img_thumb = following_user_profile_img_thumb;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public LikesContets[] getContents() {
        return contents;
    }

    public void setContents(LikesContets[] contents) {
        this.contents = contents;
    }

//    public String getBottom_flag() {
//        return bottom_flag;
//    }
//
//    public void setBottom_flag(String bottom_flag) {
//        this.bottom_flag = bottom_flag;
//    }
//
//    public String getContents_error() {
//        return contents_error;
//    }
//
//    public void setContents_error(String contents_error) {
//        this.contents_error = contents_error;
//    }

//
//    public String getSort() {
//        return sort;
//    }
//
//    public void setSort(String sort) {
//        this.sort = sort;
//    }
}
