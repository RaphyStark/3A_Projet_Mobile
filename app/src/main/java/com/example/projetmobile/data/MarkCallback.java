package com.example.projetmobile.data;

import com.example.projetmobile.presentation.model.Mark;
import java.util.List;

public interface MarkCallback
{
    void onSuccess(List<Mark> response);
    void onFailed();
}
