package rest;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    //회원가입
    @FormUrlEncoded
    @POST("login/login.php")
    Call<UserResponse> postRegister(@Field("tag") String tag

                                    , @Field("name") String name
                                    , @Field("gender") String gender
                                    , @Field("email") String email
                                    , @Field("nick_name") String nick_name
                                    , @Field("password") String password
                                    , @Field("phone_number") String phone_number

                                    , @Field("login_method") String login_method
                                    , @Field("fb_id") String fb_id
                                    , @Field("kt_id") String kt_id
                                    , @Field("profile_img") String profile_img
    );
    //post테스팅용
    @FormUrlEncoded
    @POST("login/login.php")
    Call<UserResponse> postTestRegisterMember(@Field("tag") String tag
                                        , @Field("name") String name
                                        , @Field("gender") String gender
                                        , @Field("email") String email
                                        , @Field("nick_name") String nick_name
                                        , @Field("password") String password
                                        , @Field("phone_number") String phone_number
                                        , @Field("login_method") String login_method
                                        , @Field("fb_id") String fb_id
                                        , @Field("kt_id") String kt_id
                                        , @Field("profile_img") String profile_img
                                        , @Field("profile_img_thumb") String profile_img_thumb);
    //post테스팅용
    @FormUrlEncoded
    @POST("login/login.php")
    Call<UserResponse> postTestRegister(@Field("tag") String tag
                                        , @Field("fb_id") String fb_id
                                        , @Field("kt_id") String kt_id
                                        , @Field("email") String email
                                        , @Field("nick_name") String nick_name);
    //post테스팅용
    @FormUrlEncoded
    @POST("login/login.php")
    Call<UserResponse> postTestLogin(@Field("tag") String tag
                                        , @Field("email") String email
                                        , @Field("password") String password);
    //post테스팅용
    @FormUrlEncoded
    @POST("login/login.php")
    Call<ServerInfoResponse> postTestServerInfo(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("photo_size") String photo_size);
    //post테스팅용
    @FormUrlEncoded
    @POST("timeline/timeline_btn.php")
    Call<UserResponseForLike> postTestLikeList(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("article_id") String article_id);

    //post테스팅용
    @FormUrlEncoded
    @POST("upload/upload.php")
    Call<UploadBoardResponse> postTestUpload(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("article_text") String article_text
                                        , @Field("article_photo_name") String article_photo_name
                                        , @Field("article_secret") String article_secret);
    //post테스팅용
    @FormUrlEncoded
    @POST("upload/upload.php")
    Call<UploadBoardResponse> postTestCateDown(@Field("tag") String tag
                                        , @Field("uid") String uid);

    //post테스팅용
    @FormUrlEncoded
    @POST("timeline/timeline.php")
    Call<ArticleResponse> postArtilcleView(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("top_article") String top_article
                                        , @Field("bottom_article") String bottom_article);
    //post테스팅용
    @FormUrlEncoded
    @POST("timeline/timeline.php")
    Call<DetailViewResponse> postDetailView(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("article_id") String article_id);
    //post테스팅용
    @FormUrlEncoded
    @POST("timeline/timeline_btn.php")
    Call<CommentResponse> postCommentView(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("article_id") String article_id
                                        , @Field("comment_id") String comment_id
                                        , @Field("bottom_comment") String bottom_comment
                                        , @Field("comment_text") String comment_text
                                        , @Field("article_text") String article_text
                                        , @Field("wishlist_text") String wishlist_text
                                        , @Field("like_state") String like_state);
    //post테스팅용
    @FormUrlEncoded
    @POST("timeline/timeline_btn.php")
    Call<MyPlaceResponse> postTimelineBtn(@Field("tag") String tag
                                        , @Field("uid") String uid
                                        , @Field("article_id") String article_id
                                        , @Field("comment_id") String comment_id
                                        , @Field("bottom_comment") String bottom_comment
                                        , @Field("comment_text") String comment_text
                                        , @Field("article_text") String article_text
                                        , @Field("wishlist_text") String wishlist_text
                                        , @Field("like_state") String like_state
                                        , @Field("wishlist_state") String wishlist_state);

    //post테스팅용(팔로우관련)
    @FormUrlEncoded
    @POST("timeline/timeline_btn.php")
    Call<UploadBoardResponse> postFollowTest(@Field("tag") String tag
                                            , @Field("uid") String uid
                                            , @Field("following_uid") String following_uid
                                            , @Field("follow_state") String follow_state);

    @FormUrlEncoded
    @POST("myplace/myplace.php")
    Call<MyPlaceResponse> postMyplace(@Field("tag") String tag
                                            , @Field("myplace_uid") String myplace_uid
                                            , @Field("request_uid") String request_uid
                                            , @Field("bottom_article") String bottom_article);
    @FormUrlEncoded
    @POST("myplace/myplace_btn.php")
    Call<MyPlaceResponse> postMyplaceUserEdit(@Field("tag") String tag
                                            , @Field("uid") String uid
                                            , @Field("name") String name
                                            , @Field("nick_name") String nick_name
                                            , @Field("website") String website
                                            , @Field("self_introduce") String self_introduce
                                            , @Field("phone_number") String phone_number
                                            , @Field("gender") String gender
                                            , @Field("birthday") String birthday);

    @FormUrlEncoded
    @POST("like/like.php")
    Call<LikesResponse> postLike(@Field("tag") String tag
                                            , @Field("uid") String myplace_uid
                                            , @Field("bottom_item") String bottom_item);
    @FormUrlEncoded
    @POST("myplace/myplace_btn.php")
    Call<WishlistResponse> postMyplaceWishlist(@Field("tag") String tag
                                            , @Field("uid") String uid);

    @FormUrlEncoded
    @POST("fcm/fcm.php")
    Call<FCMResponse> postTokenRegistserForFCM(@Field("tag") String tag
                                            , @Field("uid") String uid
                                            , @Field("token") String token
                                            , @Field("login_state") String login_state);
    @FormUrlEncoded
    @POST("fcm/fcm.php")
    Call<FCMResponse> postTestUploadForFCM(@Field("tag") String tag
                                            , @Field("uid") String uid);
    /**
     * upload.php로 데이터 전송
     * @param tag -> tag를 통해 프로필인지 아티클인지 판별 (프로필 -> profile, 아티클 -> article
     * @param login_method
     * @param uid
     * @param file
     * @return
     */
    @Multipart
    @POST("upload/upload_img.php")
    Call<ImageUploadeResponse> Upload_Profile_Image(@Part("tag") String tag,
                                                    @Part("login_method") RequestBody login_method,
                                                    @Part("uid") RequestBody uid,
                                                    @Part MultipartBody.Part file);
}