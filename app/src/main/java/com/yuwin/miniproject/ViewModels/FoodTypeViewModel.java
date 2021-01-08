package com.yuwin.miniproject.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.yuwin.miniproject.DB.AppDatabase;
import com.yuwin.miniproject.DB.Entity.FoodTypeEntity;
import com.yuwin.miniproject.R;
import com.yuwin.miniproject.utility.Utility;

import java.util.Arrays;
import java.util.List;

public class FoodTypeViewModel extends AndroidViewModel {

    private AppDatabase appDB;

    public LiveData<List<FoodTypeEntity>> _foodTypes;



    public FoodTypeViewModel(@NonNull Application application) {
        super(application);
        appDB = AppDatabase.getInstance(application.getApplicationContext());
        if(appDB.foodTypeDao().count() ==  0){
            appDB.foodTypeDao().insertFoodTypes(data);
        }
        _foodTypes = appDB.foodTypeDao().getAllFoodTypes();
    }

    public LiveData<List<FoodTypeEntity>> get_foodTypes() {
        return _foodTypes;
    }

    private List<FoodTypeEntity> data = Arrays.asList(
            new FoodTypeEntity("Pasta", Utility.getEntryName(getApplication(), R.drawable.ic_pasta)),
            new FoodTypeEntity("Pizza", Utility.getEntryName(getApplication(), R.drawable.ic_pizza)),
            new FoodTypeEntity("Burger", Utility.getEntryName(getApplication(), R.drawable.ic_burger)),
            new FoodTypeEntity("Steak", Utility.getEntryName(getApplication(), R.drawable.ic_steak)),
            new FoodTypeEntity("Sushi", Utility.getEntryName(getApplication(), R.drawable.ic_sushi)),
            new FoodTypeEntity("Salad", Utility.getEntryName(getApplication(), R.drawable.ic_salad)),
            new FoodTypeEntity("Doughnut", Utility.getEntryName(getApplication(), R.drawable.ic_donut)),
            new FoodTypeEntity("Cake", Utility.getEntryName(getApplication(), R.drawable.ic_cake))
    );



}
