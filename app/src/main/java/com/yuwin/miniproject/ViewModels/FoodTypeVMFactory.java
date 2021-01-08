package com.yuwin.miniproject.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FoodTypeVMFactory implements ViewModelProvider.Factory {

    private final Application mApplication;

    public FoodTypeVMFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FoodTypeViewModel(mApplication);
    }
}
