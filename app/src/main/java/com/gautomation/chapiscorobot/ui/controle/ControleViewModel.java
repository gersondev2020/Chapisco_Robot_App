package com.gautomation.chapiscorobot.ui.controle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ControleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ControleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}