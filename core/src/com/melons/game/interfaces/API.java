package com.melons.game.interfaces;

import com.melons.game.models.TokenGetter;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("api/user/request_token")
    Call<TokenGetter> request_token(@Header("X-API-KEY") String api_key,
                                    @FieldMap HashMap<String, String> map);

}
