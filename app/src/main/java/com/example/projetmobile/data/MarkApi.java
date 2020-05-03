package com.example.projetmobile.data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarkApi {
    @GET("/RaphyStark/ProjetMobile/master/MarkApi.json")
    Call<RestMarkResponse> getMarkResponse();
}