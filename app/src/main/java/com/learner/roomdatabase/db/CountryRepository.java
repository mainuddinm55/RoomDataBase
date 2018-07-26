package com.learner.roomdatabase.db;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class CountryRepository {

    private CountryDao countryDao;
    private LiveData<List<Country>> allCountryList;

    public CountryRepository(Context context){
        CountryDatabase database = CountryDatabase.getDatabase(context);
        countryDao = database.countryDao();
        allCountryList = countryDao.getAllCountry();
    }

    public LiveData<List<Country>> getAllCountryList() {
        return allCountryList;
    }


    public void insertCountry(Country name){
        countryDao.insert(name);
        //new InsertAsync(countryDao).execute();
    }

    private class InsertAsync extends AsyncTask<Country, Void, Void> {

        private CountryDao asyncCountryDao;

        public InsertAsync(CountryDao countryDao) {
            asyncCountryDao = countryDao;
        }


        @Override
        protected Void doInBackground(Country... countries) {
            asyncCountryDao.insert(countries[0]);
            return null;
        }
    }
}
