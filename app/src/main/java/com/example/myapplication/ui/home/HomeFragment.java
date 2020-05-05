package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;

public class HomeFragment extends Fragment {

private TextView tvtotalconfirmed,tvtotalrip,tvtotalrecovered;
private ProgressBar progressBar;
private Thread secThread;
private Runnable runnable;
private Button  onClickContinue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tvtotalconfirmed=root.findViewById(R.id.tvTotalConfirmed);
        setContentView(R.layout.fragment_home);
        tvtotalrip= root.findViewById(R.id.tvTotalRIP);
        init();
        tvtotalrecovered= root.findViewById(R.id.tvTotalRecovered);
        progressBar=root.findViewById(R.id.progress);

        getData();
        

        return root;
    }

    private void setContentView(int fragment_home) {
    }

    private void init(){
        runnable = new Runnable() {
            @Override
            public void run() {
               getData();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvtotalconfirmed.setText(jsonObject.getString("cases"));
                    tvtotalrip.setText(jsonObject.getString("deaths"));
                    tvtotalrecovered.setText(jsonObject.getString("recovered"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Responce",error.toString());

            }

    });
        queue.add(stringRequest);
    }




}
