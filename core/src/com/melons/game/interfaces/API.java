package com.melons.game.interfaces;

import com.melons.game.Constants;
import com.melons.game.models.MelonData;
import com.melons.game.models.PostData;
import com.melons.game.models.TokenData;
import com.melons.game.models.ServerTokenResponse;
import com.melons.game.models.UserData;
import com.melons.game.models.UserResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @Headers({"X-API-KEY: " + Constants.API_KEY,
            "Content-Type: application/x-www-form-urlencoded"})

    @FormUrlEncoded
    @POST("api/user/request_token")
    Call<ServerTokenResponse<TokenData>> request_token(@Header("X-API-KEY") String api_key,
                                                       @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("api/user/login")
    Call<UserResponse<UserData>> login(@Header("X-API-KEY") String api_key,
                                       @FieldMap HashMap<String, String> map);


    @FormUrlEncoded
    @POST("api/melongame/update")
    Call<UserResponse<PostData>> update_melon(@Header("X-API-KEY") String api_key,
                                              @Header("X-TOKEN") String token,
                                              @FieldMap HashMap<String, String> map);



    @GET("api/melongame/all")
    Call<UserResponse<UserData>> get_melon(@Header("X-API-KEY") String api_key,
                                           @Header("X-TOKEN") String token,
                                           @Query("filter") String nick, @Query("field") String nickname);

}
