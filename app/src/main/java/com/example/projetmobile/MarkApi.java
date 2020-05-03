package com.example.projetmobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarkApi {
    @GET("")
    Call<RestMarkResponse> getMarkResponse();
}
