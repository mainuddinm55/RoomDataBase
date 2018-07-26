package com.learner.roomdatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learner.roomdatabase.db.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryHolder>{

    private List<Country>countryList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public CountryListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.country_name_item, parent, false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        holder.countryNameTV.setText(countryList.get(position).getCountryName());
    }

    @Override
    public int getItemCount() {
        if (countryList!=null)
            return countryList.size();
        else
            return 0;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    public class CountryHolder extends RecyclerView.ViewHolder{
        TextView countryNameTV;
        public CountryHolder(View itemView) {
            super(itemView);
            countryNameTV = itemView.findViewById(R.id.country_name_tv);

        }
    }

}
