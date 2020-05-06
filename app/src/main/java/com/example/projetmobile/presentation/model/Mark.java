package com.example.projetmobile.presentation.model;

import android.graphics.drawable.Drawable;

public class Mark
{
    private String name;
    private String url;
    private String film;

    public String getName() {
        return name;
    }

    public Drawable getUrl() {
        return Drawable.createFromPath(url);
    }

    public String getFilm() { return film; }
}
