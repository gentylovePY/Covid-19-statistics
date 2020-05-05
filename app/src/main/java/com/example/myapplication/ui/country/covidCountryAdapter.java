package com.example.myapplication.ui.country;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class covidCountryAdapter extends RecyclerView.Adapter<covidCountryAdapter.ViewHolder> {

    ArrayList<Covid19> covid19ArrayList;

    public covidCountryAdapter(ArrayList<Covid19> covid19s){
        this.covid19ArrayList = covid19s;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country,parent,false);


        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull covidCountryAdapter.ViewHolder holder, int position) {
        Covid19 covid19 = covid19ArrayList.get(position);
        holder.tvtotalcases.setText(covid19.getmCases());
        holder.tvCountryName.setText(covid19.getmCovidCountry());

    }

    @Override
    public int getItemCount() {
        return covid19ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtotalcases,tvCountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtotalcases = itemView.findViewById(R.id.tvtotalCase);
            tvCountryName = itemView.findViewById(R.id.tvtotalName);
        }
    }
}
