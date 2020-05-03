package com.example.projetmobile;

import java.util.List;

public class RestMarkResponse {
    private Integer numM;
    private String mainColor;
    private List<Mark> results;

    public Integer getNumM() {
        return numM;
    }

    public String getMainColor() {
        return mainColor;
    }

    public List<Mark> getResults() {
        return results;
    }
}
