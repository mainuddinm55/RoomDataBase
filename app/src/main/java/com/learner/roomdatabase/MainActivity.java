package com.learner.roomdatabase;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.learner.roomdatabase.db.Country;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CountryViewModel viewModel;

    private EditText countryNameET;
    private Button saveBtn;
    private RecyclerView countryListRV;

    private CountryListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryNameET = findViewById(R.id.country_input_et);
        saveBtn = findViewById(R.id.save_country_btn);
        countryListRV = findViewById(R.id.country_list_rv);

        adapter = new CountryListAdapter(getApplicationContext());
        countryListRV.setHasFixedSize(true);
        countryListRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        countryListRV.setAdapter(adapter);

        saveBtn.setOnClickListener(this);

        viewModel.getCountryList().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable List<Country> countries) {
                adapter.setCountryList(countries);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (countryNameET.getText().toString().isEmpty()){
            countryNameET.setError("Country Name Required");
            countryNameET.requestFocus();
            return;
        }

        String name = countryNameET.getText().toString().trim();
        Country country = new Country(name);
        viewModel.insert(country);
        countryNameET.setText(null);
    }
}
