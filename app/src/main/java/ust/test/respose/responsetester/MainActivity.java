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
import rest.ServerInfoResponse;
import rest.TimelineBtnResponse;
import rest.UploadBoardResponse;
import rest.UserResponse;
import rest.UserResponseForLike;
import rest.WishlistResponse;
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
                "PostTestForDetailView(타임라인 디테일뷰)"
                , "PostTestForTimelineBtn(타임라인 버튼)"
                , "PostTestForMyplace(개인공간)"
                , "PostTestForMyplaceUserEdit(개인공간 프로필수정)"
                , "PostTestForMyplaceWishlist(개인공간 위시리스트)"
                , "PostTestForLikes(팔로잉탭)"
                , "PostTestForLikesMine(내게시물탭)"
                , "PostTokenRegiserForFCM(fcm토큰저장)"
                , "PostTestUploadForFCM(fcm테스트)"
                , "PostTestRegisterMember(회원가입테스트)"
                , "PostTestContactFollow(연락처팔로추가)"
                , "PostTestRegister(중복체크)"
                , "PostTestLogin(로그인리스폰값테스트)"
                , "PostTestServerInfo(서버정보값테스트)"
        };
        final String[][] postList = {
                {"tag", "uid", "article_id"}
                , {"tag", "uid", "article_id", "comment_id", "bottom_comment", "comment_text", "article_text", "wishlist_text", "like_state", "wishlist_state", "block_uid", "block_state", "reason"}
                , {"tag", "myplace_uid", "request_uid", "bottom_article" }
                , {"tag", "uid", "name", "nick_name", "website", "self_introduce", "phone_number", "gender", "birthday" }
                , {"tag", "uid" }
                , {"tag", "uid", "bottom_item" }
                , {"tag", "uid", "bottom_item" }
                , {"tag", "uid", "token", "login_state" }
                , {"tag", "uid" }
                , {"tag", "name", "gender", "email", "nick_name", "password", "phone_number", "login_method", "fb_id", "kt_id", "profile_img", "profile_img_thumb" }
                , {"tag", "phone_number_01", "phone_number_02", "phone_number_03" }
                , {"tag", "fb_id", "kt_id", "email", "nick_name" }
                , {"tag", "uid(임시)", "email", "password" }
                , {"tag", "uid", "photo_size" }
        };
        //test

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
                    LinearLayout temp_linear = (LinearLayout) param_layout.getChildAt(i);
                    param_post_values[i] = ((EditText) temp_linear.findViewById(R.id.param_input_text)).getText().toString();
                }

                if (seleted_method_num == 0) {
                    PostTestForDetailView(param_post_values[0], param_post_values[1], param_post_values[2]);
                }else if(seleted_method_num == 1){
                    PostTestForTimelineBtn(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3], param_post_values[4]
                                            ,param_post_values[5], param_post_values[6], param_post_values[7], param_post_values[8], param_post_values[9]
                                            ,param_post_values[10], param_post_values[11], param_post_values[12]);
                }else if(seleted_method_num == 2){
                    PostTestForMyplace(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3]);
                } else if(seleted_method_num == 3){
//                    PostTestForMyplaceUserEdit(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3]
//                                            , param_post_values[4], param_post_values[5], param_post_values[6], param_post_values[7], param_post_values[8]);
                    PostTestForMyplaceUserEdit("profile", param_post_values[1], param_post_values[2], param_post_values[3]
                                            , param_post_values[4], param_post_values[5], param_post_values[6], param_post_values[7], param_post_values[8]);
                } else if(seleted_method_num == 4){
                    PostTestForMyplaceWishlist(param_post_values[0], param_post_values[1]);
                } else if(seleted_method_num == 5){
                    PostTestForLikes(param_post_values[0], param_post_values[1], param_post_values[2]);
                } else if(seleted_method_num == 6){
                    PostTestForLikesMine(param_post_values[0], param_post_values[1], param_post_values[2]);
                } else if(seleted_method_num == 7){
//                    PostTokenRegiserForFCM(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3]);
                    PostTokenRegiserForFCM("token_register", param_post_values[1], param_post_values[2], param_post_values[3]);
                } else if(seleted_method_num == 8){
//                    PostTestUploadForFCM(param_post_values[0], param_post_values[1]);
                    PostTestUploadForFCM("test_upload", param_post_values[1]);
                } else if(seleted_method_num == 9){
//                    PostTestUploadForFCM(param_post_values[0], param_post_values[1]);
                    PostTestRegisterMember(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3], param_post_values[4]
                                            , param_post_values[5], param_post_values[6], param_post_values[7], param_post_values[8], param_post_values[9]
                                            , param_post_values[10], param_post_values[11]);
                } else if(seleted_method_num == 10){
                    String[] param_post_values_phoneNumber = {param_post_values[1], param_post_values[2], param_post_values[3]};
                    String[] param_post_values_name = {"testname01", "testname02", "testname03"};
//                    ArrayList<String> param_post_values_arraytest = new ArrayList<String>();
//                    param_post_values_arraytest.add(0, param_post_values[1]);
//                    param_post_values_arraytest.add(1, param_post_values[2]);
//                    param_post_values_arraytest.add(2, param_post_values[3]);
//                    PostTestContactFollow(param_post_values[0], param_post_values_arraytest, param_post_values_arraytest.get(0));
                    PostTestContactFollow(param_post_values[0], param_post_values_name, param_post_values_phoneNumber);
                } else if(seleted_method_num == 11){
//                    PostTestUploadForFCM(param_post_values[0], param_post_values[1]);
                    PostTestRegister(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3], param_post_values[4]);
                } else if(seleted_method_num == 12){
//                    PostTestUploadForFCM(param_post_values[0], param_post_values[1]);
                    PostTestLogin(param_post_values[0], param_post_values[1], param_post_values[2], param_post_values[3]);
                } else if(seleted_method_num == 13){
                    PostTestServerInfo("server_info", param_post_values[1], param_post_values[2]);
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
    private void PostTestRegisterMember(String tag, String temp1, String temp2, String temp3, String temp4
                                            , String temp5, String temp6, String temp7, String temp8
                                            , String temp9, String temp10, String temp11){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UserResponse> call = apiService.postTestRegisterMember(tag, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("error", user1.getError());
                Log.e("error", user1.getError_msg());

                if( null != user1.getUser()) {
                    if(null != user1.getUser().getUid()) Log.e("user_info", user1.getUser().getUid());
                    if(null != user1.getUser().getName()) Log.e("user_info", user1.getUser().getName());
                    if(null != user1.getUser().getGender()) Log.e("user_info", user1.getUser().getGender());
                    if(null != user1.getUser().getEmail()) Log.e("user_info", user1.getUser().getEmail());
                    if(null != user1.getUser().getNick_name()) Log.e("user_info", user1.getUser().getNick_name());
                    if(null != user1.getUser().getPhone_number()) Log.e("user_info", user1.getUser().getPhone_number());
                    if(null != user1.getUser().getCreated_at()) Log.e("user_info", user1.getUser().getCreated_at());

                    if(null != user1.getUser().getLogin_method()) Log.e("user_info", user1.getUser().getLogin_method());
                    if(null != user1.getUser().getFb_id()) Log.e("user_info", user1.getUser().getFb_id());
                    if(null != user1.getUser().getKt_id()) Log.e("user_info", user1.getUser().getKt_id());
                    if(null != user1.getUser().getProfile_img()) Log.e("user_info", user1.getUser().getProfile_img());
                    if(null != user1.getUser().getSelf_introduce()) Log.e("user_info", user1.getUser().getSelf_introduce());
                    if(null != user1.getUser().getWebsite()) Log.e("user_info", user1.getUser().getWebsite());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestRegister(String tag, String temp1, String temp2, String temp3, String temp4){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UserResponse> call = apiService.postTestRegister(tag, temp1, temp2, temp3, temp4);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("error", user1.getError());
                Log.e("error", user1.getError_msg());

                if( null != user1.getUser()) {
                    if(null != user1.getUser().getUid()) Log.e("user_info", user1.getUser().getUid());
                    if(null != user1.getUser().getName()) Log.e("user_info", user1.getUser().getName());
                    if(null != user1.getUser().getGender()) Log.e("user_info", user1.getUser().getGender());
                    if(null != user1.getUser().getEmail()) Log.e("user_info", user1.getUser().getEmail());
                    if(null != user1.getUser().getNick_name()) Log.e("user_info", user1.getUser().getNick_name());
                    if(null != user1.getUser().getPhone_number()) Log.e("user_info", user1.getUser().getPhone_number());
                    if(null != user1.getUser().getCreated_at()) Log.e("user_info", user1.getUser().getCreated_at());

                    if(null != user1.getUser().getLogin_method()) Log.e("user_info", user1.getUser().getLogin_method());
                    if(null != user1.getUser().getFb_id()) Log.e("user_info", user1.getUser().getFb_id());
                    if(null != user1.getUser().getKt_id()) Log.e("user_info", user1.getUser().getKt_id());
                    if(null != user1.getUser().getProfile_img()) Log.e("user_info", user1.getUser().getProfile_img());
                    if(null != user1.getUser().getSelf_introduce()) Log.e("user_info", user1.getUser().getSelf_introduce());
                    if(null != user1.getUser().getWebsite()) Log.e("user_info", user1.getUser().getWebsite());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
//    private void PostTestContactFollow(String tag, ArrayList<String> temp1, String temp2){
    private void PostTestContactFollow(String tag, String[] temp1, String[] temp2){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<TimelineBtnResponse> call = apiService.postTestContactFollow(tag, temp1, temp2);
        call.enqueue(new Callback<TimelineBtnResponse>() {
            @Override
            public void onResponse(Call<TimelineBtnResponse> call, Response<TimelineBtnResponse> response) {
                TimelineBtnResponse user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();

                if (null != user1.getError()) Log.e("error", user1.getError());
                if (null != user1.getError_msg()) Log.e("error", user1.getError_msg());

                if( null != user1.getUser()) {
                    for(int i=0; i<user1.getUser().length; i++) {
                        Log.e("contact_follow ", "i : " + i);
                        if (null != user1.getUser()[i].getUid()) Log.e("contact_follow", user1.getUser()[i].getUid());
                        if (null != user1.getUser()[i].getName()) Log.e("contact_follow", user1.getUser()[i].getName());
                        if (null != user1.getUser()[i].getNick_name()) Log.e("contact_follow", user1.getUser()[i].getNick_name());
                        if (null != user1.getUser()[i].getProfile_img()) Log.e("contact_follow", user1.getUser()[i].getProfile_img());
                    }
                }
            }

            @Override
            public void onFailure(Call<TimelineBtnResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestLogin(String tag, String temp1, String temp2, String temp3){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UserResponse> call = apiService.postTestLogin(tag, temp1, temp2, temp3);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user1 = response.body();

                Toast.makeText(getApplicationContext(), user1.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), user1.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("error", user1.getError());
                Log.e("error", user1.getError_msg());

                if( null != user1.getUser()) {
                    Log.e("user_info", user1.getUser().getUid());
                    Log.e("user_info", user1.getUser().getName());
                    Log.e("user_info", user1.getUser().getGender());
                    Log.e("user_info", user1.getUser().getEmail());
                    Log.e("user_info", user1.getUser().getNick_name());
                    if(null != user1.getUser().getBirthday()) Log.e("user_info", user1.getUser().getBirthday());
                    if(null != user1.getUser().getPhone_number()) Log.e("user_info", user1.getUser().getPhone_number());
                    Log.e("user_info", user1.getUser().getCreated_at());

                    if(null != user1.getUser().getLogin_method()) Log.e("user_info", user1.getUser().getLogin_method());
                    if(null != user1.getUser().getFb_id()) Log.e("user_info", user1.getUser().getFb_id());
                    if(null != user1.getUser().getKt_id()) Log.e("user_info", user1.getUser().getKt_id());
                    if(null != user1.getUser().getProfile_img()) Log.e("user_info", user1.getUser().getProfile_img());
                    if(null != user1.getUser().getSelf_introduce()) Log.e("user_info", user1.getUser().getSelf_introduce());
                    if(null != user1.getUser().getWebsite()) Log.e("user_info", user1.getUser().getWebsite());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestServerInfo(String tag, String temp1, String temp2){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ServerInfoResponse> call = apiService.postTestServerInfo(tag, temp1, temp2);
        call.enqueue(new Callback<ServerInfoResponse>() {
            @Override
            public void onResponse(Call<ServerInfoResponse> call, Response<ServerInfoResponse> response) {
                ServerInfoResponse server = response.body();

                Toast.makeText(getApplicationContext(), server.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), server.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("error", server.getError());
                Log.e("error", server.getError_msg());

                Log.e("server_info", server.getPhp_server_base());
                Log.e("server_info", server.getPhoto_server_base());
                Log.e("server_info", server.getArticle_image_base());
                Log.e("server_info", server.getProfile_image_base());
            }

            @Override
            public void onFailure(Call<ServerInfoResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }

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
    private void PostTestForDetailView(String tag, String temp1, String temp2){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<DetailViewResponse> call = apiService.postDetailView(tag, temp1, temp2);

        call.enqueue(new Callback<DetailViewResponse>() {
            @Override
            public void onResponse(Call<DetailViewResponse> call, Response<DetailViewResponse> response) {
                DetailViewResponse article = response.body();

                Toast.makeText(getApplicationContext(), article.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), article.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("article", article.getArticle().getUid());
                Log.e("article", article.getArticle().getNick_name());
                Log.e("article", article.getArticle().getProfile_img());
                Log.e("article", article.getArticle().getProfile_img_thumb());

                Log.e("article", article.getArticle().getArticle_id());
                Log.e("article", article.getArticle().getArticle_photo_url());
                Log.e("article", article.getArticle().getArticle_photo_thumb_url());
                Log.e("article", article.getArticle().getArticle_text());
                Log.e("article", article.getArticle().getArticle_created_at());

                Log.e("article", article.getArticle().getArticle_like_cnt());
                Log.e("article", article.getArticle().getArticle_view_cnt());
                Log.e("article", article.getArticle().getArticle_comment_cnt());

                Log.e("article", article.getArticle().getArticle_like_state());
                Log.e("article", article.getArticle().getArticle_follow_state());
                Log.e("article", article.getArticle().getArticle_wishlist_state());

                if(null != article.getComment()) {
                    for (int i = 0; i < article.getComment().length; i++) {
                        Log.e("comment", article.getComment()[i].getUid());
                        Log.e("comment", article.getComment()[i].getNick_name());
                        Log.e("comment", article.getComment()[i].getProfile_img_thumb());
                        Log.e("comment", article.getComment()[i].getComment_id());
                        Log.e("comment", article.getComment()[i].getComment_text());
                        Log.e("comment", article.getComment()[i].getComment_created_at());
                    }
                }
                if(null != article.getGrid_article()) {
                    for (int i = 0; i < article.getGrid_article().length; i++) {
                        Log.e("grid", article.getGrid_article()[i].getArticle_id());
                        Log.e("grid", article.getGrid_article()[i].getArticle_photo_url());
                        Log.e("grid", article.getGrid_article()[i].getArticle_photo_thumb_url());
                    }
                }

                if(article.isComment_error()) {
                    Log.e("error", "comment_error : true");
                } else {
                    Log.e("error", "comment_error : false");
                }
                if(article.isGrid_error()) {
                    Log.e("error", "grid_error : true");
                } else {
                    Log.e("error", "grid_error : false");
                }
            }

            @Override
            public void onFailure(Call<DetailViewResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
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

    private void PostTestForTimelineBtn(String tag, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6
                                            , String temp7, String temp8, String temp9, String temp10, String temp11, String temp12){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<TimelineBtnResponse> call = apiService.postTimelineBtn(tag, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12);

        call.enqueue(new Callback<TimelineBtnResponse>() {
            @Override
            public void onResponse(Call<TimelineBtnResponse> call, Response<TimelineBtnResponse> response) {
                TimelineBtnResponse timelineBtn = response.body();

                Toast.makeText(getApplicationContext(), timelineBtn.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), timelineBtn.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("tag", timelineBtn.getError());
                Log.e("tag", timelineBtn.getError_msg());


                if(null != timelineBtn.getUser()) {
                    for (int i = 0; i < timelineBtn.getUser().length; i++) {
                        Log.e("user", "i : " + i);
                        if (null != timelineBtn.getUser()[i].getUid()) Log.e("user", timelineBtn.getUser()[i].getUid());
                        if (null != timelineBtn.getUser()[i].getName()) Log.e("user", timelineBtn.getUser()[i].getName());
                        if (null != timelineBtn.getUser()[i].getNick_name()) Log.e("user", timelineBtn.getUser()[i].getNick_name());
                        if (null != timelineBtn.getUser()[i].getProfile_img_thumb()) Log.e("user", timelineBtn.getUser()[i].getProfile_img_thumb());
                    }
                }

            }

            @Override
            public void onFailure(Call<TimelineBtnResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }



    private void PostTestForMyplace(String tag, String temp1, String temp2, String temp3){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MyPlaceResponse> call = apiService.postMyplace(tag, temp1, temp2, temp3);

        call.enqueue(new Callback<MyPlaceResponse>() {
            @Override
            public void onResponse(Call<MyPlaceResponse> call, Response<MyPlaceResponse> response) {
                MyPlaceResponse myplace = response.body();

                Toast.makeText(getApplicationContext(), myplace.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), myplace.getError_msg(), Toast.LENGTH_SHORT).show();

                if(null != myplace.getPlace_info()) {
                    if (null != myplace.getPlace_info().getName()) Log.e("myplace", myplace.getPlace_info().getName());
                    if (null != myplace.getPlace_info().getNick_name()) Log.e("myplace", myplace.getPlace_info().getNick_name());
                    if (null != myplace.getPlace_info().getProfile_img()) Log.e("myplace", myplace.getPlace_info().getProfile_img());
                    if (null != myplace.getPlace_info().getSelf_introduce()) Log.e("myplace", myplace.getPlace_info().getSelf_introduce());

                    if (null != myplace.getPlace_info().getArticle_count()) Log.e("myplace", myplace.getPlace_info().getArticle_count());
                    if (null != myplace.getPlace_info().getFollower_count()) Log.e("myplace", myplace.getPlace_info().getFollower_count());
                    if (null != myplace.getPlace_info().getFollowing_count()) Log.e("myplace", myplace.getPlace_info().getFollowing_count());
                }

                if(null != myplace.getCouple_info()) {
                    if (null != myplace.getCouple_info().getName()) Log.e("myplace", myplace.getCouple_info().getName());
                    if (null != myplace.getCouple_info().getNick_name()) Log.e("myplace", myplace.getCouple_info().getNick_name());
                    if (null != myplace.getCouple_info().getProfile_img()) Log.e("myplace", myplace.getCouple_info().getProfile_img());
                    if (null != myplace.getCouple_info().getSelf_introduce()) Log.e("myplace", myplace.getCouple_info().getSelf_introduce());

                    if (null != myplace.getCouple_info().getArticle_count()) Log.e("myplace", myplace.getCouple_info().getArticle_count());
                    if (null != myplace.getCouple_info().getFollower_count()) Log.e("myplace", myplace.getCouple_info().getFollower_count());
                    if (null != myplace.getCouple_info().getFollowing_count()) Log.e("myplace", myplace.getCouple_info().getFollowing_count());

                    if (null != myplace.getCouple_info().getCouple_day()) Log.e("myplace", myplace.getCouple_info().getCouple_day());
                }

                if(null != myplace.getArticle()) {
                    for (int i = 0; i < myplace.getArticle().length; i++) {
                        Log.e("myplace", "i : " + i);
                        if (null != myplace.getArticle()[i].getUid()) Log.e("myplace", myplace.getArticle()[i].getUid());
                        if (null != myplace.getArticle()[i].getNick_name()) Log.e("myplace", myplace.getArticle()[i].getNick_name());
                        if (null != myplace.getArticle()[i].getProfile_img()) Log.e("myplace", myplace.getArticle()[i].getProfile_img());
                        if (null != myplace.getArticle()[i].getProfile_img_thumb()) Log.e("myplace", myplace.getArticle()[i].getProfile_img_thumb());
                        if (null != myplace.getArticle()[i].getArticle_id()) Log.e("myplace", myplace.getArticle()[i].getArticle_id());
                        if (null != myplace.getArticle()[i].getArticle_photo_url()) Log.e("myplace", myplace.getArticle()[i].getArticle_photo_url());
                        if (null != myplace.getArticle()[i].getArticle_photo_thumb_url()) Log.e("myplace", myplace.getArticle()[i].getArticle_photo_thumb_url());
                        if (null != myplace.getArticle()[i].getArticle_text()) Log.e("myplace", myplace.getArticle()[i].getArticle_text());
                        if (null != myplace.getArticle()[i].getArticle_secret()) Log.e("myplace", myplace.getArticle()[i].getArticle_secret());
                        if (null != myplace.getArticle()[i].getArticle_created_at()) Log.e("myplace", myplace.getArticle()[i].getArticle_created_at());
                        if (null != myplace.getArticle()[i].getArticle_like_cnt()) Log.e("myplace", myplace.getArticle()[i].getArticle_like_cnt());
                        if (null != myplace.getArticle()[i].getArticle_view_cnt()) Log.e("myplace", myplace.getArticle()[i].getArticle_view_cnt());
                        if (null != myplace.getArticle()[i].getArticle_comment_cnt()) Log.e("myplace", myplace.getArticle()[i].getArticle_comment_cnt());
                        if (null != myplace.getArticle()[i].getArticle_like_state()) Log.e("myplace", myplace.getArticle()[i].getArticle_like_state());
                    }
                }

                if (null != myplace.getError()) Log.e("myplace", myplace.getError());
                if (null != myplace.getError_msg()) Log.e("myplace", myplace.getError_msg());
                if (null != myplace.getArticle_error()) Log.e("myplace article_error", myplace.getArticle_error());
                if (null != myplace.getCouple_error()) Log.e("myplace couple_error", myplace.getCouple_error());
            }

            @Override
            public void onFailure(Call<MyPlaceResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestForMyplaceUserEdit(String tag, String temp1, String temp2, String temp3, String temp4, String temp5, String temp6, String temp7, String temp8){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MyPlaceResponse> call = apiService.postMyplaceUserEdit(tag, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8);

        call.enqueue(new Callback<MyPlaceResponse>() {
            @Override
            public void onResponse(Call<MyPlaceResponse> call, Response<MyPlaceResponse> response) {
                MyPlaceResponse myplace = response.body();

                Toast.makeText(getApplicationContext(), myplace.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), myplace.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("fcm", myplace.getError());
                Log.e("fcm", myplace.getError_msg());
            }

            @Override
            public void onFailure(Call<MyPlaceResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("tag", t.toString());
            }
        });
    }
    private void PostTestForMyplaceWishlist(String tag, String temp1){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<WishlistResponse> call = apiService.postMyplaceWishlist(tag, temp1);

        call.enqueue(new Callback<WishlistResponse>() {
            @Override
            public void onResponse(Call<WishlistResponse> call, Response<WishlistResponse> response) {
                WishlistResponse wishlist = response.body();

                Toast.makeText(getApplicationContext(), wishlist.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), wishlist.getError_msg(), Toast.LENGTH_SHORT).show();

                Log.e("error", wishlist.getError());
                Log.e("error", wishlist.getError_msg());

                if(null != wishlist.getWishlist_article()) {
                    for (int i = 0; i < wishlist.getWishlist_article().length; i++) {
                        Log.e("wishlist", "i : " + i);
                        Log.e("wishlist", wishlist.getWishlist_article()[i].getArticle_id());
                        if (null != wishlist.getWishlist_article()[i].getArticle_photo_url())
                            Log.e("wishlist", wishlist.getWishlist_article()[i].getArticle_photo_url());
                        if (null != wishlist.getWishlist_article()[i].getArticle_photo_thumb_url())
                            Log.e("wishlist", wishlist.getWishlist_article()[i].getArticle_photo_thumb_url());
                        if (null != wishlist.getWishlist_article()[i].getWishlist_text())
                            Log.e("wishlist", wishlist.getWishlist_article()[i].getWishlist_text());
                    }
                }
            }

            @Override
            public void onFailure(Call<WishlistResponse> call, Throwable t) {
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
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_url());
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
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_url());
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
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url());
                            }
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_url());
                            }

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
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_url());
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
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_url());
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
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_thumb_url());
                            }
                            if( null != likes.getLikes_item()[i].getContents()[j].getArticle_photo_url() ) {
                                Log.e("likes_item -> contents ", likes.getLikes_item()[i].getContents()[j].getArticle_photo_url());
                            }

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
