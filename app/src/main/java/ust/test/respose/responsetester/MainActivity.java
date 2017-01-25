package ust.test.respose.responsetester;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import fcm.Firebase_InstanceID_Service;
import rest.ApiClient;
import rest.ApiInterface;
import rest.ArticleResponse;
import rest.CommentResponse;
import rest.DetailViewResponse;
import rest.FCMResponse;
import rest.LikesResponse;
import rest.MyPlaceResponse;
import rest.UploadBoardResponse;
import rest.UserResponse;
import rest.UserResponseForLike;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    int seleted_method_num = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner(테스트 선택)
        final String[] methodList = {
                "PostTestForMyplace"
                , "PostTestForLikes"
                , "PostTestForLikesMine"
                , "PostTokenRegiserForFCM"
                , "PostTestUploadForFCM"
        };
        final String[][] postList = {
                {"tag", "myplace_uid", "request_uid", "bottom_article" }
                , {"tag", "uid", "bottom_item" }
                , {"tag", "uid", "bottom_item" }
                , {"tag", "uid", "token", "login_state" }
                , {"tag", "uid" }
        };

        ArrayList<String> method_array = new ArrayList<String>();
        for ( String method : methodList) {
            method_array.add(method);
        }
        final ArrayAdapter<String> adspin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, method_array);
        adspin.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner spin = (Spinner)findViewById(R.id.method_spinner);
        spin.setAdapter( adspin );


        //post(포스트값 입력)
        final LinearLayout param_layout = (LinearLayout)findViewById(R.id.param_input_layout);
        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE );

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                param_layout.removeAllViews();

                seleted_method_num = i;
                int seleted_method = postList[i].length;
                for(int a=0; a<seleted_method; a++) {
                    inflater.inflate(R.layout.param_input, param_layout, true);

                    LinearLayout temp_linear = (LinearLayout)param_layout.getChildAt(a);
                    ((TextView)temp_linear.findViewById(R.id.param_post_text)).setText(postList[seleted_method_num][a]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //button(테스트 실행)
        Button login_btn = (Button)findViewById(R.id.test_go);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] param_post_values = new String[param_layout.getChildCount()];
                for (int i = 0; i < param_layout.getChildCount(); i++) {
                    LinearLayout temp_linear = (LinearLayout)param_layout.getChildAt(i);
                    param_post_values[i] = ((EditText)temp_linear.findViewById(R.id.param_input_text)).getText().toString();
                }

                if(seleted_method_num == 0){
                    PostTestForMyplace(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3]);
                } else if(seleted_method_num == 1){
                    PostTestForLikes(param_post_values[0], param_post_values[1], param_post_values[2]);
                } else if(seleted_method_num == 2){
                    PostTestForLikesMine(param_post_values[0], param_post_values[1], param_post_values[2]);
                } else if(seleted_method_num == 3){
//                    PostTokenRegiserForFCM(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3]);
                    PostTokenRegiserForFCM("token_register", param_post_values[1], param_post_values[2], param_post_values[3]);
                } else if(seleted_method_num == 4){
//                    PostTestUploadForFCM(param_post_values[0], param_post_values[1]);
                    PostTestUploadForFCM("test_upload", param_post_values[1]);
                }


//                PostTest( "isuser", "", "", "ddd@ddd.com");
//                PostTest( "isuser", "a' or '1'='1' and uid='77", null, null);
//                PostTest( "isuser", "a' or '1'='1", null, null);
//                PostTestLikeList( "like_list", "149", "110");


//                PostTestForArticle( "follow", "149", "70", "3");
//                PostTestForArticle( "follow", "149", "69", "65");
//                PostTestForArticle( "follow", "149", "64", "60");
//                PostTestForArticle( "all", "149", "0", "0");
//                PostTestForArticle( "all", "149", "69", "65");
//                PostTestForArticle( "all", "149", "64", "60");

//                PostTestForDetailView( "detail", "149", "63");
//                PostTestForDetailView( "detail_back", "149", "63");


//                PostTestForComment( "article_delete", "149", "66", "" , "", "", "", "");
//                PostTestForComment( "comment_delete", "140", "", "41" , "", "", "");
//                PostTestForComment( "like", "149", "38", "N");
//                PostTestForComment( "like", "149", "38", "Y");
//                PostTestForComment( "comment", "", "94", "", "60", "", "", "");

//                PostTestForUpload("upload", "149", "testtestjksldjlk", "test", "Y");
//                PostTestForUpload("upload_category", "149");


//                PostTestForFollow( "follow_btn", "149", "141", "Y");
//                PostTestForFollow( "follow_btn", "149", "141", "N");


//                PostTestForMyplace( "myplace", "146", "0", "0");

//                PostTestForLikes( "like_following", "116", "0");
//                PostTestForLikes( "like_following", "155", "0");
//                PostTestForLikes( "like_following", "155", "1483585731.000000");
//                PostTestForLikes( "like_following", "155", "1482722155.000000");

//                PostTestForLikesMine( "like_mine", "149", "0");
//                PostTestForLikesMine( "like_mine", "115", "0");
            }
        });

        //button(테스트 실행)
        Button fcm_btn = (Button)findViewById(R.id.fcm_token);
        fcm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FirebaseMessaging.getInstance().subscribeToTopic("news");
                FirebaseInstanceId.getInstance().getToken();
                Log.e("FCM Token : ", FirebaseInstanceId.getInstance().getToken());

                Firebase_InstanceID_Service fservices = new Firebase_InstanceID_Service();
                fservices.onTokenRefresh();

            }
        });
    }


//
//    private void PostTest(String tag, String temp1, String temp2, String temp3){
////    private void PostTest(String tag, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6
////                            , String temp7, String temp8, String temp9, String temp10){
//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<UserResponse> call = apiService.postTestLogin(tag, temp1, temp2, temp3);
////        Call<UserResponse> call = apiService.postRegister(tag, temp1, temp2, temp3, temp4, temp5, temp6
////                                                            , temp7, temp8, temp9, temp10);
//        call.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                UserResponse user1 = response.body();
//
//                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();
//
////                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(getApplicationContext(), user1.getUser().getKt_id(), Toast.LENGTH_SHORT).show();
////                Log.e("tag", user1.getUser().getUid());
////                Log.e("tag", user1.getUser().getName());
////                Log.e("tag", user1.getUser().getGender());
////                Log.e("tag", user1.getUser().getEmail());
////                Log.e("tag", user1.getUser().getNick_name());
////                Log.e("tag", user1.getUser().getPhone_num());
////                Log.e("tag", user1.getUser().getCreated_at());
////
////                Log.e("tag", user1.getUser().getLogin_method());
////                Log.e("tag", user1.getUser().getFb_id());
////                Log.e("tag", user1.getUser().getKt_id());
////                Log.e("tag", user1.getUser().getProfile_img());
////                Log.e("tag", user1.getError());
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//
//    private void PostTestLikeList(String tag, String temp1, String temp2){
//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<UserResponseForLike> call = apiService.postTestLikeList(tag, temp1, temp2);
//
//        call.enqueue(new Callback<UserResponseForLike>() {
//            @Override
//            public void onResponse(Call<UserResponseForLike> call, Response<UserResponseForLike> response) {
//                UserResponseForLike user1 = response.body();
//
//                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();
//
//                for(int i=0; i<user1.getUser().length; i++) {
//                    Log.e("tag", user1.getUser()[i].getUid());
//                    Log.e("tag", user1.getUser()[i].getName());
//                    Log.e("tag", user1.getUser()[i].getNick_name());
//                    Log.e("tag", user1.getUser()[i].getProfile_img_thumb());
//                    Log.e("tag", user1.getUser()[i].getUser_follow_state());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponseForLike> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//
//
//    private void PostTestForArticle(String tag, String temp1, String temp2, String temp3){
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<ArticleResponse> call = apiService.postArtilcleView(tag, temp1, temp2, temp3);
//
//        call.enqueue(new Callback<ArticleResponse>() {
//            @Override
//            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
//                ArticleResponse article = response.body();
//
//                Toast.makeText(getApplicationContext(), article.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), article.getError_msg(), Toast.LENGTH_SHORT).show();
//
////                Toast.makeText(getApplicationContext(), article.getError(), Toast.LENGTH_SHORT).show();
////                Toast.makeText(getApplicationContext(), article.getError_msg(), Toast.LENGTH_SHORT).show();
//
//                for(int i=0; i<5; i++) {
//                    Log.e("tag", article.getArticle()[i].getUid());
//                    Log.e("tag", article.getArticle()[i].getNick_name());
//                    Log.e("tag", article.getArticle()[i].getProfile_img());
//                    Log.e("tag", article.getArticle()[i].getProfile_img_thumb());
//
//                    Log.e("tag", article.getArticle()[i].getArticle_id());
//                    Log.e("tag", article.getArticle()[i].getArticle_photo_url());
//                    //                Log.e("tag", article.getArticle()[13].getArticle_photo_thumb_url());
//                    Log.e("tag", article.getArticle()[i].getArticle_text());
//                    Log.e("tag", article.getArticle()[i].getArticle_created_at());
//
//                    Log.e("tag", article.getArticle()[i].getArticle_like_cnt());
//                    Log.e("tag", article.getArticle()[i].getArticle_view_cnt());
//                    Log.e("tag", article.getArticle()[i].getArticle_comment_cnt());
//
//                    Log.e("tag", article.getArticle()[i].getArticle_like_state());
//                }
//
////                Log.e("tag", article.getRefresh_result());
//                Log.e("tag", article.getError());
//                Log.e("tag", article.getError_msg());
//
//                if(article.getRefresh_result()) { Log.e("tag", "boolean!! true!!"); }
//                else { Log.e("tag", "boolean!! false!!"); }
//            }
//
//            @Override
//            public void onFailure(Call<ArticleResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//
//
//    private void PostTestForDetailView(String tag, String temp1, String temp2){
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<DetailViewResponse> call = apiService.postDetailView(tag, temp1, temp2);
//
//        call.enqueue(new Callback<DetailViewResponse>() {
//            @Override
//            public void onResponse(Call<DetailViewResponse> call, Response<DetailViewResponse> response) {
//                DetailViewResponse article = response.body();
//
//                Toast.makeText(getApplicationContext(), article.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), article.getError_msg(), Toast.LENGTH_SHORT).show();
//
//                Log.e("tag", article.getArticle().getUid());
//                Log.e("tag", article.getArticle().getNick_name());
//                Log.e("tag", article.getArticle().getProfile_img());
//                Log.e("tag", article.getArticle().getProfile_img_thumb());
//
//                Log.e("tag", article.getArticle().getArticle_id());
//                Log.e("tag", article.getArticle().getArticle_photo_url());
//                Log.e("tag", article.getArticle().getArticle_photo_thumb_url());
//                Log.e("tag", article.getArticle().getArticle_text());
//                Log.e("tag", article.getArticle().getArticle_created_at());
//
//                Log.e("tag", article.getArticle().getArticle_like_cnt());
//                Log.e("tag", article.getArticle().getArticle_view_cnt());
//                Log.e("tag", article.getArticle().getArticle_comment_cnt());
//
//                Log.e("tag", article.getArticle().getArticle_like_state());
//                Log.e("tag", article.getArticle().getArticle_follow_state());
//
//                for(int i=0; i<article.getComment().length; i++) {
//                    Log.e("tag", article.getComment()[i].getUid());
//                    Log.e("tag", article.getComment()[i].getNick_name());
//                    Log.e("tag", article.getComment()[i].getProfile_img_thumb());
//                    Log.e("tag", article.getComment()[i].getComment_id());
//                    Log.e("tag", article.getComment()[i].getComment_text());
//                    Log.e("tag", article.getComment()[i].getComment_created_at());
//                }
//                Log.e("tag", article.getGrid_article()[2].getArticle_id());
//                Log.e("tag", article.getGrid_article()[2].getArticle_photo_thumb_url());
//
//                if(article.isComment_error()) {
//                    Log.e("tag", "comment_error : true");
//                } else {
//                    Log.e("tag", "comment_error : false");
//                }
//                if(article.isGrid_error()) {
//                    Log.e("tag", "grid_error : true");
//                } else {
//                    Log.e("tag", "grid_error : false");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DetailViewResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//
//    private void PostTestForComment(String tag, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6, String temp7){
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<CommentResponse> call = apiService.postCommentView(tag, temp1, temp2, temp3, temp4, temp5, temp6, temp7);
//
//        call.enqueue(new Callback<CommentResponse>() {
//            @Override
//            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
//                CommentResponse comment = response.body();
//
//                Toast.makeText(getApplicationContext(), comment.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), comment.getError_msg(), Toast.LENGTH_SHORT).show();
//
//                for(int i=0; i<comment.getComment().length; i++) {
//                    Log.e("comment", "i : "+i);
//                    Log.e("comment", comment.getComment()[i].getUid());
//                    Log.e("comment", comment.getComment()[i].getNick_name());
//                    Log.e("comment", comment.getComment()[i].getProfile_img_thumb());
//                    Log.e("comment", comment.getComment()[i].getComment_id());
//                    Log.e("comment", comment.getComment()[i].getComment_text());
//                    Log.e("comment", comment.getComment()[i].getComment_created_at());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CommentResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//
//    private void PostTestForUpload(String tag, String temp1, String temp2, String temp3, String temp4){
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<UploadBoardResponse> call = apiService.postTestUpload(tag, temp1, temp2, temp3, temp4);
//
//        call.enqueue(new Callback<UploadBoardResponse>() {
//            @Override
//            public void onResponse(Call<UploadBoardResponse> call, Response<UploadBoardResponse> response) {
//                UploadBoardResponse upload = response.body();
//
//                Toast.makeText(getApplicationContext(), upload.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), upload.getError_msg(), Toast.LENGTH_SHORT).show();
//
////                Log.e("tag", upload.getCouple_yesno());
////                for(int i=0; i<upload.getCategory().length; i++) {
////                    Log.e("tag", upload.getCategory()[i]);
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UploadBoardResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//    private void PostTestForUploadCheckCouple(String tag, String temp1){
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<UploadBoardResponse> call = apiService.postTestCateDown(tag, temp1);
//
//        call.enqueue(new Callback<UploadBoardResponse>() {
//            @Override
//            public void onResponse(Call<UploadBoardResponse> call, Response<UploadBoardResponse> response) {
//                UploadBoardResponse upload = response.body();
//
//                Toast.makeText(getApplicationContext(), upload.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), upload.getError_msg(), Toast.LENGTH_SHORT).show();
//
//                Log.e("tag", upload.getCouple_yesno());
//                for(int i=0; i<upload.getCategory().length; i++) {
//                    Log.e("tag", upload.getCategory()[i]);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UploadBoardResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }
//
//    private void PostTestForFollow(String tag, String temp1, String temp2, String temp3){
//        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<UploadBoardResponse> call = apiService.postFollowTest(tag, temp1, temp2, temp3);
//
//        call.enqueue(new Callback<UploadBoardResponse>() {
//            @Override
//            public void onResponse(Call<UploadBoardResponse> call, Response<UploadBoardResponse> response) {
//                UploadBoardResponse upload = response.body();
//
//                Toast.makeText(getApplicationContext(), upload.getError(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), upload.getError_msg(), Toast.LENGTH_SHORT).show();
//
////                Log.e("tag", upload.getCouple_yesno());
////                for(int i=0; i<upload.getCategory().length; i++) {
////                    Log.e("tag", upload.getCategory()[i]);
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UploadBoardResponse> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("tag", t.toString());
//            }
//        });
//    }



    private void PostTestForMyplace(String tag, String temp1, String temp2, String temp3){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MyPlaceResponse> call = apiService.postMyplace(tag, temp1, temp2, temp3);

        call.enqueue(new Callback<MyPlaceResponse>() {
            @Override
            public void onResponse(Call<MyPlaceResponse> call, Response<MyPlaceResponse> response) {
                MyPlaceResponse myplace = response.body();

                Toast.makeText(getApplicationContext(), myplace.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), myplace.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("myplace", myplace.getPlace_info().getName());
                Log.e("myplace", myplace.getPlace_info().getNick_name());
                Log.e("myplace", myplace.getPlace_info().getProfile_img());
                Log.e("myplace", myplace.getPlace_info().getSelf_introduce());

                Log.e("myplace", myplace.getPlace_info().getArticle_count());
                Log.e("myplace", myplace.getPlace_info().getFollower_count());
                Log.e("myplace", myplace.getPlace_info().getFollowing_count());

                Log.e("myplace", myplace.getCouple_info().getName());
                Log.e("myplace", myplace.getCouple_info().getNick_name());
                Log.e("myplace", myplace.getCouple_info().getProfile_img());
                Log.e("myplace", myplace.getCouple_info().getSelf_introduce());

                Log.e("myplace", myplace.getCouple_info().getArticle_count());
                Log.e("myplace", myplace.getCouple_info().getFollower_count());
                Log.e("myplace", myplace.getCouple_info().getFollowing_count());

                Log.e("myplace", myplace.getCouple_info().getCouple_day());

                for(int i=0; i<myplace.getArticle().length; i++) {
                    Log.e("myplace", "i : "+i);
                    Log.e("myplace", myplace.getArticle()[i].getUid());
                    Log.e("myplace", myplace.getArticle()[i].getNick_name());
                    Log.e("myplace", myplace.getArticle()[i].getProfile_img());
                    Log.e("myplace", myplace.getArticle()[i].getProfile_img_thumb());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_id());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_photo_url());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_photo_thumb_url());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_text());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_secret());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_created_at());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_like_cnt());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_view_cnt());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_comment_cnt());
                    Log.e("myplace", myplace.getArticle()[i].getArticle_like_state());
                }

                Log.e("myplace", myplace.getError());
                Log.e("myplace", myplace.getError_msg());
                Log.e("myplace article_error", myplace.getArticle_error());
                Log.e("myplace couple_error", myplace.getCouple_error());
            }

            @Override
            public void onFailure(Call<MyPlaceResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestForLikes(String tag, String temp1, String temp2){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<LikesResponse> call = apiService.postLike(tag, temp1, temp2);

        call.enqueue(new Callback<LikesResponse>() {
            @Override
            public void onResponse(Call<LikesResponse> call, Response<LikesResponse> response) {
                LikesResponse likes = response.body();

                Toast.makeText(getApplicationContext(), likes.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), likes.getError_msg(), Toast.LENGTH_SHORT).show();

                for(int i=0; i<likes.getLikes_item().length; i++) {
                    Log.e("likes_item ", "i : "+i);
                    Log.e("likes_item ", likes.getLikes_item()[i].getCategory());
                    Log.e("likes_item ", likes.getLikes_item()[i].getFollowing_user_uid());
                    Log.e("likes_item ", likes.getLikes_item()[i].getFollowing_user_nickName());
                    Log.e("likes_item ", likes.getLikes_item()[i].getFollowing_user_profile_img_thumb());
                    Log.e("likes_item ", likes.getLikes_item()[i].getCreated_at());

//                    Log.e("likes_item(b_flag) ", likes.getLikes_item()[i].getBottom_flag());
//                    Log.e("likes_item ", likes.getLikes_item()[i].getContents_error());

//                    Log.e("likes_item(sort) ", likes.getLikes_item()[i].getSort());

                    if (likes.getLikes_item()[i].getCategory().equals("follow")) {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {

                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }
                    } else if (likes.getLikes_item()[i].getCategory().equals("like")) {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {

                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_id());
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url());
                            }

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }
                    } else if (likes.getLikes_item()[i].getCategory().equals("comment")) {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {
                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_id());
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url());
                            }
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getComment_text());

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }

                    } else {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {

                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());

                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_id());
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url()); }

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }
                    }

                }

                Log.e("likes", likes.getBottom_item());
                Log.e("likes", likes.getError());
                Log.e("likes", likes.getError_msg());
            }

            @Override
            public void onFailure(Call<LikesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }

    private void PostTestForLikesMine(String tag, String temp1, String temp2){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<LikesResponse> call = apiService.postLike(tag, temp1, temp2);

        call.enqueue(new Callback<LikesResponse>() {
            @Override
            public void onResponse(Call<LikesResponse> call, Response<LikesResponse> response) {
                LikesResponse likes = response.body();

                Toast.makeText(getApplicationContext(), likes.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), likes.getError_msg(), Toast.LENGTH_SHORT).show();

                for(int i=0; i<likes.getLikes_item().length; i++) {
                    Log.e("likes_item ", "i : "+i);
                    Log.e("likes_item ", likes.getLikes_item()[i].getCategory());
                    Log.e("likes_item ", likes.getLikes_item()[i].getFollowing_user_uid());
                    Log.e("likes_item ", likes.getLikes_item()[i].getFollowing_user_nickName());
                    Log.e("likes_item ", likes.getLikes_item()[i].getFollowing_user_profile_img_thumb());
                    Log.e("likes_item ", likes.getLikes_item()[i].getCreated_at());

//                    Log.e("likes_item(b_flag) ", likes.getLikes_item()[i].getBottom_flag());
//                    Log.e("likes_item ", likes.getLikes_item()[i].getContents_error());

//                    Log.e("likes_item(sort) ", likes.getLikes_item()[i].getSort());

                    if (likes.getLikes_item()[i].getCategory().equals("follow")) {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {

                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getFollow_state());

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }
                    } else if (likes.getLikes_item()[i].getCategory().equals("like")) {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {

                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_id());
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url());
                            }

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }
                    } else if (likes.getLikes_item()[i].getCategory().equals("comment")) {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {
                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_id());
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url());
                            }
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getComment_text());

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }

                    } else {
                        for (int j = 0; j < likes.getLikes_item()[i].getContents().length; j++) {

                            Log.e("likes_item -> contents ", "i : " + i + " && j : " + j);
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getUid());
//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getNick_name());

                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_id());
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url()); }

//                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getBottom_flag());
                            Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getContents_error());
                        }
                    }

                }

                Log.e("likes", likes.getBottom_item());
                Log.e("likes", likes.getError());
                Log.e("likes", likes.getError_msg());
            }

            @Override
            public void onFailure(Call<LikesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }



    private void PostTokenRegiserForFCM(String tag, String temp1, String temp2, String temp3){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<FCMResponse> call = apiService.postTokenRegistserForFCM(tag, temp1, temp2, temp3);

        call.enqueue(new Callback<FCMResponse>() {
            @Override
            public void onResponse(Call<FCMResponse> call, Response<FCMResponse> response) {
                FCMResponse fcm = response.body();

                Toast.makeText(getApplicationContext(), fcm.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), fcm.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("fcm", fcm.getError());
                Log.e("fcm", fcm.getError_msg());
            }

            @Override
            public void onFailure(Call<FCMResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestUploadForFCM(String tag, String temp1){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<FCMResponse> call = apiService.postTestUploadForFCM(tag, temp1);

        call.enqueue(new Callback<FCMResponse>() {
            @Override
            public void onResponse(Call<FCMResponse> call, Response<FCMResponse> response) {
                FCMResponse fcm = response.body();

                Toast.makeText(getApplicationContext(), fcm.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), fcm.getError_msg(), Toast.LENGTH_SHORT).show();

                if(fcm.getFcm_server_error() != null) Log.e("fcm", fcm.getFcm_server_error());
                Log.e("fcm", fcm.getError());
                Log.e("fcm", fcm.getError_msg());
            }

            @Override
            public void onFailure(Call<FCMResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
}
