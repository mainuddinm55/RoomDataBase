package com.learner.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.learner.roomdatabase.db.Country;
import com.learner.roomdatabase.db.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository repository;
    private LiveData<List<Country>> countryList;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        repository = new CountryRepository(application.getApplicationContext());
        countryList = repository.getAllCountryList();
    }

    public void insert(Country country){
        repository.insertCountry(country);
    }

    public LiveData<List<Country>> getCountryList() {
        return countryList;
    }
}
