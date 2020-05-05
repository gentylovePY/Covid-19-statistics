package com.example.myapplication.ui.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryFragment extends Fragment {

    RecyclerView rvCovidCountry;
    ProgressBar ProgressBar;
    private static final  String TAG = CountryFragment.class.getSimpleName();
    ArrayList<Covid19> covidCountries;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_country, container, false);



        rvCovidCountry=root.findViewById(R.id.rvCountry);
        ProgressBar= root.findViewById(R.id.progress_country);
        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getContext()));


      getDataFromServer();

        return root;
    }
private  void showRecyclerViev(){
        covidCountryAdapter covidCountryAdapter = new covidCountryAdapter(covidCountries);
        rvCovidCountry.setAdapter(covidCountryAdapter);
}
    private void getDataFromServer() {
        String url = "https://corona.lmao.ninja/v2/countries";
        covidCountries = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ProgressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse:" + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            covidCountries.add(new Covid19(data.getString("casses"), data.getString("cases")));

                        }
                        showRecyclerViev();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ProgressBar.setVisibility(View.GONE);
                Log.e(TAG,"onResponse "+ error);

            }
        });
       Volley.newRequestQueue(getActivity()).add(stringRequest);


    }
}
