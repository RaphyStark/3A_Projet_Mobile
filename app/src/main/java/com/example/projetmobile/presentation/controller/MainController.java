package com.example.projetmobile.presentation.controller;

import com.example.projetmobile.data.MarkCallback;
import com.example.projetmobile.data.MarkRepository;
import com.example.projetmobile.presentation.model.Mark;
import com.example.projetmobile.presentation.view.MainActivity;

import java.util.List;


public class MainController
{
    private MainActivity view;
    private MarkRepository markRepository;

    public MainController(MainActivity mainActivity, MarkRepository markRepository)
    {
        this.view = mainActivity;
        //this.markRepository = markRepository;
        markRepository.getMarkResponse(new MarkCallback()
        {
            @Override
            public void onSuccess(List<Mark> response)
            {
                view.showList(response);
            }

            @Override
            public void onFailed()
            {
                view.showError();
            }
        });

        this.markRepository = markRepository;


    }
/*
    public void onStart()
    {
        MarkCallback toto = new MarkCallback()
        {
            @Override
            public void onSuccess(List<Mark> response)
            {
                view.showList(response);
            }

            @Override
            public void onFailed()
            {
                view.showError();
            }
        };

        System.out.println(toto);
        markRepository.getMarkResponse(toto);
    }
*/
    public void onItemClick(Mark mark)
    {
        view.navigateToDetails(mark);
    }
    /*
    private void makeApiCall()
    {
        Call<RestMarkResponse> call = Singletons.getMarkApi().getMarkResponse();
        call.enqueue(new Callback<RestMarkResponse>()
        {
            @Override
            public void onResponse(Call<RestMarkResponse> call, Response<RestMarkResponse> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    List<Mark> markList = response.body().getResults();
                    saveList(markList);
                    view.showList(markList);
                }
                else view.showError();
            }

            @Override
            public void onFailure(Call<RestMarkResponse> call, Throwable t)
            {
                view.showError();
            }
        });
    }
    */
}